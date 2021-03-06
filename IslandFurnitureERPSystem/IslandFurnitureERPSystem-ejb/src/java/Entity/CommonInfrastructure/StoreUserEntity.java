/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.CommonInfrastructure;

import Entity.Store.OCRM.TransactionEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dan
 */
@Entity
@Table(name="StoreUser")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class StoreUserEntity extends UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "storeStaff")
    @XmlTransient
    private List<TransactionEntity> transactions = new ArrayList();

    private boolean isCasher;
    private Double beginCash;
    private Double endCash;
    
    public StoreUserEntity() {
    }
    
    
    public StoreUserEntity(String department, String idNumber, Integer userLevel, String lastName, String midName,
            String firstName, String position,  Calendar birthday, String gender, 
            String title, String address, String postalCode, String email, long departmentId, String password, Boolean deleteFlag) {
        super(department,idNumber, userLevel,lastName,midName, firstName, position, 
                birthday,gender,title, address, postalCode, email, deleteFlag, departmentId, password);
        this.isCasher = false;
        this.beginCash = 0D;
        this.endCash = 0D;
        
    }

    public void editStoreUserEntity(String department, Integer userLevel, String lastName, String midName,
            String firstName, String position,  Calendar birthday, String gender, 
            String title, String address, String postalCode, String email, long departmentId, Boolean deleteFlag) {
        super.editUserEntity(department, userLevel, lastName, midName, firstName,
                position, birthday, gender, title, address, postalCode, email, deleteFlag, departmentId);
        
    }

    public boolean isIsCasher() {
        return isCasher;
    }

    public void setIsCasher(boolean isCasher) {
        this.isCasher = isCasher;
    }

    public Double getBeginCash() {
        return beginCash;
    }

    public void setBeginCash(Double beginCash) {
        this.beginCash = beginCash;
    }

    public Double getEndCash() {
        return endCash;
    }

    public void setEndCash(Double endCash) {
        this.endCash = endCash;
    }
    
    

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransaction(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }
    
    
       
    
}
