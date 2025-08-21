package se.systementor.unittester.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {
    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    void testAddOperation() {
        // ASSERT
        int tal1 = 2;
        int tal2 = 3;
        String action = "add";
        //ACT
        int result = calculatorService.Calculate(tal1,tal2,action);
        assertEquals(5, result, "Addition should work correctly.");
    }

    @Test
    void testSubtractOperation() {
        assertEquals(-1, calculatorService.Calculate(2, 3, "subtract"), "Subtraction should work correctly.");
    }

    @Test
    void testMultiplyOperation() {
        assertEquals(6, calculatorService.Calculate(2, 3, "multiply"), "Multiplication should work correctly.");
    }

    @Test
    void testDivideOperation() {
        assertEquals(2, calculatorService.Calculate(6, 3, "divide"), "Division should work correctly.");
    }

    // Edge Cases
    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calculatorService.Calculate(10, 0, "divide"),
                "Division by zero should throw ArithmeticException.");
    }

    @Test
    void testNegativeNumbers() {
        assertEquals(-5, calculatorService.Calculate(-2, -3, "add"), "Addition with negative numbers should work.");
        assertEquals(1, calculatorService.Calculate(-2, -3, "subtract"), "Subtraction with negative numbers should work.");
        assertEquals(6, calculatorService.Calculate(-2, -3, "multiply"), "Multiplication with negative numbers should work.");
        assertEquals(0, calculatorService.Calculate(-2, -3, "divide"), "Division with negative numbers should work.");
    }

    // Invalid Input
    @Test
    void testInvalidAction() {
        assertThrows(IllegalArgumentException.class, () -> calculatorService.Calculate(2, 3, "invalid_action"),
                "Invalid action string should throw IllegalArgumentException.");
    }

    @Test
    void testNullAction() {
        assertThrows(NullPointerException.class, () -> calculatorService.Calculate(2, 3, null),
                "Null action string should throw a NullPointerException.");
    }
}