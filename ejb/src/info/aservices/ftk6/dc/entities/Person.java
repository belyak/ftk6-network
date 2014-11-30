package info.aservices.ftk6.dc.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 * @author andy
 */
@Entity
@Table(name = "person")
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"),
    @NamedQuery(name = "Person.findByFirstName", query = "SELECT p FROM Person p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Person.findByPatronymicName", query = "SELECT p FROM Person p WHERE p.patronymicName = :patronymicName"),
    @NamedQuery(name = "Person.findByLastName", query = "SELECT p FROM Person p WHERE p.lastName = :lastName")})
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "first_name", nullable = false, length = 200)
    private String firstName;
    @Basic(optional = false)
    @Column(name = "patronymic_name", nullable = false, length = 200)
    private String patronymicName;
    @Basic(optional = false)
    @Column(name = "last_name", nullable = false, length = 200)
    private String lastName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Collection<Account> accountCollection;

    public Person() {
    }

    public Person(Integer id) {
        this.id = id;
    }

    public Person(Integer id, String firstName, String patronymicName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.patronymicName = patronymicName;
        this.lastName = lastName;
    }

    public Person(String firstName, String patronymicName, String lastName) {
        this.firstName = firstName;
        this.patronymicName = patronymicName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.getFullName(false);
    }
    
    public String getFullName(boolean withInitials) {
        if (!withInitials) {
            return lastName + ' ' + firstName + ' ' + patronymicName;
        } else {
            StringBuilder sb = new StringBuilder(lastName);
            if (!firstName.isEmpty()) {
                sb.append(" ");
                sb.append(firstName.substring(0, 1));
                sb.append(".");
            }
            if (!patronymicName.isEmpty()) {
                sb.append(patronymicName.substring(0, 1));
                sb.append(".");
            }
            return sb.toString();
        }
    }
    
    public Collection<Account> getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection) {
        this.accountCollection = accountCollection;
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
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "info.aservices.ftk6.dc.Person[ id=" + id + " ]";
    }
    
}
