package com.portal.centro.API.enums;

public enum SolicitationStatus {
    PENDING("Aguardando Confirmação"),
    REFUSED("Recusado"),
    PENDINGPAYMENT("Aguardando Pagamento"),
    FINISHED("Concluído"),
    APPROVED("Aguardando Análise");

    private String content;

    SolicitationStatus(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
