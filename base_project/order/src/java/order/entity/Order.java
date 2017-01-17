/*
 * Copyright 2010 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package order.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;
import javax.persistence.Transient;


@Entity
@Table(name = "PERSISTENCE_ORDER_ORDER")
@NamedQueries({
    @NamedQuery(name = "findAllOrders", query = "SELECT o FROM Order o "
    + "ORDER BY o.orderId"),
    @NamedQuery(name = "getLatestOrderId", query = "SELECT MAX(o.orderId) FROM Order o")
})
public class Order implements java.io.Serializable {
    private Collection<LineItem> lineItems;
    private Integer orderId;
    private char status;
    private Customer customer;

    public Order() {
        this.lineItems = new ArrayList<LineItem>();
    }

    public Order(
        Integer orderId,
        Customer customer,
        char status) {
        this.orderId = orderId;
        this.status = status;
        this.customer = customer;
        this.lineItems = new ArrayList<LineItem>();
    }

    @Id
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @OneToMany(cascade = ALL, mappedBy = "order")
    public Collection<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(Collection<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public void addLineItem(LineItem lineItem) {
        this.getLineItems()
            .add(lineItem);
    }

    public void addLineItems(Collection<LineItem> lineItems) {
        this.getLineItems().addAll(lineItems);
    }

    public double calculateAmmount() {
        double ammount = 0;
        Collection<LineItem> items = getLineItems();

        for (Iterator it = items.iterator(); it.hasNext();) {
            LineItem item = (LineItem) it.next();
            Product product = item.getProduct();
            ammount += (product.getProductPrice() * item.getQuantity());
        }

        return ammount;
    }

    @Transient
    public int getNextId() {
        return this.lineItems.size() + 1;
    }
}
