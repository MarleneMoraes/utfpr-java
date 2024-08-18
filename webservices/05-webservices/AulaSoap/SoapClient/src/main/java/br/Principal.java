/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br;

/**
 *
 * @author default
 */
public class Principal {
    public static void main(String[] args) {
        System.out.println("####   Inicio #########");
        
        String soapEndpointUrl = "http://localhost:8080/SoapServer/SoapService?wsdl";
        String soapAction = "http://localhost:8080/SoapServer/SoapService";

        SoapClient sc = new SoapClient();
        sc.callSoapWebService(soapEndpointUrl, soapAction);

        System.out.println("Fim!");
    }
}
