package com.testsoft.crapsgame.domain;

public class Craps {
    private int soma, ponto, vencedor;
    private boolean primeiraRodada = true;
    private Dado dado = new Dado();

    public boolean isFimDeJogo() {
        return vencedor == 1 || vencedor == 2;
    }

    public int getVencedor() {
        return vencedor;
    }

    void setDado(Dado novoDado) {
        this.dado = novoDado;
    }

    public int rolarDados() {
        soma = dado.rolar() + dado.rolar();
        if (primeiraRodada) {
            switch (soma) {
                case 7:
                case 11:
                    vencedor = 1;
                    break;
                case 2:
                case 3:
                case 12:
                    vencedor = 2;
                    break;
                default:
                    ponto = soma;
                    break;
            }
            primeiraRodada = false;
        } else {
            if (soma == ponto) {
                vencedor = 1;
            } else if (soma == 7) {
                vencedor = 2;
            }
        }
        return soma;
    }
}