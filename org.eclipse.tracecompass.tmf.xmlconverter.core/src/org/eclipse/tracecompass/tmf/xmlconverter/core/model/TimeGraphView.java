//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.16 at 02:05:35 PM EDT 
//


package org.eclipse.tracecompass.tmf.xmlconverter.core.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Declares a data-driven time graph view, which defines how the view will display the results of an analysis. For now, only state system analysis are supported by this view.
 * 
 * <p>Java class for timeGraphView complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="timeGraphView">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="head" type="{}headOutput" minOccurs="0"/>
 *         &lt;element name="definedValue" type="{}definedValue" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="entry" type="{}viewEntry" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "timeGraphView", propOrder = {
    "head",
    "definedValue",
    "entry"
})
public class TimeGraphView {

    protected HeadOutput head;
    protected List<DefinedValue> definedValue;
    protected List<ViewEntry> entry;
    @XmlAttribute(name = "id", required = true)
    protected String id;

    /**
     * Gets the value of the head property.
     * 
     * @return
     *     possible object is
     *     {@link HeadOutput }
     *     
     */
    public HeadOutput getHead() {
        return head;
    }

    /**
     * Sets the value of the head property.
     * 
     * @param value
     *     allowed object is
     *     {@link HeadOutput }
     *     
     */
    public void setHead(HeadOutput value) {
        this.head = value;
    }

    /**
     * Gets the value of the definedValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the definedValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDefinedValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DefinedValue }
     * 
     * 
     */
    public List<DefinedValue> getDefinedValue() {
        if (definedValue == null) {
            definedValue = new ArrayList<DefinedValue>();
        }
        return this.definedValue;
    }

    /**
     * Gets the value of the entry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ViewEntry }
     * 
     * 
     */
    public List<ViewEntry> getEntry() {
        if (entry == null) {
            entry = new ArrayList<ViewEntry>();
        }
        return this.entry;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
