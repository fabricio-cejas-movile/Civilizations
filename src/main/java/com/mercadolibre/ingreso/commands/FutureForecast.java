package com.mercadolibre.ingreso.commands;

import com.mercadolibre.ingreso.commons.Logs;
import com.mercadolibre.ingreso.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@Component
public class FutureForecast implements CommandLineRunner {

    @Autowired
    private Logs log;

    @Autowired
    private ForecastService service;

    @Override
    public void run(String... args) {

        if (!service.existsPredictions()) {
            service.generatePredictions();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        log.system().info("[Finish][job on {}]", dateFormat.format(new Date()));

    }
}
