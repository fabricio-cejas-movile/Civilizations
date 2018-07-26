package com.mercadolibre.ingreso.entity;

import lombok.Getter;
import lombok.ToString;

/**
 * This class use to manage Planet position in function of a day
 *
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@Getter
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
        this.coordenates = calculateCoordenates();
    }

    /**
     * Returns coordenates according with day, distanFromSun, angularDirection, angularVelocity
     *
     * @return Coordenates Object
     */
    public Coordenates calculateCoordenates() {

        double angleDay = this.angularDirection == AngularDirection.AGAINST_CLOCK ? (double) 360 - (day * this.angularVelocity) : (double) (day * this.angularVelocity);

        double angleRadians = Math.toRadians(angleDay);

        Double posX = Math.round((Math.cos(angleRadians) * this.distanceFromSun) * 100d) / 100d;

        Double posY = Math.round((Math.sin(angleRadians) * this.distanceFromSun) * 100d) / 100d;

        return new Coordenates(posX, posY);
    }

    public void setCoordenates(Coordenates coordenates) {
        this.coordenates = coordenates;
    }

    public void setDay(Integer day) {
        this.day = day;
        setCoordenates(calculateCoordenates());
    }
}
