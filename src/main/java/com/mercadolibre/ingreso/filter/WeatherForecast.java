package com.mercadolibre.ingreso.filter;

import com.mercadolibre.ingreso.entity.WeatherStatus;
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

        //TODO: calculate coordenates

        //TODO: validate if planets are inline whit/whitout sun

        //TODO: else planets make a triangle is the sun inside, and is triangle in his max perimeter

        return WeatherStatus.LLUVIA;
    }
}
