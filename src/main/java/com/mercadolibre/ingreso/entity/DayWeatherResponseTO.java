package com.mercadolibre.ingreso.entity;

import lombok.*;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DayWeatherResponseTO {

    private Integer day;

    private WeatherStatus weather;
}
