package ru.dipech;

import ru.dipech.minijunit.Test;
import static ru.dipech.minijunit.Assertions.*;

public class CalculatorTest {

    private final Calculator calc = new Calculator();

    @Test
    public void sum() {
        assertEquals(4, calc.add(2, 2));
    }

    @Test
    public void subtract() {
        assertEquals(10, calc.subtract(20, 10));
    }

    @Test
    public void multiply() {
        assertEquals(12, calc.multiply(4, 3));
    }

    @Test
    public void divide() {
        assertEquals(5, calc.divide(10, 2));
    }

    @Test(expected = ArithmeticException.class)
    public void divideByZero() {
        calc.divide(10, 0);
    }

}
