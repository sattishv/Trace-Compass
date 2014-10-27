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
 * Define a path to entries in the view. If this element is at the top level, the base path to reach the entry is the root of the state system. Otherwise, it will use the parent element's corresponding attribute as the base. Each view entry element corresponds to a time graph view entry that will actually be displayed.
 * 
 * <p>Java class for viewEntry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="viewEntry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="display" type="{}viewStateAttribute" minOccurs="0"/>
 *         &lt;element name="id" type="{}viewStateAttribute" minOccurs="0"/>
 *         &lt;element name="parent" type="{}viewStateAttribute" minOccurs="0"/>
 *         &lt;element name="name" type="{}viewStateAttribute" minOccurs="0"/>
 *         &lt;element name="entry" type="{}viewEntry" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "viewEntry", propOrder = {
    "display",
    "id",
    "parent",
    "name",
    "entry"
})
public class ViewEntry {

    protected ViewStateAttribute display;
    protected ViewStateAttribute id;
    protected ViewStateAttribute parent;
    protected ViewStateAttribute name;
    protected List<ViewEntry> entry;
    @XmlAttribute(name = "path", required = true)
    protected String path;

    /**
     * Gets the value of the display property.
     * 
     * @return
     *     possible object is
     *     {@link ViewStateAttribute }
     *     
     */
    public ViewStateAttribute getDisplay() {
        return display;
    }

    /**
     * Sets the value of the display property.
     * 
     * @param value
     *     allowed object is
     *     {@link ViewStateAttribute }
     *     
     */
    public void setDisplay(ViewStateAttribute value) {
        this.display = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link ViewStateAttribute }
     *     
     */
    public ViewStateAttribute getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link ViewStateAttribute }
     *     
     */
    public void setId(ViewStateAttribute value) {
        this.id = value;
    }

    /**
     * Gets the value of the parent property.
     * 
     * @return
     *     possible object is
     *     {@link ViewStateAttribute }
     *     
     */
    public ViewStateAttribute getParent() {
        return parent;
    }

    /**
     * Sets the value of the parent property.
     * 
     * @param value
     *     allowed object is
     *     {@link ViewStateAttribute }
     *     
     */
    public void setParent(ViewStateAttribute value) {
        this.parent = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link ViewStateAttribute }
     *     
     */
    public ViewStateAttribute getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link ViewStateAttribute }
     *     
     */
    public void setName(ViewStateAttribute value) {
        this.name = value;
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
     * Gets the value of the path property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the value of the path property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPath(String value) {
        this.path = value;
    }

}
