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

    public DayWeatherDTO(Integer dayNumber, WeatherStatus weather) {
        super(dayNumber, weather);
    }

    public DayWeatherDTO(Long id, Integer dayNumber, WeatherStatus weather) {
        super(dayNumber, weather);
        this.id = id;
    }

    public DayWeatherDTO(Long id, Integer dayNumber, String weather) {
        super(dayNumber, WeatherStatus.valueOf(weather.toUpperCase()));
        this.id = id;
    }
}
