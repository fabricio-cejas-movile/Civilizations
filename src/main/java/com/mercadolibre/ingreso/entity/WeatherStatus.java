package com.mercadolibre.ingreso.entity;

import lombok.AccessLevel;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@lombok.Getter
@lombok.AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum WeatherStatus {

    SEQUIA("Sequia"),
    LLUVIA("Lluvia"),
    CONDICIONES_OPTIMAS("Condiciones_optimas"),
    NORMAL("Normal"),
    ERROR("No información para este dia");

    private String value;
}
