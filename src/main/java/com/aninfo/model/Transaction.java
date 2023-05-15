package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int type; //0 is for deposit and 1 is for withdraw
    private Long cbu;
    private Double sum;

    public Transaction(int type, Double sum, Long cbu) {
        this.type = type;
        this.sum = sum;
        this.cbu = cbu;
    }

    public Long getTransactionCbu() {
        return cbu;
    }

    public Long getTransactionId() {
        return id;
    }

    public Double getSum() {
        return sum;
    }

    public int getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Transaction() {
    }
}
