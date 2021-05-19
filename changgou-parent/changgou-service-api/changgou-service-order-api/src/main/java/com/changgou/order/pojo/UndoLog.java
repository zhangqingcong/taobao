package com.changgou.order.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Table(name = "undo_log")
public class UndoLog implements Serializable {
    @Id
    @Column(name = "id")
    private BigInteger id;
    @Column(name = "branch_id")
    private BigInteger branchId;
    @Column(name = "xid")
    private String xid;
    @Column(name = "rollback_info")
    private Long rollbackInfo;
    @Column(name = "log_status")
    private Integer logStatus;
    @Column(name = "log_created")
    private Date logCreated;
    @Column(name = "log_modified")
    private Date logModified;
    @Column(name = "ext")
    private String ext;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getBranchId() {
        return branchId;
    }

    public void setBranchId(BigInteger branchId) {
        this.branchId = branchId;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    public Long getRollbackInfo() {
        return rollbackInfo;
    }

    public void setRollbackInfo(Long rollbackInfo) {
        this.rollbackInfo = rollbackInfo;
    }

    public Integer getLogStatus() {
        return logStatus;
    }

    public void setLogStatus(Integer logStatus) {
        this.logStatus = logStatus;
    }

    public Date getLogCreated() {
        return logCreated;
    }

    public void setLogCreated(Date logCreated) {
        this.logCreated = logCreated;
    }

    public Date getLogModified() {
        return logModified;
    }

    public void setLogModified(Date logModified) {
        this.logModified = logModified;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
