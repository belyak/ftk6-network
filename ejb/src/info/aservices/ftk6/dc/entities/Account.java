package info.aservices.ftk6.dc.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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


@Entity
@Table(name = "account")
@NamedQueries({
    @NamedQuery(
            name = "Account.findAll",
            query = "SELECT a FROM Account a"),
    @NamedQuery(
            name = "Account.findById",
            query = "SELECT a FROM Account a WHERE a.id = :id"),
    @NamedQuery(
            name = "Account.findByBalance",
            query = "SELECT a FROM Account a WHERE a.balance = :balance")})
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "balance", nullable = false, precision = 18, scale = 2)
    private BigDecimal balance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beneficiary")
    private Collection<AccountMovement> beneficiaryAccountMovementsCollection;
    @OneToMany(mappedBy = "remitter")
    private Collection<AccountMovement> remitterAccountMovementsCollection;
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

    /**
     * Увеличение баланса на указанную величину.
     *
     * @param amount величина изменения
     */
    public void IncreaseBalance(BigDecimal amount) {
        changeBalance(amount);
    }

    /**
     * Уменьшение баланса на указанную величину.
     *
     * @param amount величина изменения
     */
    public void DecreaseBalance(BigDecimal amount) {
        changeBalance(amount.multiply(new BigDecimal(-1)));
    }

    /**
     * Изменение баланса на указанную величину
     *
     * @param delta величина изменения
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

    public Collection<AccountMovement> getBeneficiaryAccountMovementsCollection() {
        return beneficiaryAccountMovementsCollection;
    }

    public void setBeneficiaryAccountMovementsCollection(Collection<AccountMovement> accountMovementsCollection) {
        this.beneficiaryAccountMovementsCollection = accountMovementsCollection;
    }

    public Collection<AccountMovement> getRemitterAccountMovementsCollection() {
        return remitterAccountMovementsCollection;
    }

    public void setRemitterAccountMovementsCollection(Collection<AccountMovement> accountMovementsCollection) {
        this.remitterAccountMovementsCollection = accountMovementsCollection;
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
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "info.aservices.ftk6.dc.Account[ id=" + id + " ]";
    }
    
}
