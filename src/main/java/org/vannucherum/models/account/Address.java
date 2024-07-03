package org.vannucherum.models.account;

public class Address {
    private String name;
    private String line1;
    private String line2;
    private String phone;

    public Address(String name, String line1, String line2, String phone) {
        this.name = name;
        this.line1 = line1;
        this.line2 = line2;
        this.phone = phone;
    }

    public String getFullAddress() {
        return "\n" + name + "\n" + line1 + "\n" + line2 + "\n" + phone + "\n";
    }
}
