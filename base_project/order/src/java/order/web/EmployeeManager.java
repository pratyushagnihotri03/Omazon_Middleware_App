/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order.web;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import order.ejb.EmployeeBean;
import order.entity.Product;

/**
 *
 * @author manisha luthra
 */
@ManagedBean
@SessionScoped
public class EmployeeManager {
    private static Logger logger = Logger.getLogger("order.web.EmployeeManager");
    private List<Product> products;
    private Integer currentProductId;
    private String username;
    private String password;
    private boolean isUsernameValid;
    private boolean isPasswordValid;
    private boolean validationComplete = false;
    @EJB
    private EmployeeBean employee;
    private Integer newProductID;
    private String newProductName;
    private Integer newProductPrice;

    /**
     * @return the orders
     */
    public List<Product> getProducts() {
        try {
            this.products = employee.getProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
    
     public void createProduct() {
        try {
            employee.createProduct(
                    newProductID,
                    newProductName,
                    newProductPrice);

            logger.info(
                    "Created new product with product ID " + newProductID
                    + ", name " + newProductName + ", price "
                    + newProductPrice + ".");
            this.newProductID = null;
            this.newProductName = null;
            this.newProductPrice = null;
            
        } catch (Exception e) {
            logger.warning("Problem creating customer in newCustomer.");
        }
    }
     
    
    public void setNewProductID(Integer newProductID) {
        this.newProductID = newProductID;
    }

    public void setNewProductName(String newProductName) {
        this.newProductName = newProductName;
    }

    public void setNewProductPrice(Integer newProductPrice) {
        this.newProductPrice = newProductPrice;
    }

    public Integer getNewProductID() {
        return newProductID;
    }

    public String getNewProductName() {
        return newProductName;
    }

    public Integer getNewProductPrice() {
        return newProductPrice;
    }
    
    public Integer getCurrentProductId() {
        return currentProductId;
    }

    public void setCurrentProductId(Integer currentProductId) {
        this.currentProductId = currentProductId;
          try {
            Product p = employee.getProduct(currentProductId);
            this.newProductID = p.getProductId();
            this.newProductName = p.getProductName();
            this.newProductPrice = p.getProductPrice();
          }catch(EJBException e) {
            this.currentProductId = null;
        }
    }
    
     public void resetProductForm() {
        this.newProductID = null;
        this.newProductName = null;
        this.newProductPrice= null;
    }
    
    public void updateProduct() {
        try {
            employee.updateProduct(
                    newProductID,
                    newProductName,
                    newProductPrice);

            logger.info(
                    "updated product with product ID " + newProductID
                    + "; set: name " + newProductName + ", price "
                    + newProductPrice + ".");
            resetProductForm();
            currentProductId = null;
        } catch (Exception e) {
            logger.warning("Problem updating customer in newCustomer.");
        }
    }
    
    public void saveProductForm() {
        if(currentProductId == null) {
            createProduct();
        }else {
            updateProduct();
        }
    }
    
    public void cancelProductForm() {
        currentProductId = null;
        resetProductForm();
    }
    
    public boolean isProductListVisible() {
        return currentProductId == null;
    }

    public boolean isCreatingNewProduct() {
        return currentProductId == null;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login (){
        if(username.equals("employee") && password.equals("password")){
            System.out.print("username and password are correct");
            return "employeeMain.xhtml";
        }
                    else if(username.equals("") && password.equals("")){
                           System.out.print("Please enter correct username and password");
                              return "error.xhtml";     
                            }
                    else{
                       System.out.print("username and password are not correct"); 
                       return "error.xhtml";
                    }
        }

    public String logout() {
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    return "login.xhtml";
        }

}



