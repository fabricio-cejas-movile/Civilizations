package com.mercadolibre.ingreso.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@Getter
@Setter
@ToString
public class Planet {

    private String name;

    private Integer distanceFromSun;

    private Integer angularVelocity;

    private AngularDirection angularDirection;

    private Integer day;

    private Coordenates coordenates;

    public Planet(String name, Integer distanceFromSun, Integer angularVelocity, AngularDirection angularDirection, Integer day) {

        this.name = name;
        this.distanceFromSun = distanceFromSun;
        this.angularVelocity = angularVelocity;
        this.angularDirection = angularDirection;
        this.day = day;
    }

    /**
     * @return
     */
    public Coordenates getCoordenates() {

        double angleDay = this.angularDirection == AngularDirection.AGAINST_CLOCK ? (double) 360 - (day * this.angularVelocity) : (double) (day * this.angularVelocity);

        double angleRadians = Math.toRadians(angleDay);

        Double posX = Math.round((Math.cos(angleRadians) * this.distanceFromSun) * 10000d) / 10000d;

        Double posY = Math.round((Math.sin(angleRadians) * this.distanceFromSun) * 10000d) / 10000d;

        return new Coordenates(posX, posY);
    }
}
