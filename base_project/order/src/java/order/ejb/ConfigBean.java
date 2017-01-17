/*
 * Copyright 2010 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package order.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;


/**
 *
 * @author ian
 */
@Singleton
@Startup
public class ConfigBean {
    @EJB
    private CustomerBean customer;
    @EJB
    private EmployeeBean employee;

    @PostConstruct
    public void createData() {
        employee.createProduct(123, "Food", 5);
        employee.createProduct(142, "Shoes", 9001);
        
        customer.createCustomer(1, "Testuser Family", "Hypodrive 42", "test@family.hypo-drive-habitant");
        customer.createCustomer(2, "Java EE Developer", "Beyond Hell, Above Heaven", "vol@be.at");
        
        employee.createEmployee(42, "Employee of the Years 1795-2023");
        
    }

    @PreDestroy
    public void deleteData() {
    }
}
