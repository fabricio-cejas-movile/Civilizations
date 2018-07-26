package com.mercadolibre.ingreso.controller;

import com.mercadolibre.ingreso.commons.Logs;
import com.mercadolibre.ingreso.entity.DayWeatherResponseTO;
import com.mercadolibre.ingreso.exceptions.NumberOutOfValueException;
import com.mercadolibre.ingreso.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Autowired
    private ForecastService service;

    @RequestMapping("/weather")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody DayWeatherResponseTO getDayWeather(@RequestParam(value = "day", defaultValue = "1") Integer day) throws NumberOutOfValueException {

        StopWatch chronometer = new StopWatch();
        chronometer.start();

        DayWeatherResponseTO response = service.getWeatherDay(day);

        chronometer.stop();

        log.requestAnswered().info("[{}][response time: {}ms]", response, chronometer.getTotalTimeSeconds());

        return response;
    }
}
