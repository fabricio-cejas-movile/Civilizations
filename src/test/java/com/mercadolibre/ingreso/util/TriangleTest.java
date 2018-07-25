package com.mercadolibre.ingreso.util;

import com.mercadolibre.ingreso.entity.Coordenates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@RunWith(SpringRunner.class)
public class TriangleTest {

    private Double perimeterExpected = 23.5573d;

    private Double areaExpected = 7255d;

    private Double areaZeroExpected = 0d;

    @Test
    public void calculatePerimeterTest() {

        Coordenates coordenates1 = new Coordenates(-2d, 5d);
        Coordenates coordenates2 = new Coordenates(4d, 3d);
        Coordenates coordenates3 = new Coordenates(7d, -2d);

        Triangle t = new Triangle(coordenates1, coordenates2, coordenates3);

        Double perimeter = t.calculatePerimeter();

        assertEquals(perimeterExpected, perimeter);
    }

    @Test
    public void calculateArea() {

        Coordenates coordenates1 = new Coordenates(100d, 50d);
        Coordenates coordenates2 = new Coordenates(-90d, 70d);
        Coordenates coordenates3 = new Coordenates(-55.5d, -10d);

        Triangle t = new Triangle(coordenates1, coordenates2, coordenates3);

        Double area = t.calculateArea();

        assertEquals(areaExpected, area);

    }

    @Test
    public void calculateAreaZero() {

        Coordenates coordenates1 = new Coordenates(100d, 0d);
        Coordenates coordenates2 = new Coordenates(-90d, 0d);
        Coordenates coordenates3 = new Coordenates(-55.5d, 0d);

        Triangle t = new Triangle(coordenates1, coordenates2, coordenates3);

        Double area = t.calculateArea();

        assertEquals(areaZeroExpected, area);

    }
}