//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.16 at 02:05:35 PM EDT 
//


package org.eclipse.tracecompass.tmf.xmlconverter.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * Allows the composition of more than one conditional statements.
 * 
 * <p>Java class for conditionMultiple complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="conditionMultiple">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element name="condition" type="{}condition" minOccurs="0"/>
 *         &lt;element name="or" type="{}conditionMultiple" minOccurs="0"/>
 *         &lt;element name="and" type="{}conditionMultiple" minOccurs="0"/>
 *         &lt;element name="not" type="{}conditionSingle" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "conditionMultiple", propOrder = {
    "conditionAndOrAndAnd"
})
public class ConditionMultiple {

    @XmlElementRefs({
        @XmlElementRef(name = "or", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "not", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "condition", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "and", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> conditionAndOrAndAnd;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the conditionAndOrAndAnd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conditionAndOrAndAnd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConditionAndOrAndAnd().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Condition }{@code >}
     * {@link JAXBElement }{@code <}{@link ConditionMultiple }{@code >}
     * {@link JAXBElement }{@code <}{@link ConditionMultiple }{@code >}
     * {@link JAXBElement }{@code <}{@link ConditionSingle }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getConditionAndOrAndAnd() {
        if (conditionAndOrAndAnd == null) {
            conditionAndOrAndAnd = new ArrayList<JAXBElement<?>>();
        }
        return this.conditionAndOrAndAnd;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}