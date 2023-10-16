package br.edu.utfpr.javai.atividadeiii.java.entities;

public final class Passeio extends Veiculo {
    private int qtdPassageiros;

    public Passeio() {
        this.qtdPassageiros = 0;
    }

    public Passeio(String placa, String marca, String modelo, String cor, float velocMax, int qtdRodas, Motor motor, int qtdPassageiros) {
        super(placa, marca, modelo, cor, velocMax, qtdRodas, new Motor(motor.getPotencia(), motor.getQtdPist()));
        this.qtdPassageiros = qtdPassageiros;
    }

    public int getQtdPassageiros() {
        return qtdPassageiros;
    }

    public void setQtdPassageiros(int qtdPassageiros) {
        this.qtdPassageiros = qtdPassageiros;
    }

    @Override
    public float calcVel(float velocMax) {
        return velocMax * 1000;
    }

    @Override
    public String toString() {
        return "Marca := " + getMarca() + "\nModelo := " + getModelo() + "\nPlaca : " + getPlaca() + "\nVelocidade Maxima := "
                + getVelocMax() +"\nQtd Passageiros := " + qtdPassageiros + motor.toString();
    }
}