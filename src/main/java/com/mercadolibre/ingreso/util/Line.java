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
public class Line {

    private Double slope;

    private Double yIntercept;

    public Line(Coordenates pointA, Coordenates pointB) {
        this.slope = calculateSlope(pointA, pointB);
        this.yIntercept = calculateYIntercept(pointA, getSlope());
    }

    public Boolean pointBelongsToThisLine(Coordenates point) {
        return point.getPosY() == ((this.slope * point.getPosX()) + this.yIntercept);
    }

    private Double calculateSlope(Coordenates pointA, Coordenates pointB) {
        Double slope = (pointB.getPosY() - pointA.getPosY()) / (pointB.getPosX() - pointA.getPosX());
        return Math.round(slope * 100d) * 100d;
    }

    private Double calculateYIntercept(Coordenates pointA, Double slope) {
        Double yIntercept = pointA.getPosY() - (slope * pointA.getPosX());
        return Math.round(yIntercept * 100d) * 100d;
    }
}
