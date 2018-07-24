package com.mercadolibre.ingreso.util;

import com.mercadolibre.ingreso.entity.Coordenates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class LineTest {

    @Test
    public void calculateSlopeTest() {

        Coordenates pointA = new Coordenates(-86.82d, -492.4);

        Coordenates pointB = new Coordenates(-766.04d, 642.79d);

        Line line = new Line(pointA, pointB);
    }
}
