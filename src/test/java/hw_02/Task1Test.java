package hw_02;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Task1Test {

    private static final BaseJavaTasks tasks = new BaseJavaTasks();
    private static final Random random = new Random();

    private void printStart() {
        System.out.println("========================Test method start");
    }

    private void printEnd() {
        System.out.println("Test method end");
        System.out.println("========================");
    }

    @Test
    @Order(1)
    void testIsEven() {
        printStart();
        int n = random.nextInt(100) + 1;
        boolean result = tasks.isEven(n);

        assertEquals(n % 2 == 0, result);
        printEnd();
    }

    @RepeatedTest(20)
    @Order(2)
    void testCheckAccess(RepetitionInfo repetitionInfo) {
        if (repetitionInfo.getCurrentRepetition() == 1) {
            printStart();
        }

        int age = random.nextInt(100);
        String result = tasks.checkAccess(age);
        assertNotNull(result);

        if (repetitionInfo.getCurrentRepetition() == 20) {
            printEnd();
        }
    }

    @ParameterizedTest
    @MethodSource("randomScores")
    @Order(3)
    void testGetGrade(int score) {
        printStart();
        String result = tasks.getGrade(score);
        assertNotNull(result);
        printEnd();
    }

    static Stream<Integer> randomScores() {
        return IntStream.range(0, 10)
                .mapToObj(i -> random.nextInt(101)); // 0-100
    }
}