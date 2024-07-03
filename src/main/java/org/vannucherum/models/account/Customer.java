package org.vannucherum.models.account;

import org.vannucherum.enums.AccountStatus;

import java.util.List;

public class Customer extends Account {
    private List<Address> addressList;

    public Customer(String name, String email, String password, String phone, AccountStatus status, List<Address> addressList) {
        super(name, email, password, phone, status);
        this.addressList = addressList;
    }

    public List<Address> getAddressList() {
        return addressList;
    }
}
