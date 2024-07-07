package utility;

public enum HttpStatus {
    OK(200),
    CREATED(201),
    NOT_FOUND(404);

    private final int code;

    HttpStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
