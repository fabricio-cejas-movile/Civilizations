package com.mercadolibre.ingreso.service;

import com.mercadolibre.ingreso.commons.Logs;
import com.mercadolibre.ingreso.entity.AngularDirection;
import com.mercadolibre.ingreso.entity.DayWeatherDTO;
import com.mercadolibre.ingreso.entity.Planet;
import com.mercadolibre.ingreso.entity.WeatherStatus;
import com.mercadolibre.ingreso.util.Line;
import com.mercadolibre.ingreso.util.Triangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Given weather conditions
 *
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@Service
public class WeatherForecast {

    @Autowired
    private Logs log;

    /**
     * Calculate weather conditions according dayValue in Object of DayWeatherDTO
     *
     * @param dayWeatherDTO
     * @return
     */
    public DayWeatherDTO calculateWeatherOfDay(DayWeatherDTO dayWeatherDTO) {

        WeatherStatus weatherStatus = WeatherStatus.NORMAL;

        Planet ferengi = new Planet("Ferengi", 500, 1, AngularDirection.CLOCKWISE, dayWeatherDTO.getDay());

        Planet betasoide = new Planet("Betasoide", 2000, 3, AngularDirection.CLOCKWISE, dayWeatherDTO.getDay());

        Planet vulcano = new Planet("Vulcano", 1000, 5, AngularDirection.AGAINST_CLOCK, dayWeatherDTO.getDay());

        Planet sun = new Planet("Sol", 0, 0, AngularDirection.STATIC, dayWeatherDTO.getDay());

        if (areInLine(ferengi, betasoide, vulcano)) {

            if (areInLine(ferengi, betasoide, sun)) {
                weatherStatus = WeatherStatus.SEQUIA;
            } else {
                weatherStatus = WeatherStatus.CONDICIONES_OPTIMAS;
            }
        } else if (isPlanetInsideTriangle(ferengi, betasoide, vulcano, sun)) {

            weatherStatus = WeatherStatus.LLUVIA;

            Double perimeter = new Triangle(ferengi.getCoordenates(), betasoide.getCoordenates(), vulcano.getCoordenates()).getPerimeter();

            log.system().info("[perimeter calculated: {}, for coordenates {} - {} - {}]", perimeter, ferengi.getCoordenates(), betasoide.getCoordenates(), vulcano.getCoordenates());

            dayWeatherDTO.setPerimeter(perimeter);

        }


        dayWeatherDTO.setWeather(weatherStatus);

        return dayWeatherDTO;
    }

    /**
     * Validat if three planets are inline
     *
     * @param planetA
     * @param planetB
     * @param planetC
     * @return
     */
    public boolean areInLine(Planet planetA, Planet planetB, Planet planetC) {

        log.system().info("[Calculate Line using pointA: {}, pointB: {}]", planetA.getCoordenates(), planetC.getCoordenates());

        Line line = new Line(planetA.getCoordenates(), planetC.getCoordenates());

        return line.pointBelongsToThisLine(planetB.getCoordenates());
    }

    /**
     * Validate if some planet is inside a Triangle area
     *
     * @param planetA
     * @param planetB
     * @param planetC
     * @param planetD
     * @return
     */
    public boolean isPlanetInsideTriangle(Planet planetA, Planet planetB, Planet planetC, Planet planetD) {

        Boolean isInside = Boolean.FALSE;

        Double totalArea = new Triangle(planetA.getCoordenates(), planetB.getCoordenates(), planetC.getCoordenates()).getArea();

        if (!totalArea.equals(0d)) {

            Double area1 = new Triangle(planetC.getCoordenates(), planetB.getCoordenates(), planetD.getCoordenates()).getArea();
            Double area2 = new Triangle(planetA.getCoordenates(), planetC.getCoordenates(), planetD.getCoordenates()).getArea();
            Double area3 = new Triangle(planetA.getCoordenates(), planetB.getCoordenates(), planetD.getCoordenates()).getArea();

            isInside = (area1 + area2 + area3) == totalArea;
        }

        return isInside;
    }


}
