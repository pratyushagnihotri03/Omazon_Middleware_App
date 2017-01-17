/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order.ejb;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import order.entity.Customer;
import order.entity.Product;
import order.entity.Employee;

/**
 *
 * @author manisha luthra
 */
@Stateful
public class EmployeeBean {
    private static Logger logger = Logger.getLogger("order.ejb.EmployeeBean");
    @PersistenceContext
    private EntityManager em;

    public List<Product> getProducts() {
         try {
            return (List<Product>) em.createNamedQuery("findAllProducts")
                                   .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void createProduct(
        Integer productId,
        String productName,
        Integer productPrice) {
        try {
            Product product = new Product(productId, productName,productPrice);
            em.persist(product);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

     public void updateProduct(
        Integer productId,
        String productName,
        Integer productPrice){
        try {
            Product product = getProduct(productId);
            product.setProductName(productName);
            product.setProductPrice(productPrice);
            em.persist(product);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public Product getProduct(Integer productId) {
        try {
            return em.find(Product.class, productId);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void createEmployee(
            Integer employeeId,
            String name
        ) {
        try {
            Employee employee = new Employee(employeeId, name);
            em.persist(employee);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
}
