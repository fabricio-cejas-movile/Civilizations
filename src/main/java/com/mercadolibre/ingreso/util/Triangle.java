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

    private Coordenates pointA;

    private Coordenates pointB;

    private Coordenates pointC;

    private Double area;

    private Double perimeter;

    public Triangle(Coordenates pointA, Coordenates pointB, Coordenates pointC) {

        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;

        this.area = this.calculateArea();
        this.perimeter = this.calculatePerimeter();

    }

    /**
     * Given Three points, calculate area using:
     * [(x1 * y2) + (x2 * y3) + (x3 * y1)] - [(x1 * y3) + (x3 * y2) + (x2 * y1)]
     * --------------------------------------------------------------------------
     * 2
     *
     * @return
     */
    public Double calculateArea() {

        double firstTerm = (this.pointA.getPosX() * this.pointB.getPosY())
                + (this.pointB.getPosX() * pointC.getPosY()) + (this.pointC.getPosX() * this.pointA.getPosY());

        double secondTerm = (this.pointA.getPosX() * this.pointC.getPosY())
                + (this.pointC.getPosX() * this.pointB.getPosY()) + (this.pointB.getPosX() * this.pointA.getPosY());

        //return Math.round(((firstTerm - secondTerm) / 2) * 10000d) / 10000d;
        return ((firstTerm - secondTerm) / 2);
    }

    /**
     * Calculate perimeter, obtaining distance between pointA-pointB, pointA-pointC, pointB-pointC
     * for obtain distance squareRoot of ((x2 - x1)^2 + (x2 - y1)^2)
     *
     * @return
     */
    public Double calculatePerimeter() {

        Double distanceAB = Math.sqrt(Math.pow((this.pointB.getPosX() - this.pointA.getPosX()), 2d) + Math.pow((this.pointB.getPosY() - this.pointA.getPosY()), 2d));

        Double distanceAC = Math.sqrt(Math.pow((this.pointC.getPosX() - this.pointA.getPosX()), 2d) + Math.pow((this.pointC.getPosY() - this.pointA.getPosY()), 2d));

        Double distanceBC = Math.sqrt(Math.pow((this.pointC.getPosX() - this.pointB.getPosX()), 2d) + Math.pow((this.pointC.getPosY() - this.pointB.getPosY()), 2d));

        //return Math.round((distanceAB + distanceAC + distanceBC) * 10000d) / 10000d;
        return distanceAB + distanceAC + distanceBC;
    }
}
