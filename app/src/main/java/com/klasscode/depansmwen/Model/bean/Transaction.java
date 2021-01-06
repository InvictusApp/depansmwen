package com.klasscode.depansmwen.Model.bean;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {

    private int id;
    private int idAccount;
    private String type;
    private long numberTransferAccount;
    private long amount;
    private Date createAt;
    private Date updateAt;

    public Transaction() {
    }

    public Transaction(int id, int idAccount, String type, long numberTransferAccount, long amount, Date createAt, Date updateAt) {
        this.id = id;
        this.idAccount = idAccount;
        this.type = type;
        this.numberTransferAccount = numberTransferAccount;
        this.amount = amount;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Transaction(int idAccount, String type, long numberTransferAccount, long amount) {
        this.idAccount = idAccount;
        this.type = type;
        this.numberTransferAccount = numberTransferAccount;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getNumberTransferAccount() {
        return numberTransferAccount;
    }

    public void setNumberTransferAccount(long numberTransferAccount) {
        this.numberTransferAccount = numberTransferAccount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
