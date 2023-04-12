package be.rubus.courses.payara.micro.lab.shop;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CreateCredentials {

    public static void main(String[] args) {
        String result = createBasicAuthenticationValue("Rudy", "mySecretPassword");
        System.out.println(result);
        result = createBasicAuthenticationValue("test", "something");
        System.out.println(result);
    }

    private static String createBasicAuthenticationValue(String userName, String password) {
        return Base64.getUrlEncoder().encodeToString((userName + ":" + password).getBytes(StandardCharsets.UTF_8));
    }
}
