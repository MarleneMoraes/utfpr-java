package br.restserver.resources;

import br.data.Cidade;
import br.data.CrudCidade;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;



/**
 *
 * @author 
 */
@Path("rest")
public class JakartaEE9Resource {
    
    @GET
    @Path("ola/{nome}")
    @Produces(MediaType.TEXT_PLAIN)
    public String oi(@PathParam("nome") String nome){
        return "ola, seja bem vindo " + nome;
    }
    
    
    
    
    // este metodo exige parametros
    @PUT
    @Path("cidade/{id}/{nome}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(@PathParam("id") int id,@PathParam("nome") String nome){
        System.out.println("Adicionar uma nova cidade com parametros.......");
        Cidade cid = new Cidade();
        cid.setId(id);
        cid.setNome(nome);
        new CrudCidade().add(cid);
    }
    
    //este metodo exige o json
    @PUT
    @Path("cidade")
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(JsonObject jsonData){
        System.out.println("Adicionar uma nova cidade com json");
        Cidade cid = new Cidade();
        cid.setId(jsonData.getInt("codigo"));
        cid.setNome(jsonData.getString("nome"));
        new CrudCidade().add(cid);
    }
    
    //este metodo exige o objeto cidade
    @PUT
    @Path("cidadeobj")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addObj(Cidade cid){
        System.out.println("Adicionar uma nova cidade com objeto");
        new CrudCidade().add(cid);
    }
    
    
    //obter lista de cidades
    @GET
    @Path("cidades")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Cidade> getAllCidades(){
        ArrayList<Cidade> lista = new CrudCidade().getAll();
        return lista;
    }
    
   
    //obter uma cidade
    @GET
    @Path("cidade/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cidade getCidade(@PathParam("id") int id){
        return new CrudCidade().getCidade(id);
    }
    
}
