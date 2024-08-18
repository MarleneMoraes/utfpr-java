/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.model;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;


/**
 *
 * @author alexandrelerario
 */
@XmlRootElement(name = "lista_alunos")
public class Alunos implements java.io.Serializable{
    private java.util.ArrayList<Aluno> laluno;

    public Alunos(ArrayList<Aluno> laluno) {
        this.laluno = laluno;
    }

    public Alunos() {
    }

   
    @XmlElement(name="aluno")
    public ArrayList<Aluno> getLaluno() {
        return laluno;
    }

    public void setLaluno(ArrayList<Aluno> laluno) {
        this.laluno = laluno;
    }

}
