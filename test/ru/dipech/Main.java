package ru.dipech;

import ru.dipech.minijunit.TestRunner;

public class Main {

    public static void main(String[] args) throws Exception {
        TestRunner runner = new TestRunner();
        runner.run(CalculatorTest.class);
        runner.run(AnotherTest.class);
    }
}
