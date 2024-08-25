package com.testsoft.randomnumber;

import java.util.Random;

import com.testsoft.randomnumber.exceptions.IntervaloInvalidoException;

public class MyRandomNumber {
    private Random random = new Random();
    private int lastNumber = Integer.MIN_VALUE;

    public int nextRandomNumber(int begin, int end) throws IntervaloInvalidoException {
        if (begin >= end || begin < 0 || end < 0) {
            throw new IntervaloInvalidoException("Intervalo inválido");
        }

        int randomNumber;
        do {
            randomNumber = random.nextInt(end - begin + 1) + begin;
        } while (randomNumber == lastNumber);

        lastNumber = randomNumber;
        return randomNumber;
    }
}
