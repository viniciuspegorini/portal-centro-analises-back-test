package com.portal.centro.API.service;

import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.minio.payload.FileResponse;
import com.portal.centro.API.minio.service.MinioService;
import com.portal.centro.API.minio.util.FileTypeUtils;
import com.portal.centro.API.model.TechnicalReport;
import com.portal.centro.API.repository.TechnicalReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TechnicalReportService extends GenericService<TechnicalReport, Long> {


    @Autowired
    public TechnicalReportService(TechnicalReportRepository technicalReportRepository, MinioService minioService, TemplateEngine templateEngine) {
        super(technicalReportRepository);
        this.technicalReportRepository = technicalReportRepository;
        this.minioService = minioService;
        this.templateEngine = templateEngine;
    }

    private  final  TechnicalReportRepository technicalReportRepository;
    private  final MinioService minioService;
    private TemplateEngine templateEngine;


    protected JpaRepository<TechnicalReport, Long> getRepository() {
        return  this.technicalReportRepository;
    }

    public TechnicalReport save(TechnicalReport entity, MultipartFile file) throws Exception {
        String fileType = FileTypeUtils.getFileType(file);
        if (fileType != null) {
            FileResponse fileResponse = minioService.putObject(file, "central-de-analises", fileType);
            entity.setFileName(fileResponse.getFilename());
            entity.setContentType(fileResponse.getContentType());
        }
        return  super.save(entity);
    }

    public  void downloadFile(Long id, HttpServletResponse response) {
        InputStream in = null;
        try {
            TechnicalReport technicalReport = this.findOneById(id);
            in = minioService.downloadObject("central-de-analises", technicalReport.getFileName());
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(technicalReport.getFileName(), "UTF-8"));
            response.setCharacterEncoding("UTF-8");
            // Remove bytes from InputStream Copied to the OutputStream.
            IOUtils.copy(in, response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    public void generateReport(TechnicalReport report) throws Exception {
        Context context = new Context();
        context.setVariables(DataToMap(report));

        String html = templateEngine.process("report", context);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        renderer.createPDF(out);

        FileResponse fileResponse = minioService.putObject((MultipartFile) new ByteArrayInputStream(out.toByteArray()), "central-de-analises",  "PDF");
        report.setFileName(fileResponse.getFilename());
        report.setContentType(fileResponse.getContentType());

        super.save(report);
    }

    public Map<String, Object> DataToMap(TechnicalReport report) {
        Map<String, Object> map = new HashMap<>();
        map.put("num", report.getId());
        map.put("reportDetails", report.getSolicitation());
        return map;
    }

}
