package com.overflow.models.results;

import com.overflow.dynamodb.models.Client;

public class CreateClientResult {
    private Client client;

    public CreateClientResult(Builder builder) {
        this.client = builder.client;
    }

    public Client getClient() { return client; }

    public void setClient(Client client) { this.client = client; }

    @Override
    public String toString() {
        return "CreateClientResult{" +
                "client=" + client +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private Client client;

        public Builder withClient(Client client) {
            this.client = client;
            return this;
        }

        public CreateClientResult build() { return new CreateClientResult(this); }
    }
}
