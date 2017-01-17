/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order.ejb;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import order.entity.Customer;
import order.entity.LineItem;
import order.entity.Order;
import order.entity.Product;

/**
 *
 * @author patrick.welzel
 */
@Stateful
@LocalBean
public class CustomerBean {
    private static Logger logger = Logger.getLogger("order.ejb.CustomerBean");
    @PersistenceContext
    private EntityManager em;

    public List<Customer> getCustomers() {
        try {
            return (List<Customer>) em.createNamedQuery("findAllCustomers")
                                   .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void createCustomer(
        Integer customerId,
        String name,
        String addres,
        String emailId) {
        try {
            Customer customer = new Customer(customerId, name,addres, emailId);
            em.persist(customer);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void updateCustomer(
        Integer customerId,
        String name,
        String addres,
        String emailId) {
        try {
            Customer customer = getCustomer(customerId);
            customer.setName(name);
            customer.setAddress(addres);
            customer.setEmailId(emailId);
            em.persist(customer);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public Customer getCustomer(Integer customerId) {
        try {
            return em.find(Customer.class, customerId);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public Integer getNextOrderId() {
         try {
            return (Integer) em.createNamedQuery("findAllProducts")
                    .getFirstResult();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void createOrder(
        Customer c,
        Map<Product, Integer> items
        ) {
        Order o = new Order(3, c, 'n');
        for(Map.Entry<Product, Integer> item : items.entrySet()) {
            LineItem litem = new LineItem(o, item.getValue(), item.getKey());
            o.addLineItem(litem);
        }
        em.persist(o);
    }
    
}
