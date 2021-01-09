package com.klasscode.depansmwen.Model.bean;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {

    private int id;
    private int idUser;
    private String bankName;
    private long numberAccount;
    private long balance;
    private boolean isActive;
    private String createAt;
    private String updateAt;

    public Account() {
    }

    public Account(int id, int idUser, String bankName, long numberAccount, long balance, boolean isActive, String createAt, String updateAt) {
        this.id = id;
        this.idUser = idUser;
        this.bankName = bankName;
        this.numberAccount = numberAccount;
        this.balance = balance;
        this.isActive = isActive;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Account(String bankName, long numberAccount, long balance, boolean isActive) {
        this.bankName = bankName;
        this.numberAccount = numberAccount;
        this.balance = balance;
        this.isActive = isActive;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public long getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(long numberAccount) {
        this.numberAccount = numberAccount;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
