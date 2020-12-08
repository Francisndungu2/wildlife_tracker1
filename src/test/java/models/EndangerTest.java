package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndangerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public Endanger setUpNewEndanger() {
        return new Endanger("Lion", "20", "weak", "River");
    }

    @Test
    public void getAnimal_name() {
        Endanger newEndanger = setUpNewEndanger();
        assertEquals("Lion", newEndanger.getAnimal_name());
    }

    @Test
    public void getAnimal_age() {
        Endanger newEndanger = setUpNewEndanger();
        newEndanger.getAnimal_age();
        assertEquals("20", newEndanger.getAnimal_age());

    }

    @Test
    public void getAnimal_health() {
        Endanger newEndanger = setUpNewEndanger();
        newEndanger.getAnimal_health();
        assertEquals("weak", newEndanger.getAnimal_health());
    }

    @Test
    public void getLocation() {
        Endanger newEndanger = setUpNewEndanger();
        newEndanger.getLocation();
        assertEquals("River", newEndanger.getLocation());
    }
}

