/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.KM;

import Entity.Kitchen.ComboEntity;
import Entity.Kitchen.ComboItemEntity;
import Entity.Kitchen.DailySalesEntity;
import Entity.Kitchen.DetailedDishItemEntity;
import Entity.Kitchen.DishEntity;
import Entity.Kitchen.DishItemEntity;
import Entity.Kitchen.IngredientItemEntity;
import Entity.Kitchen.KitchenEntity;
import Entity.Kitchen.KitchenOrderEntity;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Yoky
 */
@Stateful
public class CustomerOrderFulfillmentModule implements CustomerOrderFulfillmentModuleLocal {

    @PersistenceContext(unitName = "IslandFurnitureERPSystem-ejbPU")
    private EntityManager em;

    public CustomerOrderFulfillmentModule() {
    }

    @Override
    public KitchenOrderEntity createOrder(Long kitchenId) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            KitchenOrderEntity order = new KitchenOrderEntity(kitchen);
            em.persist(order);
            kitchen.getOrders().add(order);
            em.flush();
            return order;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: createOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Long addDishItem(Long orderId, Long dishId, Integer quantity) {
        try {
            KitchenOrderEntity order = em.find(KitchenOrderEntity.class, orderId);
            DishEntity dish = em.find(DishEntity.class, dishId);
            DishItemEntity dishItem = new DishItemEntity(dish, quantity);
            em.persist(dishItem);
            order.getDishes().add(dishItem);
            em.flush();
            return dishItem.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: createOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    @Override
    public Long deleteDishItem(Long orderId, Long dishItemId) {
        try {
            KitchenOrderEntity order = em.find(KitchenOrderEntity.class, orderId);
            DishItemEntity dishItem = em.find(DishItemEntity.class, dishItemId);
            order.getDishes().remove(dishItem);
            em.flush();
            return dishItem.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: deleteDishItem(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }
    
    @Override
    public Long deleteComboItem(Long orderId, Long comboItemId) {
        try {
            KitchenOrderEntity order = em.find(KitchenOrderEntity.class, orderId);
            ComboItemEntity comboItem = em.find(ComboItemEntity.class, comboItemId);
            order.getCombos().remove(comboItem);
            em.flush();
            return comboItem.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: deleteComboItem(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }
    
    @Override
    public Long addComboItem(Long orderId, Long comboId, Integer quantity) {
        try {
            KitchenOrderEntity order = em.find(KitchenOrderEntity.class, orderId);
            ComboEntity combo = em.find(ComboEntity.class, comboId);
            ComboItemEntity comboItem = new ComboItemEntity(combo, quantity);
            em.persist(comboItem);
            order.getCombos().add(comboItem);
            em.flush();
            return comboItem.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: createOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    @Override
    public Long confirmOrder(Long orderId) {
        try {
            KitchenOrderEntity order = em.find(KitchenOrderEntity.class, orderId);
            order.setStatus("Confirmed");
            
            Calendar cal = Calendar.getInstance();
            DailySalesEntity dailySales = findDailySales(order.getKitchen().getId(), cal.getTime());
            if (dailySales == null) {
                dailySales = createDailySales(order.getKitchen());
            }
            
            for (DishItemEntity di : order.getDishes()) {
                order.setTotal(order.getTotal()+ di.getDish().getPrice() * di.getQuantity());
                DetailedDishItemEntity ddi = new DetailedDishItemEntity(di.getDish(), di.getQuantity());
                em.persist(ddi);
                em.flush();
                order.getDetailedDishItems().add(ddi);
                order.setTotalDishItemQuantity(order.getTotalDishItemQuantity() + 1);
                
                for (DishItemEntity sdi : dailySales.getDishes()) {
                    if (sdi.getDish().equals(di.getDish())) {
                        Integer quantity = sdi.getQuantity() + di.getQuantity();
                        sdi.setQuantity(quantity);
                        break;
                    }
                }
                
            }
            for (ComboItemEntity ci : order.getCombos()) {
                order.setTotal(order.getTotal()+ ci.getCombo().getPrice() * ci.getQuantity());
                for (DishItemEntity di : ci.getCombo().getDishes()) {
                    boolean found = false;
                    for (DetailedDishItemEntity ddi : order.getDetailedDishItems()) {
                        if (ddi.getDish().equals(di.getDish())) {
                            ddi.setQuantity(ddi.getQuantity() + ci.getQuantity() * di.getQuantity());
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        DetailedDishItemEntity ddi = new DetailedDishItemEntity(di.getDish(), ci.getQuantity() * di.getQuantity());
                        em.persist(ddi);
                        em.flush();
                        order.getDetailedDishItems().add(ddi);
                        order.setTotalDishItemQuantity(order.getTotalDishItemQuantity() + 1);
                    }
                }
                
                for (ComboItemEntity sci : dailySales.getCombos()) {
                    if (sci.getCombo().equals(ci.getCombo())) {
                        Integer quantity = sci.getQuantity() + ci.getQuantity();
                        sci.setQuantity(quantity);
                        break;
                    }
                }
            }
            dailySales.setSales(dailySales.getSales() + order.getTotal());

            return order.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: createOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    private DailySalesEntity createDailySales(KitchenEntity kitchen) {
        try {
            DailySalesEntity dailySales = new DailySalesEntity(kitchen);
            em.persist(dailySales);
            kitchen.getDailySales().add(dailySales);
            for (DishEntity d : kitchen.getDishes()) {
                DishItemEntity di = new DishItemEntity(d, 0);
                em.persist(di);
                em.flush();
                dailySales.getDishes().add(di);
                d.getDailySales().add(dailySales);
                em.flush();
            }
            for (ComboEntity c : kitchen.getCombos()) {
                ComboItemEntity ci = new ComboItemEntity(c, 0);
                em.persist(ci);
                em.flush();
                dailySales.getCombos().add(ci);
                c.getDailySales().add(dailySales);
                em.flush();
            }
            em.flush();
            return dailySales;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: createOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public Long cancelOrder(Long orderId) {
        try {
            KitchenOrderEntity order = em.find(KitchenOrderEntity.class, orderId);
            if (!order.getStatus().equals("Unconfirmed")) {
                System.out.println("SessionBean.KM.CustomerOrderFulfillmentModule: createOrder(): Failed. The order is already confirmed or fulfilled.");
                return -1L;
            }
            order.setStatus("Cancelled");
            order.getKitchen().getOrders().remove(order);
            order.setKitchen(null);
            order.getDishes().clear();
            order.getCombos().clear();
            em.flush();
            return order.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: createOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -2L;
        }
    }

    @Override
    public List<KitchenOrderEntity> getCurrentDateUnfulfilledOrder(Long kitchenId) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            Calendar cal = Calendar.getInstance();
            Query q = em.createQuery("SELECT odr FROM KitchenOrderEntity odr WHERE odr.kitchen = :kitchen AND odr.status = 'Confirmed' AND odr.creationTime = :cal");
            q.setParameter("kitchen", kitchen);
            q.setParameter("cal", cal);
            return q.getResultList();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: getAllUnfulfilledOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<KitchenOrderEntity> getDailyOrders(Long kitchenId, Date selectedDate) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            Calendar cal = Calendar.getInstance();
            cal.setTime(selectedDate);
            Query q = em.createQuery("SELECT odr FROM KitchenOrderEntity odr WHERE odr.kitchen = :kitchen AND odr.creationTime = :cal");
            q.setParameter("kitchen", kitchen);
            q.setParameter("cal", cal);
            return q.getResultList();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: getAllOrders(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Long fulfillDishItem(Long orderId, Long detailedDishItemId) {
        try {
            KitchenOrderEntity order = em.find(KitchenOrderEntity.class, orderId);
            DetailedDishItemEntity ddi = em.find(DetailedDishItemEntity.class, detailedDishItemId);
            ddi.setFulfilled(true);
            order.setFulfilledDishItemQuantity(order.getFulfilledDishItemQuantity() + 1);
            if(order.getFulfilledDishItemQuantity().equals(order.getTotalDishItemQuantity())) {
                order.setStatus("Fulfilled");
            }
            return ddi.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: fulfillDishItem(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    @Override
    public List<KitchenOrderEntity> getCurrentDateUnservedOrder(Long kitchenId) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            Calendar cal = Calendar.getInstance();
            Query q = em.createQuery("SELECT odr FROM KitchenOrderEntity odr WHERE odr.kitchen = :kitchen AND odr.status = 'Fulfilled' AND odr.creationTime = :cal");
            q.setParameter("kitchen", kitchen);
            q.setParameter("cal", cal);
            return q.getResultList();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: getCurrentDateUnservedOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Long serveOrder(Long orderId) {
        try {
            KitchenOrderEntity order = em.find(KitchenOrderEntity.class, orderId);
            order.setStatus("Served");
            return order.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: serveOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    @Override
    public DailySalesEntity findDailySales(Long kitchenId, Date selectedDate) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            Calendar cal = Calendar.getInstance();
            cal.setTime(selectedDate);
            Query q = em.createQuery("SELECT ds FROM DailySalesEntity ds WHERE ds.kitchen = :kitchen AND ds.salesDate = :cal");
            q.setParameter("kitchen", kitchen);
            q.setParameter("cal", cal);
            return (DailySalesEntity) q.getSingleResult();
        } catch (NoResultException nex) {
            return null;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: createOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DishItemEntity> findDailySalesDishItems(Long dailySalesId) {
        return em.find(DailySalesEntity.class, dailySalesId).getDishes();
    }
    
    @Override
    public List<ComboItemEntity> findDailySalesComboItems(Long dailySalesId) {
        return em.find(DailySalesEntity.class, dailySalesId).getCombos();
    }

    @Override
    public List<IngredientItemEntity> findRecipe(Long dishId) {
        return em.find(DishEntity.class, dishId).getRecipe();
    }

    
}
