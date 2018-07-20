package com.mercadolibre.ingreso.controller;

import com.mercadolibre.ingreso.commons.Logs;
import com.mercadolibre.ingreso.model.DayWeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@RestController
public class ForecastController {

    @Autowired
    private Logs log;

    @RequestMapping("/weather")
    @Produces(MediaType.APPLICATION_JSON)
    public DayWeatherDTO getDayWeather(@RequestParam(value = "day", required = true) Integer day) {

        StopWatch chronometer = new StopWatch();
        chronometer.start();

        DayWeatherDTO response = new DayWeatherDTO(day, "no weather");

        chronometer.stop();

        log.requestAnswered().info("[{}][response time: {}ms]", response, chronometer.getTotalTimeSeconds());

        return response;
    }
}
