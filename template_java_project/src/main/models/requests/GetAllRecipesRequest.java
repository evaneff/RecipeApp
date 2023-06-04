package main.models.requests;

import java.util.Objects;

public class GetAllRecipesRequest {

    private String customerId;

    public GetAllRecipesRequest() {
    }

    public GetAllRecipesRequest(Builder builder) { this.customerId = builder.customerId; }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetAllRecipesRequest)) return false;
        GetAllRecipesRequest that = (GetAllRecipesRequest) o;
        return Objects.equals(getCustomerId(), that.getCustomerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId());
    }

    @Override
    public String toString() {
        return "GetAllRecipesRequest{" +
                "customerId='" + customerId + '\'' +
                '}';
    }
    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String customerId;

        public Builder() {
        }

        public Builder withCustomerId(String customerIdToUse) {
            this.customerId = customerIdToUse;
            return this;
        }
        public GetAllRecipesRequest build() { return new GetAllRecipesRequest(this); }
    }
}
