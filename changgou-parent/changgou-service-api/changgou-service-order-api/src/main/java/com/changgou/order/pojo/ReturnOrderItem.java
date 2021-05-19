package com.changgou.order.pojo;

import org.mockito.internal.junit.MismatchReportingTestListener;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;
@Table(name = "tb_return_order_item")
public class ReturnOrderItem implements Serializable {
    @Id
    @Column(name = "id")
    private BigInteger id;
    @Column(name = "category_id")
    private BigInteger categoryId;
    @Column(name = "spu_id")
    private BigInteger spuId;
    @Column(name = "sku_id")
    private BigInteger skuId;
    @Column(name = "order_id")
    private BigInteger orderId;
    @Column(name = "order_item_id")
    private BigInteger orderItemId;
    @Column(name = "return_order_id")
    private BigInteger returnOrderId;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private Integer price;
    @Column(name = "num")
    private Integer num;
    @Column(name = "money")
    private Integer money;
    @Column(name = "pay_monye")
    private Integer payMoney;
    @Column(name = "image")
    private String image;
    @Column(name = "weight")
    private Integer weight;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(BigInteger categoryId) {
        this.categoryId = categoryId;
    }

    public BigInteger getSpuId() {
        return spuId;
    }

    public void setSpuId(BigInteger spuId) {
        this.spuId = spuId;
    }

    public BigInteger getSkuId() {
        return skuId;
    }

    public void setSkuId(BigInteger skuId) {
        this.skuId = skuId;
    }

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    public BigInteger getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(BigInteger orderItemId) {
        this.orderItemId = orderItemId;
    }

    public BigInteger getReturnOrderId() {
        return returnOrderId;
    }

    public void setReturnOrderId(BigInteger returnOrderId) {
        this.returnOrderId = returnOrderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Integer payMoney) {
        this.payMoney = payMoney;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
