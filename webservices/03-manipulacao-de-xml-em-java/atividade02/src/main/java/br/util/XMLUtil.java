/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.SchemaOutputResolver;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import javax.xml.XMLConstants;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

/**
 *
 * @author alexandrelerario
 */
public class XMLUtil {

    public String convertObjectToXML(Object objeto, File file, Class classe) {
        try {

            JAXBContext context = JAXBContext.newInstance(classe);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(objeto, System.out); //imprime no console

            StringWriter sw = new StringWriter();

            m.marshal(objeto, sw);
            
            if (file != null) {
                m.marshal(objeto, file);
            }
            return sw.toString();
        } catch (Exception e) {
            System.out.println("ERRO:" + e.getMessage());
            return null;
        }
    }

    public Object convertXmlToObject(File filexml, File fileSchema, Class classe) {
        JAXBContext jaxbContext;

        try {

            jaxbContext = JAXBContext.newInstance(classe);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            if (fileSchema != null) {

                SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema alunosSchema = sf.newSchema(fileSchema);
                jaxbUnmarshaller.setSchema(alunosSchema);
            }

            Object objeto = (Object) jaxbUnmarshaller.unmarshal(filexml);
            return objeto;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //criar schema
    public String getSchema(Class classe) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(classe);
            MySchemaOutputResolver sor = new MySchemaOutputResolver();
            jaxbContext.generateSchema(sor);
            return sor.getSchema();
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }

        return null;
    }

    private static class MySchemaOutputResolver extends SchemaOutputResolver {

        public MySchemaOutputResolver() {
        }
        private StringWriter stringWriter = new StringWriter();

        public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
            StreamResult result = new StreamResult(stringWriter);
            result.setSystemId(suggestedFileName);
            return result;
        }

        public String getSchema() {
            return stringWriter.toString();
        }
    }
    
    public String OBJtoJSON(Object objeto, Class classe){
        //transforacao utilizando jackson
        String result="";
        try{
          ObjectMapper objectMapper = new ObjectMapper();       
          result = objectMapper.writeValueAsString(objeto);
        }catch(Exception e){
            System.out.println("ERRO:" + e.getMessage());
        }
        return result;
    }

}
