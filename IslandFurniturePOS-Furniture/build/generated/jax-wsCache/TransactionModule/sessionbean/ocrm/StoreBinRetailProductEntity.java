
package sessionbean.ocrm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for storeBinRetailProductEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="storeBinRetailProductEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="isDeleted" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="retailProduct" type="{http://OCRM.SessionBean/}storeRetailProductEntity" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="swe" type="{http://OCRM.SessionBean/}storeWarehouseBinEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "storeBinRetailProductEntity", propOrder = {
    "id",
    "isDeleted",
    "quantity",
    "retailProduct",
    "status",
    "swe"
})
public class StoreBinRetailProductEntity {

    protected Long id;
    protected Boolean isDeleted;
    protected Double quantity;
    protected StoreRetailProductEntity retailProduct;
    protected Integer status;
    protected StoreWarehouseBinEntity swe;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the isDeleted property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsDeleted() {
        return isDeleted;
    }

    /**
     * Sets the value of the isDeleted property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDeleted(Boolean value) {
        this.isDeleted = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantity(Double value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the retailProduct property.
     * 
     * @return
     *     possible object is
     *     {@link StoreRetailProductEntity }
     *     
     */
    public StoreRetailProductEntity getRetailProduct() {
        return retailProduct;
    }

    /**
     * Sets the value of the retailProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreRetailProductEntity }
     *     
     */
    public void setRetailProduct(StoreRetailProductEntity value) {
        this.retailProduct = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    /**
     * Gets the value of the swe property.
     * 
     * @return
     *     possible object is
     *     {@link StoreWarehouseBinEntity }
     *     
     */
    public StoreWarehouseBinEntity getSwe() {
        return swe;
    }

    /**
     * Sets the value of the swe property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreWarehouseBinEntity }
     *     
     */
    public void setSwe(StoreWarehouseBinEntity value) {
        this.swe = value;
    }

}
