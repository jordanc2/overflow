package com.overflow.models.requests;

import java.util.Objects;

public class GetClientInventoryRequest {
    private String id;

    public GetClientInventoryRequest() {}

    public GetClientInventoryRequest(Builder builder) {
        this.id = builder.clientId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetClientInventoryRequest that = (GetClientInventoryRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "GetClientInventoryRequest{" +
                "id='" + id + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String clientId;

        private Builder() {}

        public Builder withClientId(String clientIdToUse) {
            this.clientId = clientIdToUse;
            return this;
        }

        public GetClientInventoryRequest build() { return new GetClientInventoryRequest(this); }
    }
}
