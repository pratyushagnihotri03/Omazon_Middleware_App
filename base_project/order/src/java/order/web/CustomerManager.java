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
import order.ejb.CustomerBean;
import order.entity.Customer;

/**
 *
 * @author patrick.welzel
 */
@ManagedBean
@SessionScoped
public class CustomerManager {
    private static Logger logger = Logger.getLogger("order.web.CustomerManager");
    private List<Customer> customers;
    @EJB
    private CustomerBean customer;
    private Integer newCustomerId;
    private String newCustomerName;
    private String newCustomerEmailId;
    private String newCustomerAddress;
    private Integer currentCustomerId;

    public List<Customer> getCustomers() {
        try {
            this.customers = customer.getCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }

    public Integer getNewCustomerId() {
        return newCustomerId;
    }

    public String getNewCustomerName() {
        return newCustomerName;
    }

    public String getNewCustomerEmailId() {
        return newCustomerEmailId;
    }

    public String getNewCustomerAddress() {
        return newCustomerAddress;
    }

    public void setNewCustomerId(Integer newCustomerId) {
        this.newCustomerId = newCustomerId;
    }

    public void setNewCustomerName(String newCustomerName) {
        this.newCustomerName = newCustomerName;
    }

    public void setNewCustomerEmailId(String newCustomerEmailId) {
        this.newCustomerEmailId = newCustomerEmailId;
    }

    public void setNewCustomerAddress(String newCustomerAddress) {
        this.newCustomerAddress = newCustomerAddress;
    }
    
    public void resetCustomerForm() {
        this.newCustomerId = null;
        this.newCustomerAddress = null;
        this.newCustomerName = null;
        this.newCustomerEmailId = null;
    }
    
    public void createCustomer() {
        try {
            customer.createCustomer(
                    newCustomerId,
                    newCustomerName,
                    newCustomerAddress,
                    newCustomerEmailId);

            logger.info(
                    "Created new customer with customer ID " + newCustomerId
                    + ", name " + newCustomerName + ", address "
                    + newCustomerAddress + ", and email ID"
                    + newCustomerEmailId + ".");
            resetCustomerForm();
        } catch (Exception e) {
            logger.warning("Problem creating customer in newCustomer.");
        }
    }

    public void updateCustomer() {
        try {
            customer.updateCustomer(
                    newCustomerId,
                    newCustomerName,
                    newCustomerAddress,
                    newCustomerEmailId);

            logger.info(
                    "updated customer with customer ID " + newCustomerId
                    + "; set: name " + newCustomerName + ", address "
                    + newCustomerAddress + ", and email ID"
                    + newCustomerEmailId + ".");
            resetCustomerForm();
            currentCustomerId = null;
        } catch (Exception e) {
            logger.warning("Problem updating customer in newCustomer.");
        }
    }
    
    public void saveCustomerForm() {
        if(currentCustomerId == null) {
            createCustomer();
        }else {
            updateCustomer();
        }
    }
    
    public void cancelCustomerForm() {
        currentCustomerId = null;
        resetCustomerForm();
    }
    
    public boolean isCustomerListVisible() {
        return currentCustomerId == null;
    }

    public boolean isCreatingNewCustomer() {
        return currentCustomerId == null;
    }

    public void setCurrentCustomerId(Integer currentCustomerId) {
        this.currentCustomerId = currentCustomerId;
        try {
            Customer c = customer.getCustomer(currentCustomerId);
            this.newCustomerId = c.getCustomerId();
            this.newCustomerName = c.getName();
            this.newCustomerEmailId = c.getEmailId();
            this.newCustomerAddress = c.getAddress();
        }catch(EJBException e) {
            this.currentCustomerId = null;
        }
    }
    
}
