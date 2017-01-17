/*
 * Copyright 2010 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package order.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@IdClass(order.entity.LineItemKey.class)
@Entity
@Table(name = "PERSISTENCE_ORDER_LINEITEM")
@NamedQueries({
    @NamedQuery(name = "findAllLineItems",query = "SELECT l "
    + "FROM LineItem l")
    , @NamedQuery(name = "findLineItemsByOrderId", query = "SELECT l FROM LineItem l "
    + "WHERE l.order.orderId = :orderId " + "ORDER BY l.itemId")
    , @NamedQuery(name = "findLineItemById", query = "SELECT DISTINCT l FROM LineItem l "
    + "WHERE l.itemId = :itemId AND l.order.orderId = :orderId")
})
public class LineItem implements java.io.Serializable {
    private Order order;
    private Product product;
    private int itemId;
    private int quantity;

    public LineItem() {
    }

    public LineItem(
        Order order,
        int quantity,
        Product product) {
        this.order = order;
        this.itemId = order.getNextId();
        this.quantity = quantity;
        this.product = product;
    }

    @Id
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @JoinColumn(name = "PRODUCT")
    @ManyToOne
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "ORDERID")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
