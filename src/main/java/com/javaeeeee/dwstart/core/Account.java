package com.javaeeeee.dwstart.core;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountID", unique = true, nullable = false)
    private Integer accountId;

    @Column(name = "AccountNumber", unique = true, nullable = false, length = 255)
    private String accountNumber;

    @Column(name = "employeeId", unique = true, nullable = false)
    private Integer employeeId;

    @ManyToOne
    @JoinColumn(name="employeeId", referencedColumnName = "id")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}