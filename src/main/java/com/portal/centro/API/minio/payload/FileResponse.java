package com.portal.centro.API.minio.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

//será utilizada para o retorno do objeto adicionado no serviço **Minio** contendo os campos que serão utilizados para armazenar no banco de dados da aplicação.

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class FileResponse {
    String filename;
    String contentType;
    Long fileSize;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private LocalDateTime createdTime;
}
