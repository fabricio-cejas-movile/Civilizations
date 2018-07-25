package com.mercadolibre.ingreso.util;

import com.mercadolibre.ingreso.entity.AngularDirection;
import com.mercadolibre.ingreso.entity.Coordenates;
import com.mercadolibre.ingreso.entity.Planet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
public class PlanetTest {

    /**
     * We assume that angle, in day 45 with angular velocity is 1, will be 45°
     * So sin and cos are equals and then value y,x are equals too
     */
    @Test
    public void getCoordenatesWithVelocity1Test() {

        Planet planet = new Planet("X", 500, 1, AngularDirection.CLOCKWISE, 45);

        Coordenates coordenates = planet.getCoordenates();

        assertEquals(coordenates.getPosX(), coordenates.getPosY());

    }

    /**
     * We assume that angle, in day 9 with angular velocity is 5, will be 45°
     * So sin and cos are equals and then value y,x are equals too
     */
    @Test
    public void getCoordenatesWithVelocity5Test() {
        Planet planet = new Planet("X", 500, 5, AngularDirection.CLOCKWISE, 9);

        Coordenates coordenates = planet.getCoordenates();

        assertEquals(coordenates.getPosX(), coordenates.getPosY());
    }

    @Test
    public void getCoordenatesWith90gradesTest() {

        Planet planet = new Planet("X", 500, 1, AngularDirection.CLOCKWISE, 90);

        Coordenates coordenates = planet.getCoordenates();

        assertNotEquals(coordenates.getPosX(), coordenates.getPosY());

        Double expectedY = 500d;

        Double expectedX = 0d;

        assertEquals(expectedY, coordenates.getPosY());

        assertEquals(expectedX, coordenates.getPosX());
    }

    @Test
    public void getCoordenatesWith270gradesTest() {

        Planet planet = new Planet("X", 500, 1, AngularDirection.AGAINST_CLOCK, 90);

        Coordenates coordenates = planet.getCoordenates();

        assertNotEquals(coordenates.getPosX(), coordenates.getPosY());

        Double expectedY = -500d;

        Double expectedX = 0d;

        assertEquals(expectedY, coordenates.getPosY());

        assertEquals(expectedX, coordenates.getPosX());
    }
}
