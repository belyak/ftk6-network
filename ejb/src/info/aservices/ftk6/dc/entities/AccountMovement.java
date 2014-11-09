/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.aservices.ftk6.dc.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author andy
 */
@Entity
@Table(name = "account_movements")
@NamedQueries({
    @NamedQuery(name = "AccountMovement.findAll", query = "SELECT a FROM AccountMovement a"),
    @NamedQuery(name = "AccountMovement.findById", query = "SELECT a FROM AccountMovement a WHERE a.id = :id"),
    @NamedQuery(name = "AccountMovement.findByTs", query = "SELECT a FROM AccountMovement a WHERE a.ts = :ts"),
    @NamedQuery(name = "AccountMovement.findByAmount", query = "SELECT a FROM AccountMovement a WHERE a.amount = :amount"),
    @NamedQuery(name = "AccountMovement.findByDescription", query = "SELECT a FROM AccountMovement a WHERE a.description = :description"),
    @NamedQuery(name = "AccountMovement.findByIsRecharge", query = "SELECT a FROM AccountMovement a WHERE a.isRecharge = :isRecharge")})
public class AccountMovement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ts", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 2048)
    private String description;
    @Basic(optional = false)
    @Column(name = "is_recharge", nullable = false)
    private boolean isRecharge;
    @JoinColumn(name = "beneficiary_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Account beneficiary;
    @JoinColumn(name = "remitter_id", referencedColumnName = "id")
    @ManyToOne
    private Account remitter;

    public AccountMovement() {
    }

    public AccountMovement(Integer id) {
        this.id = id;
    }
    
    public AccountMovement(boolean isRecharge, Date ts) {
        this.isRecharge = isRecharge;
        this.ts = ts;
    }

    public AccountMovement(Integer id, Date ts, BigDecimal amount, String description, boolean isRecharge) {
        this.id = id;
        this.ts = ts;
        this.amount = amount;
        this.description = description;
        this.isRecharge = isRecharge;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsRecharge() {
        return isRecharge;
    }

    public void setIsRecharge(boolean isRecharge) {
        this.isRecharge = isRecharge;
    }

    public Account getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Account beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Account getRemitter() {
        return remitter;
    }

    public void setRemitter(Account remitter) {
        this.remitter = remitter;
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
        if (!(object instanceof AccountMovement)) {
            return false;
        }
        AccountMovement other = (AccountMovement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "info.aservices.ftk6.dc.AccountMovements[ id=" + id + " ]";
    }
    
}
