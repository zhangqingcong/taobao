package com.changgou.order.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_order_log")
public class OrderLog implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "operater")
    private String opereter;
    @Column(name = "operater_time")
    private Date operaterTime;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "order_status")
    private Character orderStatus;
    @Column(name = "pay_status")
    private Character payStatus;
    @Column(name = "consign_status")
    private Character consignStatus;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "money")
    private Integer money;
    @Column(name = "username")
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpereter() {
        return opereter;
    }

    public void setOpereter(String opereter) {
        this.opereter = opereter;
    }

    public Date getOperaterTime() {
        return operaterTime;
    }

    public void setOperaterTime(Date operaterTime) {
        this.operaterTime = operaterTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Character getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Character orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Character getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Character payStatus) {
        this.payStatus = payStatus;
    }

    public Character getConsignStatus() {
        return consignStatus;
    }

    public void setConsignStatus(Character consignStatus) {
        this.consignStatus = consignStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
