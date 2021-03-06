/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule.ReferenceToIPO;

import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.DeliveryOrderEntity;
import Entity.Factory.SCM.SupplierEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author zhangshiyu
 */
@Named(value = "displayPlannedOrderDeliveryAmountAndDate")
@ViewScoped
public class DisplayPlannedDeliveryAmountAndDate implements Serializable {

    @EJB
    private PurchaseOrderManagementModuleLocal pmb;
    private IntegratedPlannedOrderEntity integratedPlannedOrder;

    private Collection<DeliveryOrderEntity> deliveryOrderList;
    private Double nextMonthBeginPlannedAmount;
    private Double purchaseAmount;

    private String itemType;
    private Long itemId;
    private FactoryRawMaterialEntity frm;
    private FactoryRetailProductEntity frp;
    private SupplierEntity supplier;
    private ContractEntity contract;

    @PostConstruct
    private void init() {
        try {
            //be put @selectedIntegratedPlannedOrder
            integratedPlannedOrder = (IntegratedPlannedOrderEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedIPO");
            nextMonthBeginPlannedAmount = (Double) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nextMonthBeginPlannedAmount");
            supplier = (SupplierEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedSupplierIPO");

            if (integratedPlannedOrder.getFactoryRawMaterialAmount() != null) {
                itemType = "RawMaterial";
                frm = integratedPlannedOrder.getFactoryRawMaterialAmount().getFactoryRawMaterial();
                itemId = frm.getFactoryRawMaterialId();
            } else {
                itemType = "RetailProduct";
                frp = integratedPlannedOrder.getFactoryRetailProductAmount().getFactoryRetailProduct();
                itemId = frp.getFactoryRetailProdctId();
            }
            contract = pmb.selectSupplier(itemType, itemId, supplier.getSupplierId());
            purchaseAmount = pmb.generatePurchaseAmount(integratedPlannedOrder.getId(), nextMonthBeginPlannedAmount, itemType, contract.getLotSize());

            deliveryOrderList = pmb.getDeliveryAmountAndDate(integratedPlannedOrder.getId(), purchaseAmount);
        } catch (Exception ex) {
            Logger.getLogger(DisplayPlannedDeliveryAmountAndDate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public IntegratedPlannedOrderEntity getIntegratedPlannedOrder() {
        return integratedPlannedOrder;
    }

    public void setIntegratedPlannedOrder(IntegratedPlannedOrderEntity integratedPlannedOrder) {
        this.integratedPlannedOrder = integratedPlannedOrder;
    }

    public Collection<DeliveryOrderEntity> getDeliveryOrderList() {
        return deliveryOrderList;
    }

    public void setDeliveryOrderList(Collection<DeliveryOrderEntity> deliveryOrderList) {
        this.deliveryOrderList = deliveryOrderList;
    }

    public Double getNextMonthBeginPlannedAmount() {
        return nextMonthBeginPlannedAmount;
    }

    public void setNextMonthBeginPlannedAmount(Double nextMonthBeginPlannedAmount) {
        this.nextMonthBeginPlannedAmount = nextMonthBeginPlannedAmount;
    }

    public Double getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(Double purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public FactoryRawMaterialEntity getFrm() {
        return frm;
    }

    public void setFrm(FactoryRawMaterialEntity frm) {
        this.frm = frm;
    }

    public FactoryRetailProductEntity getFrp() {
        return frp;
    }

    public void setFrp(FactoryRetailProductEntity frp) {
        this.frp = frp;
    }

    public DisplayPlannedDeliveryAmountAndDate() {
    }

    public String displayPlannedDeliveryOrders() {
        return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/ReferenceToIntegratedPlannedOrder/DisplayPlannedDeliveryAmountAndDate?faces-redirect=true";
    }

    public String passValue() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deliveryOrderList", deliveryOrderList);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemType", itemType);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemId", itemId);

        if (itemType.equals("RawMaterial")) {
            return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/ReferenceToIntegratedPlannedOrder/DisplayGeneratedPO?faces-redirect=true";
        } else {//itemType.equals("RetailProduct")
            return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/ReferenceToIntegratedPlannedOrder/DisplayDeliveryDestination?faces-redirect=true";
        }
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Delivery Order Edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Delviery Order Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
