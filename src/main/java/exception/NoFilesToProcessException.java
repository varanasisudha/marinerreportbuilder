package exception;

public class NoFilesToProcessException extends Exception {

    public NoFilesToProcessException(String errorMessage) {
        super(errorMessage);
    }
}
