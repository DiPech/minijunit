package ru.dipech.minijunit;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestRunner {
    public void run(Class<?> clazz) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        String className = clazz.getCanonicalName();
        stringBuilder.append("Testing class «").append(className).append("»\n");
        Method[] methods = clazz.getDeclaredMethods();
        Object testClass = clazz.getDeclaredConstructor().newInstance();
        String msgOffset = "  ";
        int pass = 0;
        int fail = 0;
        for (Method method : methods) {
            if (!method.isAnnotationPresent(Test.class)) {
                continue;
            }
            String methodName = method.getName();
            if (!Modifier.isPublic(method.getModifiers())) {
                System.out.println("Annotated method " + className + "::" + methodName + " should be public!\n");
                continue;
            }
            if (method.getParameters().length > 0) {
                System.out.println("Annotated method " + className + "::" + methodName + " shouldn't has parameters!\n");
                continue;
            }
            stringBuilder.append(msgOffset).append("Method «").append(methodName).append("» ");
            Test annotation = method.getAnnotation(Test.class);
            Class expectedException = annotation.expected();
            try {
                method.invoke(testClass);
                if (!expectedException.equals(Test.None.class)) {
                    throw new Exception("Expected exception «" + expectedException.getCanonicalName() + "» hasn't been thrown!");
                }
                pass++;
                stringBuilder.append("passed.");
            } catch (Exception e) {
                Throwable cause = e;
                if (cause.getCause() != null) {
                    cause = cause.getCause();
                }
                if (cause.getClass().equals(expectedException)) {
                    pass++;
                    stringBuilder.append("passed.");
                } else {
                    fail++;
                    stringBuilder.append("failed: ").append(cause.getMessage());
                }
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("Passed: ").append(pass).append("; ")
                .append("Failed: ").append(fail).append(".");
        System.out.println(stringBuilder.toString());
    }
}
