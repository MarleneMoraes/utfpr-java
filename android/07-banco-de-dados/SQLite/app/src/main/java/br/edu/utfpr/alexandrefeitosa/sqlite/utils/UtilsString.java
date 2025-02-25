package br.edu.utfpr.alexandrefeitosa.sqlite.utils;

public class UtilsString {

    public static boolean stringVazia(String texto){

        return texto == null || texto.trim().length() == 0;
    }
}