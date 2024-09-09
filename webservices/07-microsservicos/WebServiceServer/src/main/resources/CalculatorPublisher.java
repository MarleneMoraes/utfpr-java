package br.utfpr.webservice;

import javax.xml.ws.Endpoint;

public class CalculatorPublisher {
  public static void main(String[] args) {
    Endpoint.publish("http://localhost:8080/ws/calculator", new CalculatorServiceImpl());
  }
}
