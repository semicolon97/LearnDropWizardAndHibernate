
package com.javaeeeee.dwstart.core;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;

@Entity
@Table(name = "employees")
@NamedQueries({
        @NamedQuery(name = "com.javaeeeee.dwstart.core.Employee.findAll",
                query = "select e from Employee e")
        ,
        @NamedQuery(name = "com.javaeeeee.dwstart.core.Employee.findByName",
                query = "select e from Employee e "
                        + "where e.firstName like :name "
                        + "or e.lastName like :name")
})
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "e_position")
    private String position;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String e_mail;

//    @OneToMany(mappedBy = "employee")
//    private Set<Account> accounts;

    public Employee() {
    }

    public Employee(String firstName,
                    String lastName, String position, String phone, String e_mail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.phone = phone;
        this.e_mail = e_mail;
    }

    // Auto-generated equald, hashCode, getters and setters.
//    @Override
//    public int hashCode() {
//        int hash = 3;
//        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
//        hash = 29 * hash + Objects.hashCode(this.firstName);
//        hash = 29 * hash + Objects.hashCode(this.lastName);
//        hash = 29 * hash + Objects.hashCode(this.position);
//        hash = 29 * hash + Objects.hashCode(this.phone);
//        hash = 29 * hash + Objects.hashCode(this.e_mail);
//        return hash;
//    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Employee other = (Employee) obj;
//        if (this.id != other.id) {
//            return false;
//        }
//        if (!Objects.equals(this.firstName, other.firstName)) {
//            return false;
//        }
//        if (!Objects.equals(this.lastName, other.lastName)) {
//            return false;
//        }
//        if (!Objects.equals(this.position, other.position)) {
//            return false;
//        }
//        if (!Objects.equals(this.phone, other.phone)) {
//            return false;
//        }
//        if (!Objects.equals(this.e_mail, other.e_mail)) {
//            return false;
//        }
//        return true;
//    }

    //Getters and setters.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

//    public Set<Account> getAccounts() {
//        return accounts;
//    }
//
//    public void setAccounts(Set<Account> accounts) {
//        this.accounts = accounts;
//    }
}
