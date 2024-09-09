package br.utfpr.webservice;

import javax.jws.WebService;

@WebService(endpointInterface = "br.utfpr.webservice.CalculatorService")
public class CalculatorServiceImpl implements CalculatorService {
  @Override
  public int somar(int v1, int v2) {
    return v1 + v2;
  }
}
