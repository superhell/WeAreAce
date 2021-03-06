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
import Entity.Store.StoreEntity;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author apple
 */
@Remote
public interface SalesForecastModuleRemote {
    public SalesForecastEntity GetSalesForecast(Long salesForecastId) throws Exception;
    
    public List<SalesForecastEntity> ListSalesForecast(Long factoryId,Long storeId, Object product, Calendar period);
    
    public List<IntegratedSalesForecastEntity> getIntegrateSalesForecastList(Long FactoryId,Calendar period,Long factoryProductId);
    
    public IntegratedSalesForecastEntity IntegrateSalesForecast(String type,Long factoryProductId, Calendar period);
    
    public List<FactoryProductEntity> productListNeededTobeIntegrated(Long FactoryId) throws Exception;
    
    public List<FactoryRetailProductEntity> retailProductListNeedToBeIntegrated(Long FactoryId);
    
    public void GenerateIntegratedSalesForecast(String type,Long factoryProductId,Calendar period);
    
    public List<StoreEntity> ListStore(Long factoryId);
    
}
