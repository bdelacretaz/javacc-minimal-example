package ch.x42.javacc.samples.calculator.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import ch.x42.javacc.samples.calculator.Calculator;
import ch.x42.javacc.samples.calculator.ParseException;

public class CalculatorTest {
    private void assertResult(String input, int expected) throws ParseException, IOException {
        final Reader r = new StringReader(input + "\n");
        try {
            assertEquals(expected, new Calculator(r).parseOneLine());
        } finally {
            r.close();
        }
    }
    
    @Test
    public void add() throws ParseException, IOException {
        assertResult("2+2", 4);
    }
    
    @Test
    public void unary() throws ParseException, IOException {
        assertResult("-2 + 5", 3);
    }
    
    @Test
    public void whitespace() throws ParseException, IOException {
        assertResult("\t\r- 12 \t + -25\t", -37);
    }
    
    @Test
    public void empty() throws ParseException, IOException {
        assertResult("", 0);
    }
}