/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author manisha luthra
 */
@Entity
@Table(name = "PERSISTENCE_ORDER_EMPLOYEE")
@NamedQuery(name = "findAllEmployee", query = "SELECT e FROM Employee e "
+ "ORDER BY e.employeeID")
public class Employee implements Serializable {
    Integer employeeID;
    String employeeName;

    public Employee() {
    }
     
    public Employee(Integer employeeID, String employeeName) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
    }
   
    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    @Id
    public Integer getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }
    
}
