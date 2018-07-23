package com.mercadolibre.ingreso.service;

import com.mercadolibre.ingreso.entity.Coordenates;
import com.mercadolibre.ingreso.entity.WeatherStatus;
import com.mercadolibre.ingreso.util.Line;
import com.mercadolibre.ingreso.util.PositionCalculator;
import com.mercadolibre.ingreso.util.Triangle;
import lombok.AccessLevel;
import org.springframework.stereotype.Service;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@Service
public class WeatherForecast {

    @lombok.Getter
    @lombok.AllArgsConstructor(access = AccessLevel.PRIVATE)
    private enum Planets {
        FERENGI("Ferengi", 500, 1), BETASOIDE("Betasoide", 2000, 3), VULCANO("Vulcano", 1000, 5), SOL("Sol", 0, 0);

        private String name;

        private Integer distanceFromSun;

        private Integer angularVelocity;
    }

    public WeatherStatus calculateWeatherOfDay(Integer dayNumber) {

        WeatherStatus weatherStatus = WeatherStatus.NORMAL;

        Coordenates ferengiXY = PositionCalculator.getCoordenates(dayNumber, Planets.FERENGI.getAngularVelocity(), Planets.FERENGI.getDistanceFromSun(), Boolean.FALSE);

        Coordenates betasoideXY = PositionCalculator.getCoordenates(dayNumber, Planets.BETASOIDE.getAngularVelocity(), Planets.BETASOIDE.getDistanceFromSun(), Boolean.FALSE);

        Coordenates vulcanoXY = PositionCalculator.getCoordenates(dayNumber, Planets.VULCANO.getAngularVelocity(), Planets.VULCANO.getDistanceFromSun(), Boolean.TRUE);

        Coordenates sunXY = new Coordenates(0d, 0d);

        Line line = new Line(ferengiXY, vulcanoXY);

        if (areInLine(betasoideXY, line)) {

            if (areInLine(sunXY, line)) {
                weatherStatus = WeatherStatus.SEQUIA;
            } else {
                weatherStatus = WeatherStatus.CONDICIONES_OPTIMAS;
            }
        } else {

            Double totalArea = new Triangle(ferengiXY, betasoideXY, vulcanoXY).getArea();

            Double area1 = new Triangle(sunXY, betasoideXY, vulcanoXY).getArea();
            Double area2 = new Triangle(ferengiXY, sunXY, vulcanoXY).getArea();
            Double area3 = new Triangle(ferengiXY, betasoideXY, sunXY).getArea();

            if ((area1 + area2 + area3) == totalArea) {
                weatherStatus = WeatherStatus.LLUVIA;
            }
        }

        return weatherStatus;
    }

    private boolean areInLine(Coordenates planetXY, Line line) {
        return line.pointBelongsToThisLine(planetXY);
    }
}
