/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hangsun
 */
@Local
public interface RetailProductPurchasePlanModuleLocal {

    public boolean generateRetailProductPurchasePlan(String status, Calendar generateDate, Calendar targetSalesStartDate, Calendar targetSalesEndDate, Integer output, Long productId, String remark);

    public void editRetailProductPurchasePlan(Long productionPlanId, String field, Object content);

    public boolean deleteRetailProductPurchasePlan(Long productionPlanId);

    public List<ArrayList> getProductionPlan();
    
}