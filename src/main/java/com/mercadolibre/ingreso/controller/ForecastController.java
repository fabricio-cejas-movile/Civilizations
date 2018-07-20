package com.mercadolibre.ingreso.controller;

import com.mercadolibre.ingreso.model.DayWeatherDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@RestController
public class ForecastController {

    @RequestMapping("/weather")
    public DayWeatherDTO getDayWeather(@RequestParam(value = "day", required = true) Integer day) {
        return new DayWeatherDTO(day, "no weather");
    }
}
