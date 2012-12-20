//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.01.03 at 08:03:30 PM EST 
//


package cruise.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="TemplateClass" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TemplatePackage" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ParameterType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PackagePreffix" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ClassSuffix" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OutputName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OutputExtension" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OutputSubFolder" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AddClassNameToRoute" type="{http://www.w3.org/2001/XMLSchema}string" default="NO" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "GenerationUnit")
public class GenerationUnit {

    @XmlAttribute(name = "TemplateClass", required = true)
    protected String templateClass;
    @XmlAttribute(name = "TemplatePackage", required = true)
    protected String templatePackage;
    @XmlAttribute(name = "ParameterType", required = true)
    protected String parameterType;
    @XmlAttribute(name = "PackagePreffix")
    protected String packagePreffix;
    @XmlAttribute(name = "ClassSuffix")
    protected String classSuffix;
    @XmlAttribute(name = "OutputName")
    protected String outputName;
    @XmlAttribute(name = "OutputExtension")
    protected String outputExtension;
    @XmlAttribute(name = "OutputSubFolder")
    protected String outputSubFolder;
    @XmlAttribute(name = "AddClassNameToRoute")
    protected String addClassNameToRoute;

    /**
     * Gets the value of the templateClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateClass() {
        return templateClass;
    }

    /**
     * Sets the value of the templateClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateClass(String value) {
        this.templateClass = value;
    }

    /**
     * Gets the value of the templatePackage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplatePackage() {
        return templatePackage;
    }

    /**
     * Sets the value of the templatePackage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplatePackage(String value) {
        this.templatePackage = value;
    }

    /**
     * Gets the value of the parameterType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParameterType() {
        return parameterType;
    }

    /**
     * Sets the value of the parameterType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParameterType(String value) {
        this.parameterType = value;
    }

    /**
     * Gets the value of the packagePreffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackagePreffix() {
        return packagePreffix;
    }

    /**
     * Sets the value of the packagePreffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackagePreffix(String value) {
        this.packagePreffix = value;
    }

    /**
     * Gets the value of the classSuffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassSuffix() {
        return classSuffix;
    }

    /**
     * Sets the value of the classSuffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassSuffix(String value) {
        this.classSuffix = value;
    }

    /**
     * Gets the value of the outputName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputName() {
        return outputName;
    }

    /**
     * Sets the value of the outputName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputName(String value) {
        this.outputName = value;
    }

    /**
     * Gets the value of the outputExtension property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputExtension() {
        return outputExtension;
    }

    /**
     * Sets the value of the outputExtension property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputExtension(String value) {
        this.outputExtension = value;
    }

    /**
     * Gets the value of the outputSubFolder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputSubFolder() {
        return outputSubFolder;
    }

    /**
     * Sets the value of the outputSubFolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputSubFolder(String value) {
        this.outputSubFolder = value;
    }

    /**
     * Gets the value of the addClassNameToRoute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddClassNameToRoute() {
        if (addClassNameToRoute == null) {
            return "NO";
        } else {
            return addClassNameToRoute;
        }
    }

    /**
     * Sets the value of the addClassNameToRoute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddClassNameToRoute(String value) {
        this.addClassNameToRoute = value;
    }

}
