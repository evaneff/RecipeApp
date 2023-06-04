package main.models.requests;


import java.util.Objects;

public class DeleteRecipeRequest {
    private String id;

    public DeleteRecipeRequest() {}

    public DeleteRecipeRequest(Builder builder) { this.id = builder.id; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeleteRecipeRequest)) return false;
        DeleteRecipeRequest that = (DeleteRecipeRequest) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "DeleteRecipeRequest{" +
                "id='" + id + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String id;

        public Builder() {}

        public Builder withId(String idToUse) {
            this.id = idToUse;
            return this;
        }

        public DeleteRecipeRequest build() { return new DeleteRecipeRequest(this); }
    }
}
