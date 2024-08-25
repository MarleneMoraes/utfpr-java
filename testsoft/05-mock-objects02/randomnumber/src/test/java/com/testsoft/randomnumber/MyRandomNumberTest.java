package com.testsoft.randomnumber;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.testsoft.randomnumber.exceptions.IntervaloInvalidoException;

public class MyRandomNumberTest {
    private MyRandomNumber myRandomNumber;

    @BeforeEach
    public void setUp() {
        myRandomNumber = new MyRandomNumber();
    }

    @Test
    public void testNextRandomNumberValidInterval() throws IntervaloInvalidoException {
        int begin = 1;
        int end = 10;
        int randomNumber = myRandomNumber.nextRandomNumber(begin, end);
        assertTrue(randomNumber >= begin && randomNumber <= end);
    }

    @Test
    public void testNextRandomNumberInvalidInterval() {
        int begin = 10;
        int end = 1;
        assertThrows(IntervaloInvalidoException.class, () -> {
            myRandomNumber.nextRandomNumber(begin, end);
        });
    }

    @Test
    public void testNextRandomNumberNegativeInterval() {
        int begin = -1;
        int end = 10;
        assertThrows(IntervaloInvalidoException.class, () -> {
            myRandomNumber.nextRandomNumber(begin, end);
        });
    }
}
