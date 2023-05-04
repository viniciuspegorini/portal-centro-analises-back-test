package com.portal.centro.API.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    @Setter
    @Getter
    private String code;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "validate_at")
    private LocalDateTime validateAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
