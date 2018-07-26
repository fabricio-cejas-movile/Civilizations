package com.mercadolibre.ingreso.service;

import com.mercadolibre.ingreso.commons.Logs;
import com.mercadolibre.ingreso.dao.WeatherDAO;
import com.mercadolibre.ingreso.entity.DayWeatherDTO;
import com.mercadolibre.ingreso.entity.DayWeatherResponseTO;
import com.mercadolibre.ingreso.entity.WeatherStatus;
import com.mercadolibre.ingreso.exceptions.NumberOutOfValueException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@RunWith(SpringRunner.class)
public class ForecastServiceTest {

    @Mock
    private Logs log;

    @Mock
    private WeatherDAO dao;

    @Spy
    @InjectMocks
    private ForecastService service;

    private DayWeatherResponseTO dayWeatherResponseTOExpected;

    @Before
    public void setUp() {

        dayWeatherResponseTOExpected = new DayWeatherResponseTO(1, WeatherStatus.LLUVIA);

        when(dao.findWeatherByDay(any(DayWeatherDTO.class))).thenReturn(Optional.of(new DayWeatherDTO(1, WeatherStatus.LLUVIA)));

        when(log.system()).thenReturn(LoggerFactory.getLogger("system"));

        when(log.exceptions()).thenReturn(LoggerFactory.getLogger("exceptions"));

    }

    @Test(expected = NumberOutOfValueException.class)
    public void getWeatherDayTestFail() throws NumberOutOfValueException {

        service.getWeatherDay(-1);

    }

    @Test(expected = NumberOutOfValueException.class)
    public void getWeatherDayTestFail2() throws NumberOutOfValueException {

        service.getWeatherDay(3650);

    }

    @Test
    public void getWeatherDayTestOk() {

        DayWeatherResponseTO dayWeatherResponseTO = service.getWeatherDay(1);

        assertEquals(dayWeatherResponseTOExpected.getDay(), dayWeatherResponseTO.getDay());
        assertEquals(dayWeatherResponseTOExpected.getWeather(), dayWeatherResponseTO.getWeather());
    }
}
