package hw_02;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    private static final BaseJavaTasks tasks = new BaseJavaTasks();
    private static final Random random = new Random();

    private void check(String testName, boolean condition) {
        if (condition) {
            System.out.println(testName + " TEST PASSED");
        } else {
            System.out.println(testName + " TEST FAILED");
        }
    }

    @Test
    void testIsEven_WithRandom() {
        int n = random.nextInt(100) + 1;
        boolean expected = (n % 2 == 0);
        boolean actual = tasks.isEven(n);
        check("isEven(" + n + ")", expected == actual);
    }

    @Test
    void testCheckAccess_Adult() {
        int age = 18 + random.nextInt(82);
        String actual = tasks.checkAccess(age);
        check("checkAccess(" + age + ") Adult", "Allowed".equals(actual));
    }

    @Test
    void testCheckAccess_Minor() {
        int age = random.nextInt(18);
        String actual = tasks.checkAccess(age);
        check("checkAccess(" + age + ") Minor", "Denied".equals(actual));
    }

    @Test
    void testGetGrade_A() {
        int score = 90 + random.nextInt(11);
        String actual = tasks.getGrade(score);
        check("getGrade(" + score + ") A", "A".equals(actual));
    }

    @RepeatedTest(5)
    void repeatedIsEven() {
        int n = random.nextInt(100) + 1;
        boolean expected = (n % 2 == 0);
        boolean actual = tasks.isEven(n);
        check("repeated isEven(" + n + ")", expected == actual);
    }

    @RepeatedTest(5)
    void repeatedCheckAccess() {
        int age = random.nextInt(100);
        String actual = tasks.checkAccess(age);
        String expected = (age >= 18) ? "Allowed" : "Denied";
        check("repeated checkAccess(" + age + ")", expected.equals(actual));
    }

    @RepeatedTest(5)
    void repeatedGetGrade_ValidRange() {
        int score = random.nextInt(101);
        String actual = tasks.getGrade(score);
        check("repeated getGrade(" + score + ") not null", actual != null);
    }

    @RepeatedTest(5)
    void repeatedIsEven_Zero() {
        boolean actual = tasks.isEven(0);
        check("repeated isEven(0)", actual == true);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void paramIsEven(int n) {
        boolean expected = (n % 2 == 0);
        boolean actual = tasks.isEven(n);
        check("param isEven(" + n + ")", expected == actual);
    }

    @ParameterizedTest
    @MethodSource("randomAges")
    void paramCheckAccess(int age) {
        String actual = tasks.checkAccess(age);
        String expected = (age >= 18) ? "Allowed" : "Denied";
        check("param checkAccess(" + age + ")", expected.equals(actual));
    }

    @ParameterizedTest
    @MethodSource("randomScores")
    void paramGetGrade(int score) {
        String actual = tasks.getGrade(score);
        check("param getGrade(" + score + ") not null", actual != null);
    }

    @ParameterizedTest
    @MethodSource("boundaryScores")
    void paramGetGrade_Boundaries(int score, String expectedGrade) {
        String actual = tasks.getGrade(score);
        check("param boundary getGrade(" + score + ")", expectedGrade.equals(actual));
    }

    static Stream<Integer> randomAges() {
        return Stream.generate(() -> random.nextInt(100)).limit(10);
    }

    static Stream<Integer> randomScores() {
        return Stream.generate(() -> random.nextInt(101)).limit(10);
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> boundaryScores() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(0, "F"),
                org.junit.jupiter.params.provider.Arguments.of(59, "F"),
                org.junit.jupiter.params.provider.Arguments.of(60, "D"),
                org.junit.jupiter.params.provider.Arguments.of(70, "C"),
                org.junit.jupiter.params.provider.Arguments.of(80, "B"),
                org.junit.jupiter.params.provider.Arguments.of(90, "A"),
                org.junit.jupiter.params.provider.Arguments.of(100, "A")
        );
    }
}