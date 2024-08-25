package com.testsoft.calculosalario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Funcionario {
    private String nome;
    private String email;
    private double salarioBase;
    private String cargo;
}
