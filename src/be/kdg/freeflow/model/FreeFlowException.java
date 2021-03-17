package be.kdg.freeflow.model;

public class FreeFlowException extends RuntimeException {
    public FreeFlowException() {
    }

    public FreeFlowException(String message) {
        super(message);
    }

    public FreeFlowException(String message, Throwable cause) {
        super(message, cause);
    }

    public FreeFlowException(Throwable cause) {
        super(cause);
    }
}
