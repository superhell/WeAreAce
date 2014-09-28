/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import SessionBean.MRP.RetailProductPurchasePlanModuleLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author hangsun
 */
@Named(value = "retailProductPurchasePlanView")
@ViewScoped
public class RetailProductPurchasePlan{

    /**
     * Creates a new instance of PlannedOrderList
     */
    
    private List<IntegratedPlannedOrderEntity> retailProductPurchasePlan;
    private List<IntegratedPlannedOrderEntity> retialProductPurchasePlanUnconfirmed;
    private List<IntegratedPlannedOrderEntity> retialProductPurchasePlanConfirmed;
    private List<IntegratedPlannedOrderEntity> retialProductPurchasePlanCancelled;
    private Long integratedSalesForecastId;
    private Long id;
    private String department;
    private Long retailProductPurchasePlanId;
    private Double amount;
    
    @EJB
    private RetailProductPurchasePlanModuleLocal RPPP;
    
    @PostConstruct
    public void init(){
        id = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        department = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("department");
        retailProductPurchasePlan = RPPP.getRetailProductPurchasePlan(id,department);
        retialProductPurchasePlanUnconfirmed = RPPP.getRetailProductPurchasePlanUnconfirmed(id,department);
        retialProductPurchasePlanConfirmed = RPPP.getRetailProductPurchasePlanConfirmed(id,department);
        retialProductPurchasePlanCancelled = RPPP.getRetailProductPurchasePlanCancelled(id,department);
    }
    
    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlan(){
        return retailProductPurchasePlan;
    } 

    public List<IntegratedPlannedOrderEntity> getRetialProductPurchasePlanUnconfirmed() {
        return retialProductPurchasePlanUnconfirmed;
    }

    public List<IntegratedPlannedOrderEntity> getRetialProductPurchasePlanConfirmed() {
        return retialProductPurchasePlanConfirmed;
    }

    public List<IntegratedPlannedOrderEntity> getRetialProductPurchasePlanCancelled() {
        return retialProductPurchasePlanCancelled;
    }

    public Long getIntegratedSalesForecastId() {
        return integratedSalesForecastId;
    }   

    public Long getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public Long getRetailProductPurchasePlanId() {
        return retailProductPurchasePlanId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setRetailProductPurchasePlanId(Long retailProductPurchasePlanId) {
        this.retailProductPurchasePlanId = retailProductPurchasePlanId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    

    public void setRetailProductPurchasePlan(List<IntegratedPlannedOrderEntity> retailProductPurchasePlan) {
        this.retailProductPurchasePlan = retailProductPurchasePlan;
    }

    public void setRetialProductPurchasePlanUnconfirmed(List<IntegratedPlannedOrderEntity> retialProductPurchasePlanUnconfirmed) {
        this.retialProductPurchasePlanUnconfirmed = retialProductPurchasePlanUnconfirmed;
    }

    public void setRetialProductPurchasePlanConfirmed(List<IntegratedPlannedOrderEntity> retialProductPurchasePlanConfirmed) {
        this.retialProductPurchasePlanConfirmed = retialProductPurchasePlanConfirmed;
    }

    public void setRetialProductPurchasePlanCancelled(List<IntegratedPlannedOrderEntity> retialProductPurchasePlanCancelled) {
        this.retialProductPurchasePlanCancelled = retialProductPurchasePlanCancelled;
    }

    public void setIntegratedSalesForecastId(Long integratedSalesForecastId) {
        this.integratedSalesForecastId = integratedSalesForecastId;
    }

    public String create(){
        System.out.println("SalesForecastId" + integratedSalesForecastId);
        Long factoryRetailProductId = RPPP.getFactoryRetailProductId(integratedSalesForecastId);
        System.out.println("factoryRetailProductId " + factoryRetailProductId);
        RPPP.generateRetailProductPurchasePlan(integratedSalesForecastId, factoryRetailProductId);
        return "/secured/restricted/Factory/MRP/RetailProductPurchasePlan/MRPRetailProductPurchasePlan?faces-redirect=true";
    }
    
    public String confirm(Long id){
        RPPP.editRetailProductPurchasePlan(id, "status", "confirmed");     
        return "/secured/restricted/Factory/MRP/RetailProductPurchasePlan/MRPRetailProductPurchasePlanUnconfirmed?faces-redirect=true";
    }
    
    public String cancel(Long id){
        RPPP.editRetailProductPurchasePlan(id, "status", "cancelled"); 
        return "/secured/restricted/Factory/MRP/RetailProductPurchasePlan/MRPRetailProductPurchasePlanUnconfirmed?faces-redirect=true";
    }
    
    public void saveId(Long id) {
        retailProductPurchasePlanId = id;
        RPPP.editRetailProductPurchasePlan(retailProductPurchasePlanId, "amount", amount);
    }   
    
    public void amountChanged(ValueChangeEvent event) {
        Object newValue = event.getNewValue();
        amount = (Double) newValue;
    }
    
}
