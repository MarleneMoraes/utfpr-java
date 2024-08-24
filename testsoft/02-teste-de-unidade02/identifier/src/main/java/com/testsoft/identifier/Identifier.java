package com.testsoft.identifier;

import com.testsoft.exceptions.InvalidIdentifierException;

public class Identifier {
    public static boolean isValidIdentifier(String identifier) throws InvalidIdentifierException {
        if (identifier == null || identifier.length() < 1 || identifier.length() > 6) {
            throw new InvalidIdentifierException("O identificador deve ter entre 1 e 6 caracteres.");
        }

        if (!Character.isLetter(identifier.charAt(0))) {
            throw new InvalidIdentifierException("O primeiro caractere deve ser uma letra.");
        }
        
        for (char c : identifier.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                throw new InvalidIdentifierException("O identificador deve conter apenas letras ou dígitos.");
            }
        }
        return true;
    }
}