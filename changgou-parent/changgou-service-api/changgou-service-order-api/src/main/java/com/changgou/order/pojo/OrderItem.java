package com.changgou.order.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "category_id1")
    private Integer categoryId1;
    @Column(name = "category_id2")
    private Integer categoryId2;
    @Column(name = "category_id3")
    private Integer categoryId3;
    @Column(name = "spu_id")
    private BigInteger spuId;
    @Column(name = "sku_id")
    private BigInteger skuId;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Integer price;
    @Column(name = "num")
    private Integer num;
    @Column(name = "money")
    private Integer money;
    @Column(name = "pay_money")
    private Integer payMoney;
    @Column(name = "image")
    private String image;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "post_fee")
    private Integer postFee;
    @Column(name = "is_return")
    private Character isReturn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCategoryId1() {
        return categoryId1;
    }

    public void setCategoryId1(Integer categoryId1) {
        this.categoryId1 = categoryId1;
    }

    public Integer getCategoryId2() {
        return categoryId2;
    }

    public void setCategoryId2(Integer categoryId2) {
        this.categoryId2 = categoryId2;
    }

    public Integer getCategoryId3() {
        return categoryId3;
    }

    public void setCategoryId3(Integer categoryId3) {
        this.categoryId3 = categoryId3;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getPostFee() {
        return postFee;
    }

    public void setPostFee(Integer postFee) {
        this.postFee = postFee;
    }

    public Character getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(Character isReturn) {
        this.isReturn = isReturn;
    }
}
