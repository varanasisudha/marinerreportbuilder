package exception;

public class FileTypeNotSupportedException extends Exception {

    public FileTypeNotSupportedException(String errorMessage) {
        super(errorMessage);
    }
}