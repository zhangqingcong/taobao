package com.changgou.order.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_order")
public class Order implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "total_num")
    private Integer totalNum;
    @Column(name = "total_money")
    private Integer totalMoney;
    @Column(name = "pre_money")
    private Integer preMoney;
    @Column(name = "post_fee")
    private Integer postFee;
    @Column(name = "pay_money")
    private Integer payMoney;
    @Column(name = "pay_type")
    private String payType;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "pay_time")
    private Date payTime;
    @Column(name = "consign_time")
    private Date consignTime;
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "close_time")
    private Date closeTime;
    @Column(name = "shipping_name")
    private String shippingName;
    @Column(name = "shipping_code")
    private String shippingCode;
    @Column(name = "user_name")
    private String username;
    @Column(name = "buyer_message")
    private String buyerMessage;
    @Column(name = "buyer_rate")
    private Character buyerRate;
    @Column(name = "receiver_contact")
    private String receiverContact;
    @Column(name = "receiver_mobile")
    private String receiverMobile;
    @Column(name = "receiver_address")
    private String receiverAddress;
    @Column(name = "source_type")
    private Character sourceType;
    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "order_status")
    private Character orderStatus;
    @Column(name = "pay_status")
    private Character payStatus;
    @Column(name = "consign_status")
    private Character consignStatus;
    @Column(name = "is_delete")
    private Character isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getPreMoney() {
        return preMoney;
    }

    public void setPreMoney(Integer preMoney) {
        this.preMoney = preMoney;
    }

    public Integer getPostFee() {
        return postFee;
    }

    public void setPostFee(Integer postFee) {
        this.postFee = postFee;
    }

    public Integer getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Integer payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getConsignTime() {
        return consignTime;
    }

    public void setConsignTime(Date consignTime) {
        this.consignTime = consignTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public Character getBuyerRate() {
        return buyerRate;
    }

    public void setBuyerRate(Character buyerRate) {
        this.buyerRate = buyerRate;
    }

    public String getReceiverContact() {
        return receiverContact;
    }

    public void setReceiverContact(String receiverContact) {
        this.receiverContact = receiverContact;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Character getSourceType() {
        return sourceType;
    }

    public void setSourceType(Character sourceType) {
        this.sourceType = sourceType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public Character getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Character isDelete) {
        this.isDelete = isDelete;
    }
}
