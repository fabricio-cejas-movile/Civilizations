package com.mercadolibre.ingreso.util;

import com.mercadolibre.ingreso.entity.Coordenates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@Getter
@Setter
@ToString
public class Triangle {

    private Double area;

    private Double perimeter;

    public Triangle(Coordenates pointA, Coordenates pointB, Coordenates pointC) {
        this.area = calculateArea(pointA, pointB, pointC);
    }

    private Double calculateArea(Coordenates pointA, Coordenates pointB, Coordenates pointC) {

        double firstTerm = (pointA.getPosX() * pointB.getPosY())
                + (pointB.getPosX() * pointC.getPosY()) + (pointC.getPosX() * pointA.getPosY());

        double secondTerm = (pointA.getPosX() * pointC.getPosY())
                + (pointC.getPosX() * pointB.getPosY()) + (pointB.getPosX() * pointA.getPosY());

        return Math.round(((firstTerm - secondTerm) / 2) * 100d) * 100d;
    }

    private Double calculatePerimeter() {
        return null;
    }
}
