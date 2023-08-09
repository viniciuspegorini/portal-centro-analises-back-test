package com.portal.centro.API.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class UserBalance {

    public UserBalance(BigDecimal old, BigDecimal current) {
        this.old = old;
        this.current = current;
    }

    private BigDecimal old;
    private BigDecimal current;

}
