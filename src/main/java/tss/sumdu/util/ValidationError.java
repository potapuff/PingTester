package tss.sumdu.util;

public class ValidationError extends RuntimeException {
    private Exception root_cause;
    private String message;

    public ValidationError(Exception exception) {
        this.root_cause = exception;
    }

    public ValidationError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message == null ? root_cause.getMessage() : message;
    }
}
