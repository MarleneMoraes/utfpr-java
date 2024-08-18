/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br;

/**
 *
 * @author default
 */



import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.MimeHeaders;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;


public class SoapClient {

    public void callSoapWebService(String soapEndpointUrl, String soapAction) {
        try {
            // Criar conexao SOAP 
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Enviar SOAP Message para o server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);

            // Imprimir resposta
            System.out.println("Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();

            soapConnection.close();
        } catch (Exception e) {
            System.out.println("ERRO:");
            System.out.println(e.getMessage());
        }
    }

    private static SOAPMessage createSOAPRequest(String soapAction) throws Exception {

        //criar mensagem SOAP
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        //criar envelope SOAP
        createSoapEnvelope(soapMessage);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        //Exibir mensagem
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println("\n");

        return soapMessage;
    }

    private static void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        //verificar no wsdl o namespace utilizado
        String myNamespace = "ns2";
        String myNamespaceURI = "http://ws.br/";

        // Preencher SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

        // Preencher SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("hello", myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("name"); //o child name foi criado sem namespace
        soapBodyElem1.addTextNode("Alexandre");
    }
}
