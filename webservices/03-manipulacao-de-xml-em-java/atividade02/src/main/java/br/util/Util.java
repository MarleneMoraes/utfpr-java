/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.util;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author alexandrelerario
 */
public class Util {

    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
    final String lexnumbers = "12345674890";
    final java.util.Random rand = new java.util.Random();

    final Set<String> identifiers = new HashSet<String>();

    public String randomName() {
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(5) + 5;
            for (int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
        }
        return builder.toString();
    }

    public String randomRA() {
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            builder = builder.append("RA");
            for (int i = 0; i < 5; i++) {
                builder.append(lexnumbers.charAt(rand.nextInt(lexnumbers.length())));
            }
            if (identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }

}
