package org.vannucherum.models.account;

import org.vannucherum.enums.AccountStatus;

public class Account {
    private String name;
    private String email;
    private String password;
    private String phone;
    private AccountStatus status;

    public Account(String name, String email, String password, String phone, AccountStatus status) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public boolean login(String password) {
        boolean authenticated = this.password.equals(password);
        if (!authenticated) {
            System.out.println("Authentication failed for account: " + this.email);
        }
        else {
            System.out.println("Logged in to account: " + this.email);
        }
        return authenticated;
    }

    public void logout() {
        System.out.println("Logged out account: " + this.email);
    }
 }
