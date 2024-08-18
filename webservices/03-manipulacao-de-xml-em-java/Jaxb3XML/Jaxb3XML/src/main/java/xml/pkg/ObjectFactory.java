//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v3.0.0 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2021.01.21 às 03:24:28 PM BRT 
//


package xml.pkg;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xml.pkg package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xml.pkg
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Chat }
     * 
     */
    public Chat createChat() {
        return new Chat();
    }

    /**
     * Create an instance of {@link Sessao }
     * 
     */
    public Sessao createSessao() {
        return new Sessao();
    }

    /**
     * Create an instance of {@link Participante }
     * 
     */
    public Participante createParticipante() {
        return new Participante();
    }

    /**
     * Create an instance of {@link Chat.Mensagem }
     * 
     */
    public Chat.Mensagem createChatMensagem() {
        return new Chat.Mensagem();
    }

}
