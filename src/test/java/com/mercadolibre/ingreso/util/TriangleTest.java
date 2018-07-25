package com.mercadolibre.ingreso.util;

import com.mercadolibre.ingreso.entity.Coordenates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class TriangleTest {

    private Double perimeterExpected = 23.5573d;

    @Test
    public void calculatePerimeterTest() {

        Coordenates coordenates1 = new Coordenates(-2d, 5d);
        Coordenates coordenates2 = new Coordenates(4d, 3d);
        Coordenates coordenates3 = new Coordenates(7d, -2d);

        Triangle t = new Triangle(coordenates1, coordenates2, coordenates3);

        Double perimeter = t.calculatePerimeter();

        assertEquals(perimeterExpected, perimeter);
    }
}
