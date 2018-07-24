package com.mercadolibre.ingreso.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class DayWeatherDTO extends DayWeatherResponseTO {

    private Long id;

    public DayWeatherDTO(Integer day, WeatherStatus weather) {
        super(day, weather);
    }

    public DayWeatherDTO(Long id, Integer day, WeatherStatus weather) {
        super(day, weather);
        this.id = id;
    }

    public DayWeatherDTO(Long id, Integer day, String weather) {
        super(day, WeatherStatus.valueOf(weather.toUpperCase()));
        this.id = id;
    }
}
