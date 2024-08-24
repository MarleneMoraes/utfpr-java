/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br;

import br.data.Cidade;
import br.rs.RestClient;

/**
 *
 * @author default
 */
public class Principal {
    public static void main(String[] args) {
        System.out.println("Inicio da aplicacao");
        RestClient rc = new RestClient();
        
        //adicionando cidades..
        System.out.println("Adicionando cidades");
        rc.add(1, "Assis");
        rc.addJson(2, "Cornelio procopio");
        
        Cidade cid = new Cidade();
        cid.setId(3);
        cid.setNome("Londrina");
        rc.addObj(cid);
        
        System.out.println("Listando cidades");
        for (Cidade cidade : rc.getCidades()) {
            System.out.println("###   ###");
            System.out.println(cidade.getId());
            System.out.println(cidade.getNome());
        }
        
        System.out.println("Obter uma cidade");
       
        cid = rc.getCidade(1);
        System.out.println(cid.getNome());
        rc.close();
        System.out.println("EOF");
    }
}