package com.aninfo.model;

import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cbu;

    private Double balance;


    /*@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "transactions", joinColumns = @JoinColumn(name = "account_cbu"))
    @Column(name = "transaction", nullable = false)
    private List<String> transactionList;*/

    public Account(){
    }

    public Account(Double balance) {
        this.balance = balance;
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /*public void addTransaction(int type, Double sum) {
        String str = new String();
        if (type == 0) {str += "deposit ";} else {str+="withdraw ";}
        str+= sum;
        this.transactionList.add(str);
    }

    public List<String> getAllTransctions() {
        return transactionList;
    }*/

}
