/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.aservices.ftk6.dc.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author andy
 */
@Entity
@Table(name = "account")
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id"),
    @NamedQuery(name = "Account.findByBalance", query = "SELECT a FROM Account a WHERE a.balance = :balance")})
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "balance", nullable = false, precision = 18, scale = 2)
    private BigDecimal balance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beneficiary")
    private Collection<AccountMovement> accountMovementsCollection;
    @OneToMany(mappedBy = "remitter")
    private Collection<AccountMovement> accountMovementsCollection1;
    @JoinColumn(name = "person", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Person person;

    public Account() {
    }

    public Account(Integer id) {
        this.id = id;
    }

    public Account(Integer id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    public void IncreaseBalance(BigDecimal amount) {
        changeBalance(amount);
    }
    
    public void DecreaseBalance(BigDecimal amount) {
        changeBalance(amount);
    }
    
    /**
     * 
     */
    private void changeBalance(BigDecimal delta) {
       BigDecimal newBalance;
       if (delta.signum() == 1) {
           newBalance = this.balance.add(delta);
       } else if (delta.signum() == -1) {
           newBalance = this.balance.subtract(delta);
       } else {
           newBalance = this.balance;
       }
       this.balance = newBalance;
    }

    public Collection<AccountMovement> getAccountMovementsCollection() {
        return accountMovementsCollection;
    }

    public void setAccountMovementsCollection(Collection<AccountMovement> accountMovementsCollection) {
        this.accountMovementsCollection = accountMovementsCollection;
    }

    public Collection<AccountMovement> getAccountMovementsCollection1() {
        return accountMovementsCollection1;
    }

    public void setAccountMovementsCollection1(Collection<AccountMovement> accountMovementsCollection1) {
        this.accountMovementsCollection1 = accountMovementsCollection1;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "info.aservices.ftk6.dc.Account[ id=" + id + " ]";
    }
    
}