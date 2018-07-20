package com.mercadolibre.ingreso.model;

import lombok.*;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DayWeatherDTO {

    private Integer dayNumber;

    private String weather;
}
