/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.rs;

import br.data.Cidade;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author default
 */
public class RestClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/RestProvider/resources/";

    public RestClient() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("rest");
    }

    public Response ping() throws ClientErrorException {
        Response resource = webTarget.request().get();
        return resource;
    }

    public String ola(String nome) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("ola/{0}", new Object[]{nome}));
        return resource.request().get(String.class);
    }

    //adicionar apenas com parametros
    public void add(int codigo, String nome) throws ClientErrorException {
        Response response = webTarget.path("cidade")
                .path("/" + codigo + "/" + nome)
                .request()
                .put(Entity.entity("", MediaType.APPLICATION_JSON));
    }

    // adicionar com json
    public void addJson(int codigo, String nome) throws ClientErrorException {
        JsonObject value = Json.createObjectBuilder()
                .add("codigo", codigo)
                .add("nome", nome)
                .build();

        Response response = webTarget.path("cidade")
                .request()
                .put(Entity.entity(value, MediaType.APPLICATION_JSON));

    }

//adicionar com objeto cidade
    public void addObj(Cidade cid) throws ClientErrorException {

        Response response = webTarget.path("cidadeobj")
                .request()
                .put(Entity.entity(cid, MediaType.APPLICATION_JSON));
    }

    //obter cidades
    public ArrayList<Cidade> getCidades() throws ClientErrorException {
        ArrayList resource = webTarget.path("cidades").request(MediaType.APPLICATION_JSON).get(ArrayList.class);
        //converter Arraylist de hashmap para ArrayList de Cidade
        ArrayList<Cidade> lcid = new ArrayList<Cidade>();
        for (Object object : resource) {
            HashMap hm = (HashMap) object;
            Cidade cid= new Cidade();
            cid.setId(((BigDecimal) hm.get("id")).intValue());
            cid.setNome((String) hm.get("nome"));
            lcid.add(cid);
        }
        return lcid;
    }
    
    //obter uma cidade
    public Cidade getCidade(int id) throws ClientErrorException {
        Cidade resource = webTarget.path("cidade")
                .path("/" + id)
                .request(MediaType.APPLICATION_JSON).get(Cidade.class);
        return resource;
    }

    public void close() {
        client.close();
    }

}
