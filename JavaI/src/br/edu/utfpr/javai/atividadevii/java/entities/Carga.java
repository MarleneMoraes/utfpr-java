package br.edu.utfpr.javai.atividadevi.java.entities;

import br.edu.utfpr.javai.atividadeiv.java.entities.Calcular;

public final class Carga extends Veiculo implements Calcular {
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

    // Calculo da Velocidade para Veiculos de Carga 
    @Override
    public float calcVel(float velocMax) {
        return velocMax * 100000;
    }

    // Retorna a soma de todos os valores dos atributos numericos
    @Override
    public int calcular() {
    	int valoresNumericos = 0;
    	
    	valoresNumericos += getVelocMax();
    	valoresNumericos += getQtdRodas();
    	valoresNumericos += getMotor().getQtdPist();
    	valoresNumericos += getMotor().getPotencia();
    	valoresNumericos += getCargaMax();
    	valoresNumericos += getTara();
    	
    	return valoresNumericos;
    }

    @Override
    public String toString() {
        return "Marca := " + getMarca() + "\nModelo := " + getModelo() + "\nPlaca := " + getPlaca()
        + "\nVelocidade Máxima := " + calcVel(getVelocMax()) + "\nTara: " + tara 
        + "\nCarga Máxima := " + cargaMax + motor.toString() + "\nSomatório Numéricos := " + calcular();
    }
}