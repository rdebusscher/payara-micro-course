package be.rubus.courses.payara.micro.lab.shop;

/**
 * POJO when an error message in JSON format needs to be returned.
 */
public class ErrorMessage {

    private final String code;
    private final String message;

    public ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
