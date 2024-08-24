/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.data;

import lombok.Data;

/**
 *
 * @author default
 */
@Data
public class Cidade implements br.data.model.ICidade {
    private int id;
    private String nome;
}
