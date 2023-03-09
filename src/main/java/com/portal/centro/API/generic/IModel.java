package com.portal.centro.API.generic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class IModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    private UUID id;

}







