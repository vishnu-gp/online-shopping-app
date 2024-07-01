package org.vannucherum.models.account;

import org.vannucherum.enums.AccountStatus;
import org.vannucherum.utils.AppLogger;

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
            AppLogger.logError(String.format("Authentication failed for account: %s", this.email));
        }
        else {
            AppLogger.logInfo(String.format("Logged in to account: %s", this.email));
        }
        return authenticated;
    }

    public void logout() {
        AppLogger.logInfo(String.format("Logged out account: %s", this.email));
    }
 }
