package com.testsoft.calculosalario.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.testsoft.calculosalario.Funcionario;

public class CalculadoraSalarioTest {
    @Test
    public void testCalcularSalarioDesenvolvedor() {
        Funcionario dev = new Funcionario("João", "joao@example.com", 5000, "DESENVOLVEDOR");
        CalculadoraSalario calculadora = new CalculadoraSalario();
        assertEquals(4000, calculadora.calcularSalarioLiquido(dev));
    }

    @Test
    public void testCalcularSalarioGerente() {
        Funcionario gerente = new Funcionario("Maria", "maria@example.com", 2500, "GERENTE");
        CalculadoraSalario calculadora = new CalculadoraSalario();
        assertEquals(2000, calculadora.calcularSalarioLiquido(gerente));
    }

    @Test
    public void testCalcularSalarioTestador() {
        Funcionario testador = new Funcionario("Carlos", "carlos@example.com", 550, "TESTADOR");
        CalculadoraSalario calculadora = new CalculadoraSalario();
        assertEquals(467.50, calculadora.calcularSalarioLiquido(testador));
    }
}
