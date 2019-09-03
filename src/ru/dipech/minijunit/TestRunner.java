package ru.dipech.minijunit;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestRunner {
    private StringBuilder stringBuilder;

    public void run(Class<?> clazz) throws Exception {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Testing class «").append(clazz.getCanonicalName()).append("»\n");
        testMethods(clazz);
        System.out.println(stringBuilder.toString());
    }

    private void testMethods(Class<?> clazz) throws Exception {
        String className = clazz.getCanonicalName();
        Method[] methods = clazz.getDeclaredMethods();
        Object classObject = clazz.getDeclaredConstructor().newInstance();
        int passed = 0;
        int failed = 0;
        for (Method method : methods) {
            if (!method.isAnnotationPresent(Test.class)) {
                continue;
            }
            String methodName = method.getName();
            String classAndMethod = className + "." + methodName;
            if (!Modifier.isPublic(method.getModifiers())) {
                System.out.println("Annotated method " + classAndMethod + " should be public!\n");
                continue;
            }
            if (method.getParameters().length > 0) {
                System.out.println("Annotated method " + classAndMethod + " shouldn't has parameters!\n");
                continue;
            }
            stringBuilder.append("  ").append("Method «").append(methodName).append("» ");
            try {
                testMethod(method, classObject);
                passed++;
                stringBuilder.append("passed.");
            } catch (TestException e) {
                failed++;
                stringBuilder.append("failed: ").append(e.getMessage());
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("Passed: ").append(passed).append("; ")
                .append("Failed: ").append(failed).append(".");
    }

    private void testMethod(Method method, Object classObject) {
        Test annotation = method.getAnnotation(Test.class);
        Class expectedException = annotation.expected();
        try {
            method.invoke(classObject);
            if (!expectedException.equals(Test.None.class)) {
                throw new TestException("Expected exception «" + expectedException.getCanonicalName() +
                        "» hasn't been thrown!");
            }
        } catch (Exception e) {
            Throwable cause = e;
            if (cause.getCause() != null) {
                cause = cause.getCause();
            }
            if (!cause.getClass().equals(expectedException)) {
                throw new TestException(cause.getMessage());
            }
        }
    }
}
