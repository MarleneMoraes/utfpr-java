//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v3.0.0 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2021.01.21 às 03:24:28 PM BRT 
//


package xml.pkg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element ref="{}Participante" maxOccurs="2" minOccurs="2"/&gt;
 *         &lt;element name="Mensagem"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Data" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                   &lt;element name="Hora" type="{http://www.w3.org/2001/XMLSchema}time"/&gt;
 *                   &lt;element name="Corpo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
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
    "participante",
    "mensagem"
})
@XmlRootElement(name = "Chat")
public class Chat {

    @XmlElement(name = "Participante", required = true)
    protected List<Participante> participante;
    @XmlElement(name = "Mensagem", required = true)
    protected Chat.Mensagem mensagem;

    /**
     * Gets the value of the participante property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the participante property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParticipante().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Participante }
     * 
     * 
     */
    public List<Participante> getParticipante() {
        if (participante == null) {
            participante = new ArrayList<Participante>();
        }
        return this.participante;
    }

    /**
     * Obtém o valor da propriedade mensagem.
     * 
     * @return
     *     possible object is
     *     {@link Chat.Mensagem }
     *     
     */
    public Chat.Mensagem getMensagem() {
        return mensagem;
    }

    /**
     * Define o valor da propriedade mensagem.
     * 
     * @param value
     *     allowed object is
     *     {@link Chat.Mensagem }
     *     
     */
    public void setMensagem(Chat.Mensagem value) {
        this.mensagem = value;
    }


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
     *         &lt;element name="Data" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *         &lt;element name="Hora" type="{http://www.w3.org/2001/XMLSchema}time"/&gt;
     *         &lt;element name="Corpo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "data",
        "hora",
        "corpo"
    })
    public static class Mensagem {

        @XmlElement(name = "Data", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar data;
        @XmlElement(name = "Hora", required = true)
        @XmlSchemaType(name = "time")
        protected XMLGregorianCalendar hora;
        @XmlElement(name = "Corpo", required = true)
        protected String corpo;

        /**
         * Obtém o valor da propriedade data.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getData() {
            return data;
        }

        /**
         * Define o valor da propriedade data.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setData(XMLGregorianCalendar value) {
            this.data = value;
        }

        /**
         * Obtém o valor da propriedade hora.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getHora() {
            return hora;
        }

        /**
         * Define o valor da propriedade hora.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setHora(XMLGregorianCalendar value) {
            this.hora = value;
        }

        /**
         * Obtém o valor da propriedade corpo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCorpo() {
            return corpo;
        }

        /**
         * Define o valor da propriedade corpo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCorpo(String value) {
            this.corpo = value;
        }

    }

}
