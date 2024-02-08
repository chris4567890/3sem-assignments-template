package org.example;

import java.util.List;

public class AccountDTO {



private String firstname;

private String lastname;

private String birthday;
private Address address;
private Account account;

    public static class Address {
        private String street;
        private String city;
        private int zipcode;

        @Override
        public String toString() {
            return "Address{" +
                    "street='" + street + '\'' +
                    ", city='" + city + '\'' +
                    ", zipcode=" + zipcode +
                    '}';
        }
    }

    public static class Account{
        private String id;
        private String balance;
        private boolean isActive;

        @Override
        public String toString() {
            return "Account{" +
                    "id='" + id + '\'' +
                    ", balance='" + balance + '\'' +
                    ", isActive=" + isActive +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address=" + address +
                ", account=" + account;
    }
}
