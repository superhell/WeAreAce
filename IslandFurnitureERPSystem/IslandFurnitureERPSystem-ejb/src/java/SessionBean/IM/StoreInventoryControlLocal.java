/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.IM;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.RetailProductEntity;
import Entity.Store.IM.StoreBinProductEntity;
import Entity.Store.IM.StoreBinRetailProductEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zhengyuan
 */
@Local
public interface StoreInventoryControlLocal {

    public List<StoreProductEntity> getListOfStoreProduct(Long storeId);

    public List<ProductEntity> getListOfProductNotInStore(Long storeId);

    public int addNewStoreProduct(Long storeId, Long productId, Long factoryProductId, Boolean isSelfPicked, String storeRemark, Double minimumInv, Double onAirInventory);

    public int deleteStoreProduct(Long storeId, Long storeProductId, Long factoryProductId);

    public void editStoreProduct(Long storeId, Long storeProductId, Boolean isSelfPicked, Long newFactoryProductId, Double minimumInventory, String storeRemark);

    public List<FactoryProductEntity> listAvailableFactoryProduct(Long productId);

    public List<StoreRetailProductEntity> getListOfStoreRetailProduct(Long storeId);

    public List<RetailProductEntity> getListOfRetailProductNotInStore(Long storeId);

    public int addNewRetailProduct(Long storeId, Long storeRetailProductId, Long factoryRetailProductId,Double minInv , Double onAir, String storeRemark);

    public int deleteStoreRetailProduct(Long storeId, Long storeRetailProductId, Long FactoryRetailProductId);

    public void editStoreRetailProduct(Long storeId, Long storeRetailProductId, Long oldFactoryRetailProductId, Long newFactoryRetailProductId, Double minimumInventory, String storeRemark);

    public List<FactoryRetailProductEntity> listAvailableFactoryRetail(Long rproductId);

    public List<StoreRetailProductEntity> getHaveStockRP(Long storeId);

    public List<StoreProductEntity> getHaveStockP(Long storeId);

    public List<StoreBinProductEntity> getProductStorageInformation(Long productId);

    public List<StoreBinRetailProductEntity> getRProductStorageInformation(Long productId);

    public List<StoreBinProductEntity> getReturnedProduct(Long storeId);

    public List<StoreProductEntity> getNonSelfCollectProduct(Long storeId);

    public List<StoreRetailProductEntity> getRetailProduct(Long storeId);

    public List<StoreBinProductEntity> listAllAvailBin(Long storeProductId);

    public List<StoreBinRetailProductEntity> listAllAvailBinRP(Long storeProductId);

    
    
}
