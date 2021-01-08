package com.klasscode.depansmwen.Model.bean;

import java.util.Date;

public class Cash {
    private int id;
    private int idUser;
    private String description;
    private double amount;
    private Date createAt;
    private Date updateAt;

    public Cash() {
    }

    public Cash(int idUser, String description, double amount, Date createAt, Date updateAt) {
        this.idUser = idUser;
        this.description = description;
        this.amount = amount;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Cash(int id, int idUser, String description, double amount, Date createAt, Date updateAt) {
        this.id = id;
        this.idUser = idUser;
        this.description = description;
        this.amount = amount;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Cash{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
