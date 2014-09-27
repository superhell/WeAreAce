/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.MRP.IntegratedSalesForecastEntity;
import Entity.Factory.MRP.SalesForecastEntity;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface SalesForecastModuleLocal {
    public SalesForecastEntity GetSalesForecast(Long salesForecastId);
    
    public List<SalesForecastEntity> ListSalesForecast(Long factoryId,Long storeId, Object product, Calendar period);
    
    public List<IntegratedSalesForecastEntity> getIntegrateSalesForecastList(Calendar period,Long factoryProductId);
    
    public IntegratedSalesForecastEntity IntegrateSalesForecast(String type,Long factoryProductId, Calendar period);
    
    public List<FactoryProductEntity> productListNeededTobeIntegrated(Long FactoryId);
    
    public List<FactoryRetailProductEntity> retailProductListNeedToBeIntegrated(Long FactoryId);
    
    public void GenerateIntegratedSalesForecast(String type,Long factoryProductId,Calendar period);
    
}
