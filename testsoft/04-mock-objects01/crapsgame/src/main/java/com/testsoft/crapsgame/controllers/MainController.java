package com.testsoft.crapsgame.controllers;

import java.util.Scanner;

import com.testsoft.crapsgame.domain.Craps;

public class MainController {
    public static void main(String[] args) {
        Craps craps = new Craps();
        int vencedor = jogada(craps);
        resultado(vencedor);
    }
    
    private static int jogada(Craps craps) {
        try (Scanner scan = new Scanner(System.in)) {
            while (!craps.isFimDeJogo()) {
                System.out.println("Digite enter para continuar.");
                scan.nextLine();
                int soma = craps.rolarDados();
                System.out.println("Soma: " + soma);
            }
            return craps.getVencedor();
        } catch (Exception e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
            return -1;
        }
    }

    private static void resultado(int vencedor) {
        switch (vencedor) {
            case 1:
                System.out.println("Você ganhou!");
                break;
            case 2:
                System.out.println("A banca ganhou!");
                break;
            default:
                throw new IllegalStateException("Estado inválido: " + vencedor);
        }
    }    
}
