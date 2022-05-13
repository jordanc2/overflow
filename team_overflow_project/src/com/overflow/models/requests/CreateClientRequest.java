package com.overflow.models.requests;

import java.util.Objects;

public class CreateClientRequest {
    private String name;
    private String email;
    private String phoneNumber;

    public CreateClientRequest() {}

    public CreateClientRequest(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateClientRequest that = (CreateClientRequest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return  Objects.hash(name, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "CreateClientRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String name;
        private String email;
        private String phoneNumber;

        private Builder() {

        }

        public Builder withName(String nameToUse) {
            this.name = nameToUse;
            return this;
        }

        public Builder withEmail(String emailToUse){
            this.email = emailToUse;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumberToUse){
            this.phoneNumber = phoneNumberToUse;
            return this;
        }

        public CreateClientRequest build() { return new CreateClientRequest(this); }
        }
}
