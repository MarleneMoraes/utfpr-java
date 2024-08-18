//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v3.0.0 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2021.01.21 às 03:24:28 PM BRT 
//


package xml.pkg;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{}Chat" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "chat"
})
@XmlRootElement(name = "Sessao")
public class Sessao {

    @XmlElement(name = "Chat", required = true)
    protected List<Chat> chat;

    /**
     * Gets the value of the chat property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the chat property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChat().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Chat }
     * 
     * 
     */
    public List<Chat> getChat() {
        if (chat == null) {
            chat = new ArrayList<Chat>();
        }
        return this.chat;
    }

}
