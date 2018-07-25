package com.mercadolibre.ingreso.service;

import com.mercadolibre.ingreso.commons.Logs;
import com.mercadolibre.ingreso.entity.*;
import com.mercadolibre.ingreso.util.Line;
import com.mercadolibre.ingreso.util.Triangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@Service
public class WeatherForecast {

    @Autowired
    private Logs log;

    public DayWeatherDTO calculateWeatherOfDay(DayWeatherDTO dayWeatherDTO) {

        WeatherStatus weatherStatus = WeatherStatus.NORMAL;

        Planet ferengi = new Planet("Ferengi", 500, 1, AngularDirection.CLOCKWISE, dayWeatherDTO.getDay());

        Planet betasoide = new Planet("Betasoide", 2000, 3, AngularDirection.CLOCKWISE, dayWeatherDTO.getDay());

        Planet vulcano = new Planet("Vulcano", 1000, 5, AngularDirection.AGAINST_CLOCK, dayWeatherDTO.getDay());

        Planet sun = new Planet("Sol", 0, 0, AngularDirection.STATIC, dayWeatherDTO.getDay());

        log.system().info("[Calculate Line using pointA: {}, pointB: {}]", ferengi.getCoordenates(), vulcano.getCoordenates());

        Line line = new Line(ferengi.getCoordenates(), vulcano.getCoordenates());

        if (areInLine(betasoide.getCoordenates(), line)) {

            if (areInLine(sun.getCoordenates(), line)) {
                weatherStatus = WeatherStatus.SEQUIA;
            } else {
                weatherStatus = WeatherStatus.CONDICIONES_OPTIMAS;
            }
        } else {

            Double totalArea = new Triangle(ferengi.getCoordenates(), betasoide.getCoordenates(), vulcano.getCoordenates()).getArea();

            Double area1 = new Triangle(sun.getCoordenates(), betasoide.getCoordenates(), vulcano.getCoordenates()).getArea();
            Double area2 = new Triangle(ferengi.getCoordenates(), sun.getCoordenates(), vulcano.getCoordenates()).getArea();
            Double area3 = new Triangle(ferengi.getCoordenates(), betasoide.getCoordenates(), sun.getCoordenates()).getArea();

            if ((area1 + area2 + area3) == totalArea) {
                weatherStatus = WeatherStatus.LLUVIA;

                Double perimeter = new Triangle(ferengi.getCoordenates(), betasoide.getCoordenates(), vulcano.getCoordenates()).getPerimeter();

                log.system().info("[perimeter calculated: {}, for coordenates {} - {} - {}]", perimeter, ferengi.getCoordenates(), betasoide.getCoordenates(), vulcano.getCoordenates());

                dayWeatherDTO.setPerimeter(perimeter);
            }
        }



        dayWeatherDTO.setWeather(weatherStatus);

        return dayWeatherDTO;
    }

    private boolean areInLine(Coordenates planetXY, Line line) {
        return line.pointBelongsToThisLine(planetXY);
    }
}
