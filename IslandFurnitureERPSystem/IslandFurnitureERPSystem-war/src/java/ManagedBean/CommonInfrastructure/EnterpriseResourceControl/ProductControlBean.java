/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure.EnterpriseResourceControl;

import Entity.Factory.ProductEntity;
import SessionBean.CommonInFrastructure.RetailProduct_ProductManagementModuleLocal;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author dan
 */
@Named(value = "productControl")
@ViewScoped
public class ProductControlBean {

    @EJB
    private RetailProduct_ProductManagementModuleLocal RPMM;
    private List<ProductEntity> productList;
    private List<ProductEntity> filteredProduct;

    private String newProductName;
    private String newProductDescription;
    private Double newProductPrice;
    private String newProductUnit;
    private Double newProductMemberPrice;

    private ProductEntity selectedProduct;
    private Long selectedDeleteProductId;

    /**
     * Creates a new instance of ProductControlBean
     */
    public ProductControlBean() {
    }

    @PostConstruct
    public void init() {
        System.out.println("ProductControlBean: init:");

        productList = RPMM.ListProduct();
        filteredProduct = productList;

    }

    public void onRowEdit(RowEditEvent event) {
        try {
            System.out.println("onRowEdit test:");
            ProductEntity entity = (ProductEntity) event.getObject();
            System.out.println("onRowEdit test: " + entity.getProductId() + entity.getName());

            if (entity.getPrice() <= entity.getMemberPrice()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Product cannot be edited!", "Member Price must be smaller than Original Price!"));
            } else {
                RPMM.ModifyProduct(entity.getProductId(), entity.getName(), entity.getDescription(),
                        entity.getPrice(), entity.getMemberPrice(), entity.getUnit());

                FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(entity.getProductId()));
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
            System.out.println("unexpected exception occured");
            e.printStackTrace();
        }
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((ProductEntity) event.getObject()).getProductId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteProduct(Long id) {
        try {
            System.out.println("ProductControlBean: deleteProduct: " + String.valueOf(id));
            if (RPMM.DeleteProduct(id)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product deleted successfully! ", ""));

                productList = RPMM.ListProduct();
                filteredProduct = productList;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Product cannot be deleted! ", "Factory product or store product still exists!"));
            }
        } catch (Exception e) {
            System.out.println("unexpected exception occured");
            e.printStackTrace();
        }
    }

    public void addProduct() {
        System.out.println("ProductControlBean: addProduct: ");
        if (newProductPrice <= newProductMemberPrice) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Product cannot be created! ", "Member Price must be smaller than Original Price!"));
        } else {
            RPMM.AddProduct(newProductName, newProductDescription, newProductPrice, newProductMemberPrice, newProductUnit);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product added successfully! ", ""));
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("ProductControl.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(FactoryControlBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void viewProduct(ProductEntity product) throws IOException {
        selectedProduct = product;
        String path = "/secured/restricted/CommonInfrastructure/EnterpriseResouces/ProductBOMControl.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedProduct", selectedProduct);

        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url + path);
        System.err.println("go to another page");

    }

    public List<ProductEntity> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductEntity> productList) {
        this.productList = productList;
    }

    public List<ProductEntity> getFilteredProduct() {
        return filteredProduct;
    }

    public void setFilteredProduct(List<ProductEntity> filteredProduct) {
        this.filteredProduct = filteredProduct;
    }

    public String getNewProductName() {
        return newProductName;
    }

    public void setNewProductName(String newProductName) {
        this.newProductName = newProductName;
    }

    public String getNewProductDescription() {
        return newProductDescription;
    }

    public void setNewProductDescription(String newProductDescription) {
        this.newProductDescription = newProductDescription;
    }

    public Double getNewProductPrice() {
        return newProductPrice;
    }

    public void setNewProductPrice(Double newProductPrice) {
        this.newProductPrice = newProductPrice;
    }

    public String getNewProductUnit() {
        return newProductUnit;
    }

    public void setNewProductUnit(String newProductUnit) {
        this.newProductUnit = newProductUnit;
    }

    public RetailProduct_ProductManagementModuleLocal getRPMM() {
        return RPMM;
    }

    public void setRPMM(RetailProduct_ProductManagementModuleLocal RPMM) {
        this.RPMM = RPMM;
    }

    public ProductEntity getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductEntity selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Double getNewProductMemberPrice() {
        return newProductMemberPrice;
    }

    public void setNewProductMemberPrice(Double newProductMemberPrice) {
        this.newProductMemberPrice = newProductMemberPrice;
    }

    public Long getSelectedDeleteProductId() {
        return selectedDeleteProductId;
    }

    public void setSelectedDeleteProductId(Long selectedDeleteProductId) {
        this.selectedDeleteProductId = selectedDeleteProductId;
    }

}
