/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order.entity;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author manisha luthra
 */
@Entity
@Table(name = "PERSISTENCE_ORDER_PRODUCT")
@NamedQuery(name = "findAllProducts", query = "SELECT p FROM Product p "
+ "ORDER BY p.productId")
public class Product implements Serializable {
 
 Integer productId;
 String productName;
 Integer productPrice;
    public Product() {
    }

    public Product(Integer productId, String productName, Integer productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }
    
    @Id
    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }
   
    
}
