package br.edu.utfpr.javai.atividadeiii.java.entities;

public final class Carga extends Veiculo {
    private int cargaMax;
    private int tara;

    public Carga() {
        this.cargaMax = 0;
        this.tara = 0;
    }

    public Carga(String placa, String marca, String modelo, String cor, float velocMax, int qtdRodas, Motor motor,
            int cargaMax, int tara) {
        super(placa, marca, modelo, cor, velocMax, qtdRodas, new Motor(motor.getPotencia(), motor.getQtdPist()));
        this.cargaMax = cargaMax;
        this.tara = tara;
    }

    public int getCargaMax() {
        return cargaMax;
    }

    public void setCargaMax(int cargaMax) {
        this.cargaMax = cargaMax;
    }

    public int getTara() {
        return tara;
    }

    public void setTara(int tara) {
        this.tara = tara;
    }

    @Override
    public float calcVel(float velocMax) {
        return velocMax * 100000;
    }

    @Override
    public String toString() {
        return "Marca := " + getMarca() + "\nModelo := " + getModelo() + "\nPlaca := " + getPlaca()
                + "\nVelocidade Máxima := " + getVelocMax() + "\nTara: " + tara + "\nCarga Máxima := " + cargaMax
                + motor.toString();
    }
}