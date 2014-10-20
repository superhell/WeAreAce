/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Kitchen;

import Entity.CommonInfrastructure.UserEntity;
import Entity.Store.OCRM.MemberEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Yoky
 */
@Entity
public class KitchenOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<DishItemEntity> dishes = new ArrayList<>();
    @OneToMany
    private List<ComboItemEntity> combos = new ArrayList<>();
    private Double total = 0.0;
    private Double received = 0.0;
    private Double due = 0.0;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar creationTime = Calendar.getInstance();
    private String status;  //Unconfirmed, Confirmed, Fulfilled, Cancelled
    @ManyToOne
    private KitchenEntity kitchen;
    @ManyToOne  // not included in member yet
    private MemberEntity member = null;
//    @ManyToOne
//    private UserEntity operator;

    public KitchenOrderEntity() {
        this.status = "Unconfirmed";
    }

    public KitchenOrderEntity(KitchenEntity kitchen) {
        this.kitchen = kitchen;
        this.status = "Unconfirmed";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DishItemEntity> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishItemEntity> dishes) {
        this.dishes = dishes;
    }

    public List<ComboItemEntity> getCombos() {
        return combos;
    }

    public void setCombos(List<ComboItemEntity> combos) {
        this.combos = combos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getReceived() {
        return received;
    }

    public void setReceived(Double received) {
        this.received = received;
    }

    public Double getDue() {
        return due;
    }

    public void setDue(Double due) {
        this.due = due;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KitchenOrderEntity)) {
            return false;
        }
        KitchenOrderEntity other = (KitchenOrderEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Kitchen.OrderEntity[ id=" + id + " ]";
    }

}
