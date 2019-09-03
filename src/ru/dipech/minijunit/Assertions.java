package ru.dipech.minijunit;

public class Assertions {
    public static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            fail(expected, actual);
        }
    }

    private static void fail(Object expected, Object actual) {
        throw new AssertionError("Expected isn't equal actual: " + expected + ", " + actual);
    }
}
