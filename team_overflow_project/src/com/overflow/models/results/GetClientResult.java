package com.overflow.models.results;

import com.overflow.dynamodb.models.Client;

public class GetClientResult {
    private Client client;

    public GetClientResult(Builder builder) {
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
        return "GetClientResult{" +
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

        public GetClientResult build() { return new GetClientResult(this); }
    }
}
