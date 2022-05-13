package com.overflow.models.results;

import com.overflow.dynamodb.models.Client;

public class UpdateClientResult {
    private Client client;

    public UpdateClientResult(Builder builder) {
        this.client = builder.client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "UpdateClientResult{" +
                "client=" + client +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private Client client;

        public Builder withClient(Client clientToUse) {
            this.client = clientToUse;
            return this;
        }

        public UpdateClientResult build() { return new UpdateClientResult(this); }
    }
}
