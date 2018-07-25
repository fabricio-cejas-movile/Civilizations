package com.mercadolibre.ingreso.service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.ingreso.commons.Logs;
import com.mercadolibre.ingreso.dao.WeatherDAO;
import com.mercadolibre.ingreso.entity.DayWeatherDTO;
import com.mercadolibre.ingreso.entity.DayWeatherResponseTO;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@Service
public class ForecastService {

    @Autowired
    private Logs log;

    @Autowired
    private WeatherDAO dao;

    @Autowired
    private WeatherForecast weatherForecast;

    private static final int AMOUNT_DAYS = 365;

    private static final int AMOUNT_YEARS = 10;

    private static final int MAX_NUMBER_DAY = 3649;

    /**
     * Get specific weather of a day
     *
     * @param day
     * @return DayWeatherDTO Object
     */
    public DayWeatherResponseTO getWeatherDay(Integer day) throws Exception {

        if (day < 0 || day > MAX_NUMBER_DAY) {
            throw new Exception("The value for the day parameter must be in between 0 and 3649.");
        }

        DayWeatherDTO dayWeatherDTO = new DayWeatherDTO();
        dayWeatherDTO.setDay(day);

        Optional<DayWeatherDTO> optional = dao.findWeatherByDay(dayWeatherDTO);

        dayWeatherDTO = optional.orElse(new DayWeatherDTO());

        DayWeatherResponseTO dayWeatherResponseTO = new DayWeatherResponseTO(dayWeatherDTO.getDay(), dayWeatherDTO.getWeather());

        return dayWeatherResponseTO;
    }

    /**
     * Generate predictions for next ten years
     */
    public void generatePredictions() {

        IntStream.range(0, AMOUNT_YEARS).parallel().forEach(year -> {
            IntStream.range(0, AMOUNT_DAYS).parallel().forEach(day -> {

                final Integer uniqueDay = day + (year * AMOUNT_DAYS);

                DayWeatherDTO dayWeatherDTO = new DayWeatherDTO(uniqueDay, null);

                dayWeatherDTO = weatherForecast.calculateWeatherOfDay(dayWeatherDTO);
                
                Optional<DayWeatherDTO> optDayWeatherDTO = dao.saveDay(dayWeatherDTO);

                log.system().debug("[init registers in data base][day: {}][was saved: {}]", uniqueDay, optDayWeatherDTO.get());
            });
        });
    }

    /**
     * Consult in DB if registers were been generated
     */
    public Boolean existsPredictions() {

        return dao.getAmountRegisters().orElse(0) != 0;
    }
}
