package com.mercadolibre.ingreso.service;

import com.mercadolibre.ingreso.commons.Logs;
import com.mercadolibre.ingreso.entity.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@RunWith(SpringRunner.class)
public class WeatherForecastTest {

    @Mock
    private Logs log;

    @Spy
    @InjectMocks
    private WeatherForecast service;

    private Planet ferengi;

    private Planet betasoide;

    private Planet vulcano;

    private Planet sun;

    @Before
    public void setUp() {

        ferengi = new Planet("Ferengi", 500, 1, AngularDirection.CLOCKWISE, 1);

        betasoide = new Planet("Betasoide", 2000, 3, AngularDirection.CLOCKWISE, 1);

        vulcano = new Planet("Vulcano", 1000, 5, AngularDirection.AGAINST_CLOCK, 1);

        sun = new Planet("Sol", 0, 0, AngularDirection.STATIC, 1);

        when(log.system()).thenReturn(LoggerFactory.getLogger("system"));

        when(log.exceptions()).thenReturn(LoggerFactory.getLogger("exceptions"));

    }

    @Ignore
    public void calculateWeatherOfDayTestRainingOK() {

        DayWeatherDTO dayWeatherDTO = new DayWeatherDTO(566, null);

        dayWeatherDTO = service.calculateWeatherOfDay(dayWeatherDTO);

        assertEquals(WeatherStatus.LLUVIA, dayWeatherDTO.getWeather());
        assertNotNull(dayWeatherDTO.getPerimeter());
    }

    @Ignore
    public void calculateWeatherOfDayTestNormalOK() {

        DayWeatherDTO dayWeatherDTO = new DayWeatherDTO(162, null);

        dayWeatherDTO = service.calculateWeatherOfDay(dayWeatherDTO);

        assertEquals(WeatherStatus.NORMAL, dayWeatherDTO.getWeather());
        assertNull(dayWeatherDTO.getPerimeter());
    }

    @Test
    public void areInLineTest() {

        ferengi.setDay(0);
        betasoide.setDay(0);
        vulcano.setDay(0);

        Boolean inLine = service.areInLine(ferengi, betasoide, vulcano);

        assertNotNull(inLine);
        assertTrue(inLine);
    }

    @Test
    public void isPlanetInsideTriangleTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        ferengi.setCoordenates(new Coordenates(0d, 90d));
        betasoide.setCoordenates(new Coordenates(80d, -1d));
        vulcano.setCoordenates(new Coordenates(-80d, -1d));

        Boolean isInside = service.isPlanetInsideTriangle(ferengi, betasoide, vulcano, sun);

        assertNotNull(isInside);
        assertTrue(isInside);

    }

    @Test
    public void isPlanetOutsideTriangleTest() {

        ferengi.setCoordenates(new Coordenates(90d, 90d));
        betasoide.setCoordenates(new Coordenates(-80d, 70d));
        vulcano.setCoordenates(new Coordenates(1000d, 1d));

        Boolean isInside = service.isPlanetInsideTriangle(ferengi, betasoide, vulcano, sun);

        assertNotNull(isInside);
        assertFalse(isInside);

    }

}
