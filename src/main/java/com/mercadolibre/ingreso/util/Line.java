package com.mercadolibre.ingreso.util;

import com.mercadolibre.ingreso.entity.Coordenates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The formula of any given line its y = mx+b
 * where m is the slope, and b is the y-intercept.
 *
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@Getter
@Setter
@ToString
public class Line {

    private Coordenates pointA;

    private Coordenates pointB;

    private Double slope;

    private Double yIntercept;

    public Line(Coordenates pointA, Coordenates pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.slope = calculateSlope();
        this.yIntercept = calculateYIntercept();
    }

    public Boolean pointBelongsToThisLine(Coordenates point) {
        return point.getPosY() == ((this.slope * point.getPosX()) + this.yIntercept);
    }

    public Double calculateSlope() {
        Double slope = (this.pointB.getPosY() - this.pointA.getPosY()) / (this.pointB.getPosX() - this.pointA.getPosX());
        return Math.round(slope * 10000d) / 10000d;
    }

    public Double calculateYIntercept() {
        Double yIntercept = this.pointA.getPosY() - (this.slope * this.pointA.getPosX());
        return Math.round(yIntercept * 10000d) / 10000d;
    }
}
