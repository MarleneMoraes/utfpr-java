/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.restserver.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author alerario
 */
@Provider
public class JaxRsFilter implements ContainerRequestFilter {
    
    @Context
    private ResourceInfo resourceInfo;
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";

    //se utilizar soapui para testar, crie um header chamado Authorization
    //o header Authorization deve conter o valor user1:pass2
   
    public void filter(ContainerRequestContext requestContext) {
        System.out.println("Container request ====");
        //recupera os cabecalhos da requisicao        
        final MultivaluedMap<String, String> headers = requestContext.getHeaders();
        //recupera o cabecalho de autenticacao        
        final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
        //Bloquea o acesso caso nao haja informacao de autorizacao        
        if (authorization == null || authorization.isEmpty()) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity("Nao possui informacao de autenticacao! (adicione o header  Authorization = user1:pass2)").build());
            return;
        }

        //Recupera o usuario e senha codificados        
        final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
       
        //Decodifica-os        
        String usernameAndPassword;
        boolean autenticar = false;
        try {
            usernameAndPassword = new String(Base64.getDecoder().decode(Base64.getEncoder().encode(encodedUserPassword.getBytes("UTF-8"))));
            //Separa os tokens          
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();
            System.out.println(username);
            System.out.println(password);
       
            //nesse ponto, o username e password podem ser recuperados de um BD ou objeto de sessao        
            //eu usei valores fixos          
            if (username.equals("user1") && password.equals("pass2")) {
                autenticar = true;
            }
           
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Erro de codificacao");
            System.out.println(ex.getMessage());
        }
       
        if (!autenticar) {
            System.out.println("usuario e senha invalidos");
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("usuario e senha invalidos").build());
            
        } else {
            System.out.println("autenticado, requisicao sera processada.");
        }
    }
}