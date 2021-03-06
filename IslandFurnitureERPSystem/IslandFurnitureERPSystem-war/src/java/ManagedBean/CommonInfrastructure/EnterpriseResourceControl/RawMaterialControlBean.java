/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.CommonInfrastructure.EnterpriseResourceControl;

import Entity.Factory.ProductEntity;
import Entity.Factory.RawMaterialEntity;
import SessionBean.CommonInFrastructure.EnterpriseInventoryManagementModule_RawMaterialLocal;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author dan
 */
@Named(value = "rawMaterialControl")
@ViewScoped
public class RawMaterialControlBean {

    @EJB
    private EnterpriseInventoryManagementModule_RawMaterialLocal EIMR;
    private List<RawMaterialEntity> rawMaterialList;
    private List<RawMaterialEntity> filteredRawMaterial;
    
    private String newRawMaterialName;
    private String newRawMaterialDescription;
    
    private String newRawMaterialUnit;
    
    private Long selectedDeleteRawMaterialId;
    /**
     * Creates a new instance of RawMaterialControlBean
     */
    public RawMaterialControlBean() {
    }
    
    @PostConstruct
    public void init() {
        System.out.println("RawMaterialControlBean: init:");

        rawMaterialList = EIMR.listRawMaterial();
        filteredRawMaterial = rawMaterialList;

    }

    public void onRowEdit(RowEditEvent event) throws Exception {
        System.out.println("onRowEdit test:");
        RawMaterialEntity entity = (RawMaterialEntity) event.getObject();
        System.out.println("onRowEdit test: " + entity.getMaterialId()+ entity.getMaterialName());
        
        EIMR.modifyRawMaterial(entity.getMaterialId(), entity.getMaterialName(), entity.getDescription(), entity.getUnit());
        FacesMessage msg = new FacesMessage("Raw Material Edited", String.valueOf(entity.getMaterialId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((RawMaterialEntity) event.getObject()).getMaterialId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteRawMaterial(Long id) throws Exception {
        System.out.println("RawMaterialControlBean: deleteRawMaterial: " + String.valueOf(id));      
        int result = EIMR.deleteRawMaterial(id);
        if(result==1){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product deleted successfully! ", ""));
        
        rawMaterialList = EIMR.listRawMaterial();
        filteredRawMaterial = rawMaterialList;
        }else if(result == -1){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Raw Material cannot be deleted! ", "Still associated with BOM entity!"));
        }else if(result == -2){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Raw Material cannot be deleted! ", "Factory raw material still exists!"));
        }
    }
    
    public void addRawMaterial() {
        System.out.println("RawMaterialControlBean: addRawMaterial: ");
        try{EIMR.addRawMaterial(newRawMaterialName, newRawMaterialDescription, newRawMaterialUnit);}catch(Exception ex){}
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product added successfully! ", ""));

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("RawMaterialControl.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FactoryControlBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<RawMaterialEntity> getRawMaterialList() {
        return rawMaterialList;
    }

    public void setRawMaterialList(List<RawMaterialEntity> rawMaterialList) {
        this.rawMaterialList = rawMaterialList;
    }

    public List<RawMaterialEntity> getFilteredRawMaterial() {
        return filteredRawMaterial;
    }

    public void setFilteredRawMaterial(List<RawMaterialEntity> filteredRawMaterial) {
        this.filteredRawMaterial = filteredRawMaterial;
    }

    public String getNewRawMaterialName() {
        return newRawMaterialName;
    }

    public void setNewRawMaterialName(String newRawMaterialName) {
        this.newRawMaterialName = newRawMaterialName;
    }

    public String getNewRawMaterialDescription() {
        return newRawMaterialDescription;
    }

    public void setNewRawMaterialDescription(String newRawMaterialDescription) {
        this.newRawMaterialDescription = newRawMaterialDescription;
    }

    public String getNewRawMaterialUnit() {
        return newRawMaterialUnit;
    }

    public void setNewRawMaterialUnit(String newRawMaterialUnit) {
        this.newRawMaterialUnit = newRawMaterialUnit;
    }

    public Long getSelectedDeleteRawMaterialId() {
        return selectedDeleteRawMaterialId;
    }

    public void setSelectedDeleteRawMaterialId(Long selectedDeleteRawMaterialId) {
        this.selectedDeleteRawMaterialId = selectedDeleteRawMaterialId;
    }
    
    
    
}
