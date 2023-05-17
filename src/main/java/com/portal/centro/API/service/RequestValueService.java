package com.portal.centro.API.service;

import com.portal.centro.API.model.Equipment;
import com.portal.centro.API.model.Solicitation;
import com.portal.centro.API.model.TechnicalReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RequestValueService {

    public BigDecimal calculate(TechnicalReport report) {
        Solicitation solicitation = report.getSolicitation();
        Equipment equipment = report.getSolicitation().getEquipment();
        switch (solicitation.getTypeUser()) {
            case UTFPR -> {
                if (equipment.getValueHourUtfpr() != null) {
                    return equipment.getValueHourUtfpr().multiply(new BigDecimal(report.getAmountHours()));
                } else if (equipment.getValueSampleUtfpr() != null) {
                    return equipment.getValueSampleUtfpr().multiply(new BigDecimal(report.getAmountSamples()));
                }
            }
            case PATNER -> {
                if (equipment.getValueHourPartner() != null) {
                    return equipment.getValueHourPartner().multiply(new BigDecimal(report.getAmountHours()));
                } else if (equipment.getValueSamplePartner() != null) {
                    return equipment.getValueSamplePartner().multiply(new BigDecimal(report.getAmountSamples()));
                }
            }
            case PF_PJ -> {
                if (equipment.getValueHourPfPj() != null) {
                    return equipment.getValueHourPfPj().multiply(new BigDecimal(report.getAmountHours()));
                } else if (equipment.getValueSamplePfPj() != null) {
                    return equipment.getValueSamplePfPj().multiply(new BigDecimal(report.getAmountSamples()));
                }
            }
            default -> {
                return null;
            }
        }
        return null;
    }
}
