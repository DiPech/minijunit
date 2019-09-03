package ru.dipech;

import ru.dipech.minijunit.Assertions;
import ru.dipech.minijunit.Test;

public class AnotherTest {

    @Test(expected = RuntimeException.class)
    public void testWillFailedBecauseExceptionWillNotThrown() {
        int a = 0;
    }

    @Test
    public void testWillFailedBecauseExceptionWillThrown() {
        throw new RuntimeException("Awesome runtime exception we've ever seen.");
    }

    @Test
    public void justSimpleTrueTest() {
        Assertions.assertTrue(true);
    }

    @Test
    public void justSimpleFalseTest() {
        Assertions.assertTrue(false);
    }

    @Test
    private void testHasBadAccessModifier() {
    }

    @Test
    public void testHasOneOrMoreParameter(int a, int b) {
    }

}
