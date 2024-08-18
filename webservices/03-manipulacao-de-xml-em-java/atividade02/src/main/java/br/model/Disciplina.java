/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author alexandrelerario
 */
@XmlRootElement(name = "disciplina")
public class Disciplina {
    private String nome;

    public Disciplina() {
    }
    

    public Disciplina(String nome) {
        this.nome = nome;
    }
  
    
    @XmlElement(name="nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
