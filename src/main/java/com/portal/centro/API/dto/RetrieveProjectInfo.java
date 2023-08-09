package com.portal.centro.API.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class RetrieveProjectInfo {
    TeacherDTO teacherDTO;
    List<ProjectDTO> projectDTOS;
}
