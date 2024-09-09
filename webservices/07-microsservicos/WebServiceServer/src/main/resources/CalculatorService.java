package br.utfpr.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface CalculatorService {
  @WebMethod
  int add(int v1, int v2);
}
