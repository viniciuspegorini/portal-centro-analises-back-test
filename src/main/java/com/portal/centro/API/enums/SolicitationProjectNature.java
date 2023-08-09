package com.portal.centro.API.enums;

public enum SolicitationProjectNature {
  MASTERS_THESIS("Trabalho de Mestrado"),
  DOCTORATE_DISSERTATION("Trabalho de Doutorado"),
  UNDERGRADUATE_THESIS("Trabalho de Conclusão de curso (TCC)"),
  INTERNSHIP_PROJECT("Trabalho de Estágio"),
  SCIENTIFIC_INITIATION("Iniciação Científica (programas PIBIC/PIBIT)"),
  EXTENSION_PROJECT("Projeto de Extensão"),
  RESEARCH_PROJECT("Projeto de Pesquisa"),
  TEACHING_PROJECT("Projeto de Ensino"),
  OTHER("Outro");

  private String content;

  SolicitationProjectNature(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }
}
