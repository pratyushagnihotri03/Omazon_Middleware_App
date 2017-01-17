/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order.entity;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author patrick.welzel
 */
@Entity
@Table(name = "PERSISTENCE_ORDER_CUSTOMER")
@NamedQuery(name = "findAllCustomers", query = "SELECT c FROM Customer c "
+ "ORDER BY c.customerId")
public class Customer implements java.io.Serializable {
    private Integer customerId;
    private String name;
    private String address;
    private String emailId;
    
    protected Customer () {
        
    }
    
    public Customer (Integer customerId, String name, String address, String emailId) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.emailId = emailId;
    }
    
    @Id
    public Integer getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
}
