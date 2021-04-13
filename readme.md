# MiniJUnit Test Project (2019)

## What I was asked to implement

Implement your own simple test system like JUnit. It should have only `@Test` annotation and should be able to run all
the `public void` methods annotated with the annotation.

## Implementation explanations

- Implemented expected exception handling like it is in JUnit 4.
- Made `Assertions` class methods static because it's easier to use (see `CalculatorTest`).
- It took 30 minutes to implement it.

## Possible further improvements

- Add test classes parsing (don't run them manually).
- Cover `Assertions` and `TestRunner` with tests themselves.
- Check test methods signatures and report if it's wrong.
- Add more overloaded `Assertions.assertEquals` methods.
- Improve expected exceptions awaiting like it's done in JUnit5.
- And so on and so forth... There is enormous room for improvements, but it's useless. Just use JUnit :)
