package br.edu.utfpr.javai.atividadeiv.java.entities;

public final class Passeio extends Veiculo implements Calcular {
    private int qtdPassageiros;

    public Passeio() {
        this.qtdPassageiros = 0;
    }

    public Passeio(String placa, String marca, String modelo, String cor, float velocMax, int qtdRodas, Motor motor,
            int qtdPassageiros) {
        super(placa, marca, modelo, cor, velocMax, qtdRodas, new Motor(motor.getPotencia(), motor.getQtdPist()));
        this.qtdPassageiros = qtdPassageiros;
    }

    public int getQtdPassageiros() {
        return qtdPassageiros;
    }

    public void setQtdPassageiros(int qtdPassageiros) {
        this.qtdPassageiros = qtdPassageiros;
    }

    // Calculo da Velocidade para Veiculos de Passeio
    @Override
    public float calcVel(float velocMax) {
        return velocMax * 1000;
    }

    // Retorna a soma de caracteres dos atributos String
    @Override
    public int calcular() {
        int qtdCaracteres = 0;

        qtdCaracteres += getPlaca().length();
        qtdCaracteres += getMarca().length();
        qtdCaracteres += getModelo().length();
        qtdCaracteres += getCor().length();

        return qtdCaracteres;
    }

    @Override
    public String toString() {
        return "Marca := " + getMarca() + "\nModelo := " + getModelo() + "\nPlaca : " + getPlaca()
                + "\nVelocidade Maxima := " + calcVel(getVelocMax()) + "\nQtd Passageiros := " 
                + qtdPassageiros + motor.toString() + "\nNúmero de Letras := " + calcular();
    }
}