package com.changgou.order.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Table(name = "tb_return_order")
public class ReturnOrder implements Serializable {
    @Id
    @Column(name = "id")
    private BigInteger id;
    @Column(name = "order_id")
    private BigInteger orderId;
    @Column(name = "apply_time")
    private Date applyTime;
    @Column(name = "user_id")
    private BigInteger userId;
    @Column(name = "user_account")
    private String userAccount;
    @Column(name = "link_man")
    private String linkMan;
    @Column(name = "linkman_mobile")
    private String linkmanMobile;
    @Column(name = "type")
    private Character type;
    @Column(name = "return_money")
    private Integer returnMoney;
    @Column(name = "is_return_freight")
    private Character isReturnFreight;
    @Column(name = "status")
    private Character status;
    @Column(name = "dispose_time")
    private Date disposeTime;
    @Column(name = "return_cause")
    private Integer returnCause;
    @Column(name = "evidence")
    private String evidence;
    @Column(name = "description")
    private String description;
    @Column(name = "remark")
    private String remark;
    @Column(name = "admin_id")
    private Integer adminId;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkmanMobile() {
        return linkmanMobile;
    }

    public void setLinkmanMobile(String linkmanMobile) {
        this.linkmanMobile = linkmanMobile;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public Integer getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(Integer returnMoney) {
        this.returnMoney = returnMoney;
    }

    public Character getIsReturnFreight() {
        return isReturnFreight;
    }

    public void setIsReturnFreight(Character isReturnFreight) {
        this.isReturnFreight = isReturnFreight;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Date getDisposeTime() {
        return disposeTime;
    }

    public void setDisposeTime(Date disposeTime) {
        this.disposeTime = disposeTime;
    }

    public Integer getReturnCause() {
        return returnCause;
    }

    public void setReturnCause(Integer returnCause) {
        this.returnCause = returnCause;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}
