/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.RetailProductEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import ManagedBean.SCM.AddFactoryProduct;
import SessionBean.IM.StoreInventoryControlLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author zhengyuan
 */
@Named(value = "storeRetailProductControl")
@ViewScoped
public class StoreRetailProductControl {

    /**
     * Creates a new instance of StoreRetailProductControl
     */
    public StoreRetailProductControl() {
    }
        @EJB
    StoreInventoryControlLocal sicl;
        
    private List<StoreRetailProductEntity> currentStoreRetailProductList = new ArrayList<>();
    private List<RetailProductEntity> retailProductNotInStoreList = new ArrayList<>();
    private StoreRetailProductEntity selectedStoreRetailProduct;
    private RetailProductEntity selectedRetailProduct;
    private StoreEntity store;
    private String msgprint1;
    private String msgprint2;
   
    
    private List<FactoryRetailProductEntity> availableFactory;
    private FactoryRetailProductEntity selectedFactory;
    private StoreRetailProductEntity storeRetailProductToDelete;
    private String remark;
    private Long storeId;
    
    
    @PostConstruct
    public void init(){
        try{
            if ((int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Userlvl")==0) {
                storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("HFactoryId");
                
            } else {
                storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            }
        currentStoreRetailProductList = sicl.getListOfStoreRetailProduct(storeId);
        System.out.println("ManagedBean : StoreProductControl : currentStoreProductList size()" + currentStoreRetailProductList.size());
        retailProductNotInStoreList = sicl.getListOfRetailProductNotInStore(storeId);
        System.out.println("ManagedBean : StoreProductControl : productNotInStore size()" + retailProductNotInStoreList.size());

         } catch (Exception ex) {
            Logger.getLogger(AddFactoryProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void selectRetailProductToDetele(StoreRetailProductEntity srpe){
        storeRetailProductToDelete = srpe;
        System.out.println("selected retail product to delete:" + srpe.getStoreRetailProductId());
         
        
    }
    
   public void selelectProductToAdd(RetailProductEntity pe){
        selectedRetailProduct = pe;
        selectAvailableFactory(pe);
        
        
    }
    public void deleteStoreRetailProduct(StoreRetailProductEntity spe){
        
      System.out.println("deleteRetailProduct " + spe.getFactoryRetailProduct().getFactoryRetailProdctId());
      int result = sicl.deleteStoreRetailProduct(storeId, spe.getStoreRetailProductId(), spe.getFactoryRetailProduct().getFactoryRetailProdctId());
       if(result == 1){
            
            msgprint2 = "Retail roduct delete successfully!!";
            currentStoreRetailProductList = sicl.getListOfStoreRetailProduct(storeId);
            retailProductNotInStoreList = sicl.getListOfRetailProductNotInStore(storeId);
            
        }
        else {
            msgprint2 = "Exception occured. Please try again or raise a ticket.";
        } 
    }
    
    public void selectAvailableFactory(RetailProductEntity rpe){
        selectedRetailProduct = rpe;
        availableFactory = sicl.listAvailableFactoryRetail(rpe.getRetailProductId());
    }
    
    public void addStoreRetailProduct(RetailProductEntity rpe,FactoryRetailProductEntity sf){
        
        int result = sicl.addNewRetailProduct(storeId, rpe.getRetailProductId(),sf.getFactoryRetailProdctId(), remark);
        if(result == 1) {
            msgprint1 = "A new retail product added successfully!";
            currentStoreRetailProductList = sicl.getListOfStoreRetailProduct(storeId);
            retailProductNotInStoreList = sicl.getListOfRetailProductNotInStore(storeId);
            
        }
        else{
            msgprint1 = "Exception occured. Please try again or raise a ticket.";
            
        }
            
    }

 public void onRowEdit(RowEditEvent event) {
        System.out.println("onRowEdit test: ");
        StoreRetailProductEntity entity = (StoreRetailProductEntity) event.getObject();
        Long oldFactoryRetailProductId = entity.getFactoryRetailProduct().getFactoryRetailProdctId();
        System.out.println("onRowEdit test: " + entity.getStoreRetailProductId() + entity.getMinimumInventory());

        if (entity.getFactoryRetailProduct()==null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Store Retail Product cannot be edited!", "Please at least select a factory"));
        } else {
           sicl.editStoreRetailProduct(entity.getStore().getStoreId(), entity.getStoreRetailProductId(), oldFactoryRetailProductId, entity.getFactoryRetailProduct().getFactoryRetailProdctId(), entity.getMinimumInventory(),entity.getStoreRemark());

            FacesMessage msg = new FacesMessage("Store Retail Product Edited", String.valueOf(entity.getStoreRetailProductId()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((StoreRetailProductEntity) event.getObject()).getStoreRetailProductId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    
    
    public StoreInventoryControlLocal getSicl() {
        return sicl;
    }

    public void setSicl(StoreInventoryControlLocal sicl) {
        this.sicl = sicl;
    }

    public List<StoreRetailProductEntity> getCurrentStoreRetailProductList() {
        return currentStoreRetailProductList;
    }

    public void setCurrentStoreRetailProductList(List<StoreRetailProductEntity> currentStoreRetailProductList) {
        this.currentStoreRetailProductList = currentStoreRetailProductList;
    }

    public List<RetailProductEntity> getRetailProductNotInStoreList() {
        return retailProductNotInStoreList;
    }

    public void setRetailProductNotInStoreList(List<RetailProductEntity> retailProductNotInStoreList) {
        this.retailProductNotInStoreList = retailProductNotInStoreList;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }


    public StoreRetailProductEntity getSelectedStoreRetailProduct() {
        return selectedStoreRetailProduct;
    }

    public void setSelectedStoreRetailProduct(StoreRetailProductEntity selectedStoreRetailProduct) {
        this.selectedStoreRetailProduct = selectedStoreRetailProduct;
    }

    public RetailProductEntity getSelectedRetailProduct() {
        return selectedRetailProduct;
    }

    public void setSelectedRetailProduct(RetailProductEntity selectedRetailProduct) {
        this.selectedRetailProduct = selectedRetailProduct;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public List<FactoryRetailProductEntity> getAvailableFactory() {
        return availableFactory;
    }

    public void setAvailableFactory(List<FactoryRetailProductEntity> availableFactory) {
        this.availableFactory = availableFactory;
    }

    public FactoryRetailProductEntity getSelectedFactory() {
        return selectedFactory;
    }

    public void setSelectedFactory(FactoryRetailProductEntity selectedFactory) {
        this.selectedFactory = selectedFactory;
    }

    public StoreRetailProductEntity getStoreRetailProductToDelete() {
        return storeRetailProductToDelete;
    }

    public void setStoreRetailProductToDelete(StoreRetailProductEntity storeRetailProductToDelete) {
        this.storeRetailProductToDelete = storeRetailProductToDelete;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



    public String getMsgprint1() {
        return msgprint1;
    }

    public void setMsgprint1(String msgprint1) {
        this.msgprint1 = msgprint1;
    }

    public String getMsgprint2() {
        return msgprint2;
    }

    public void setMsgprint2(String msgprint2) {
        this.msgprint2 = msgprint2;
    }
    
    
}
