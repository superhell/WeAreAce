/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.Factory.FactoryBin.FactoryBinEntity;
import Entity.Factory.FactoryBin.FactoryBinStoredProductEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Yoky
 */
@Entity
@Table(name = "InboundMovement")
// only for unrestrictd stock
public class InboundMovementEntity /*extends FactoryMovementEntity*/ implements Serializable {

    @PersistenceContext(unitName = "IslandFurnitureERPSystem-ejbPU")
    private EntityManager em;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long InboundMovementId;

    //goods receipt entity -- inbound movements: 1 <--> M (from which corresponding goods receipt)
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private GoodsReceiptEntity fromGoodsRecipt;
    //factory bin stored product entity -- inbound movements: 1 <--> M (to which bin)
    @ManyToOne
    private FactoryBinEntity toBin;

    //factory rawMaterial entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne
    private FactoryRawMaterialEntity factoryRawMaterial = null;

    //factory retail product entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne
    private FactoryRetailProductEntity factoryRetailProduct = null;

    private int stockTypeIndicator = 0; // default is 0    //to indicate the type of stocks: 1 for factoryRawMaterial, 3 for factoryRetailProduct
    private double quantity;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar creationDate;

    public InboundMovementEntity() {
    }

    public Long getInboundMovementId() {
        return InboundMovementId;
    }

    public void setInboundMovementId(Long InboundMovementId) {
        this.InboundMovementId = InboundMovementId;
    }

    public GoodsReceiptEntity getFromGoodsRecipt() {
        return fromGoodsRecipt;
    }

    public void setFromGoodsRecipt(GoodsReceiptEntity fromGoodsRecipt) {
        this.fromGoodsRecipt = fromGoodsRecipt;
    }

    public FactoryBinEntity getToBin() {
        return toBin;
    }

    public void setToBin(FactoryBinEntity toBin) {
        this.toBin = toBin;
    }

    public FactoryRawMaterialEntity getFactoryRawMaterial() {
        return factoryRawMaterial;
    }

    public void setFactoryRawMaterial(FactoryRawMaterialEntity factoryRawMaterial) {
        this.factoryRawMaterial = factoryRawMaterial;
    }

    public FactoryRetailProductEntity getFactoryRetailProduct() {
        return factoryRetailProduct;
    }

    public void setFactoryRetailProduct(FactoryRetailProductEntity factoryRetailProduct) {
        this.factoryRetailProduct = factoryRetailProduct;
    }

    public int getStockTypeIndicator() {
        return stockTypeIndicator;
    }

    public void setStockTypeIndicator(int stockTypeIndicator) {
        this.stockTypeIndicator = stockTypeIndicator;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    // assumption: the inbound goods is unrestricted
    // changes need further modification
    public void recordInboundMovement(GoodsReceiptEntity goodsRecipt, FactoryBinEntity toBin, FactoryRawMaterialEntity factoryRawMaterial, double quantity, Calendar creationDate) {
        this.fromGoodsRecipt = goodsRecipt;
        this.toBin = toBin;
        this.factoryRawMaterial = factoryRawMaterial;
        this.stockTypeIndicator = 1;
        this.quantity = quantity;
        this.creationDate = creationDate;
        updateFactoryBinStoredProduct(toBin, factoryRawMaterial, quantity);
        updateFactoryRawMaterial(factoryRawMaterial, quantity);
    }

    public void recordInboundMovement(GoodsReceiptEntity GoodsRecipt, FactoryBinEntity toBin, FactoryRetailProductEntity factoryRetailProduct, double quantity, Calendar creationDate) {
        this.fromGoodsRecipt = GoodsRecipt;
        this.toBin = toBin;
        this.factoryRetailProduct = factoryRetailProduct;
        this.stockTypeIndicator = 3;
        this.quantity = quantity;
        this.creationDate = creationDate;
        updateFactoryBinStoredProduct(toBin, factoryRetailProduct, quantity);
        updateFactoryRetailProduct(factoryRetailProduct, quantity);
    }

    private void updateFactoryBinStoredProduct(FactoryBinEntity toBin, FactoryRawMaterialEntity factoryRawMaterial, double quantity) {
        try {
            Query q = em.createQuery("SELECT fbsp FROM FactoryBinStoredProduct fbsp WHERE fbsp.factoryBin = :toBin AND fbsp.factoryRawMaterial = :factoryRawMaterial AND fbsp.status = 'unrestricted'");
            q.setParameter("toBin", toBin);
            q.setParameter("factoryRawMaterial", factoryRawMaterial);

            if (q.getResultList() == null) {
                FactoryBinStoredProductEntity factoryBinStoredProduct = new FactoryBinStoredProductEntity();
                factoryBinStoredProduct.createFactoryBinStoredProduct(factoryRawMaterial, toBin, "unrestricted");
                factoryBinStoredProduct.increaseQuantity(quantity);
                em.persist(factoryBinStoredProduct);
            } else {
                FactoryBinStoredProductEntity factoryBinStoredProduct = (FactoryBinStoredProductEntity) q.getSingleResult();
                factoryBinStoredProduct.increaseQuantity(quantity);
                em.flush();
            }
        } catch (Exception ex) {
            System.err.println("Entity.Factory.SCM.InboundMovementEntity: updateFactoryBinStoredProduct(): Caught an unexpected exception in recordInboundMovement()");
            ex.printStackTrace();
        }
    }

    private void updateFactoryBinStoredProduct(FactoryBinEntity toBin, FactoryRetailProductEntity factoryRetailProduct, double quantity) {
        try {
            Query q = em.createQuery("SELECT fbsp FROM FactoryBinStoredProduct fbsp WHERE fbsp.factoryBin = :toBin AND fbsp.factoryRetailProduct = :factoryRetailProduct AND fbsp.status = 'unrestricted'");
            q.setParameter("toBin", toBin);
            q.setParameter("factoryRetailProduct", factoryRetailProduct);

            if (q.getResultList() == null) {
                FactoryBinStoredProductEntity factoryBinStoredProduct = new FactoryBinStoredProductEntity();
                factoryBinStoredProduct.createFactoryBinStoredProduct(factoryRetailProduct, toBin, "unrestricted");
                factoryBinStoredProduct.increaseQuantity(quantity);
                em.persist(factoryBinStoredProduct);
            } else {
                FactoryBinStoredProductEntity factoryBinStoredProduct = (FactoryBinStoredProductEntity) q.getSingleResult();
                factoryBinStoredProduct.increaseQuantity(quantity);
                em.flush();
            }
        } catch (Exception ex) {
            System.err.println("Entity.Factory.SCM.InboundMovementEntity: updateFactoryBinStoredProduct(): Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    private void updateFactoryRawMaterial(FactoryRawMaterialEntity factoryRawMaterial, double quantity) {
        factoryRawMaterial.setInventory(factoryRawMaterial.getInventory() + quantity);
        em.flush();
    }

    private void updateFactoryRetailProduct(FactoryRetailProductEntity factoryRetailProduct, double quantity) {
        factoryRetailProduct.setInventory(factoryRetailProduct.getInventory() + quantity);
        em.flush();
    }

}
