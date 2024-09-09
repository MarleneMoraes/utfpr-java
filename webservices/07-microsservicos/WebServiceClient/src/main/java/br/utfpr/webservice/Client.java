package br.utfpr.webservice;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class Client {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/CalculatorService?wsdl");
        QName qname = new QName("http://server/", "CalculadoraService");
        Service service = Service.create(url, qname);
        CalculatorService calculator = service.getPort(CalculatorService.class);
        int result = calculator.add(5, 3);
        System.out.println("Resultado: " + result);
    }
}
