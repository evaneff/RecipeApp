package main.models.results;


public class DeleteRecipeResult {
    private String success;

    public DeleteRecipeResult(DeleteRecipeResult.Builder builder) { this.success = builder.success; }

    public String getSuccess() { return success; }

    public void setSuccess(String success) { this.success = success; }

    public static DeleteRecipeResult.Builder builder() { return new DeleteRecipeResult.Builder(); }

    public static final class Builder {
        private String success;

        public DeleteRecipeResult.Builder withSuccess(String successToUse) {
            this.success = successToUse;
            return this;
        }

        public DeleteRecipeResult build() { return new DeleteRecipeResult(this); }
    }


}
