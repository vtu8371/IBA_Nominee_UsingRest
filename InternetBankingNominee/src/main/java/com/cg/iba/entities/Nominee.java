package com.cg.iba.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Nominee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long nomineeId;
    private String name;
    private String govtId;
    private String govtIdType;
    private String phoneNo;
    @Enumerated(EnumType.STRING)
    private Relation relation;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountId")
    private Account bankAccount;

    /*
     * getters and setters for private fields
     */
    public long getNomineeId() {
        return nomineeId;
    }

    public void setNomineeId(long nomineeId) {
        this.nomineeId = nomineeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGovtId() {
        return govtId;
    }

    public void setGovtId(String govtId) {
        this.govtId = govtId;
    }

    public String getGovtIdType() {
        return govtIdType;
    }

    public void setGovtIdType(String govtIdType) {
        this.govtIdType = govtIdType;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public Account getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Account bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * Default Constructor
     */
    public Nominee() {
        super();
    }

    /**
     * Parameterized Constructor
     * 
     * @param nomineeId
     * @param name
     * @param govtId
     * @param govtIdType
     * @param phoneNo
     * @param relation
     * @param bankAccount
     */
    public Nominee(long nomineeId, String name, String govtId, String govtIdType, String phoneNo, Relation relation, Account bankAccount) {
        super();
        this.nomineeId = nomineeId;
        this.name = name;
        this.govtId = govtId;
        this.govtIdType = govtIdType;
        this.phoneNo = phoneNo;
        this.relation = relation;
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "Nominee [nomineeId=" + nomineeId + ", name=" + name + ", govtId=" + govtId + ", govtIdType=" + govtIdType + ", phoneNo=" + phoneNo + ", relation=" + relation
                + ", bankAccount=" + bankAccount + "]";
    }

}
