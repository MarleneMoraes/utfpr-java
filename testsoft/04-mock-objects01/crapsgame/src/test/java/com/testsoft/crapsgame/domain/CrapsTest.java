package com.testsoft.crapsgame.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CrapsTest {

    @Test
    public void verificarDuasRolagensDeDado() {
        Dado dadoMock = Mockito.mock(Dado.class);

        Mockito.when(dadoMock.rolar()).thenReturn(1);

        Craps craps = new Craps();
        craps.setDado(dadoMock);
        craps.rolarDados();
        assertTrue(craps.isFimDeJogo());
        assertEquals(2, craps.getVencedor());
    }

    @Test
    public void verificarSeJogadorGanhaNaPrimeiraRolagem() {
        Dado dadoMock = Mockito.mock(Dado.class);
        Mockito.when(dadoMock.rolar()).thenReturn(5, 2);
        Craps craps = new Craps();
        craps.setDado(dadoMock);
        craps.rolarDados();
        assertTrue(craps.isFimDeJogo());
        assertEquals(1, craps.getVencedor());
    }

    @Test
    public void verificarSeJogadorPerdeNaTerceiraRolagem() {
        Dado dadoMock = Mockito.mock(Dado.class);
        Craps craps = new Craps();
        craps.setDado(dadoMock);
        Mockito.when(dadoMock.rolar()).thenReturn(3, 5);
        craps.rolarDados();
        assertFalse(craps.isFimDeJogo());
        Mockito.when(dadoMock.rolar()).thenReturn(6, 5);
        craps.rolarDados();
        assertFalse(craps.isFimDeJogo());
        Mockito.when(dadoMock.rolar()).thenReturn(5, 2);
        craps.rolarDados();
        assertTrue(craps.isFimDeJogo());
        assertEquals(2, craps.getVencedor());
    }

    @Test
    public void verificarSeJogadorGanhaNaSegundaRolagem() {
        Dado dadoMock = Mockito.mock(Dado.class);
        Craps craps = new Craps();
        craps.setDado(dadoMock);
        Mockito.when(dadoMock.rolar()).thenReturn(1, 3);
        craps.rolarDados();
        assertFalse(craps.isFimDeJogo());
        Mockito.when(dadoMock.rolar()).thenReturn(2, 2);
        craps.rolarDados();
        assertTrue(craps.isFimDeJogo());
        assertEquals(1, craps.getVencedor());
    }

}
