package com.testsoft.identifier;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.testsoft.exceptions.InvalidIdentifierException;

public class IdentifierTest {

    @Test
    public void testValidIdentifier() {
        assertDoesNotThrow(() -> Identifier.isValidIdentifier("a1"));
    }

    @Test
    public void testInvalidIdentifierTooLong() {
        Exception exception = assertThrows(InvalidIdentifierException.class, () -> {
            Identifier.isValidIdentifier("A1b2C3d");
        });
        assertEquals("O identificador deve ter entre 1 e 6 caracteres.", exception.getMessage());
    }

    @Test
    public void testInvalidIdentifierFirstCharNotLetter() {
        Exception exception = assertThrows(InvalidIdentifierException.class, () -> {
            Identifier.isValidIdentifier("2B3");
        });
        assertEquals("O primeiro caractere deve ser uma letra.", exception.getMessage());
    }

    @Test
    public void testInvalidIdentifierContainsInvalidChar() {
        Exception exception = assertThrows(InvalidIdentifierException.class, () -> {
            Identifier.isValidIdentifier("Z-12");
        });
        assertEquals("O identificador deve conter apenas letras ou dígitos.", exception.getMessage());
    }
}