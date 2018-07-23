package com.mercadolibre.ingreso.util;

import com.mercadolibre.ingreso.entity.Coordenates;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
public class PositionCalculator {

    /**
     * Get coordenates
     *
     * @param day
     * @param velocity
     * @param distance
     * @param againstRelox
     * @return
     */
    public static Coordenates getCoordenates(Integer day, Integer velocity, Integer distance, boolean againstRelox) {

        double angleDay = againstRelox ? (double) 360 - (day * velocity) : (double) (day * velocity);

        double angleRadians = Math.toRadians(angleDay);

        Double posX = Math.round((Math.cos(angleRadians) * distance) * 100d) / 100d;

        Double posY = Math.round((Math.sin(angleRadians) * distance) * 100d) / 100d;

        return new Coordenates(posX, posY);
    }
}
