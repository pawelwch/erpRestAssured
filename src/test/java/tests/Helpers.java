package tests;

import com.github.javafaker.Faker;

public class Helpers {

    private Faker faker;

    public String generateRandomLocalPhoneNumber() {
        return "+48" + faker.number().digits(9).toString();
    }
}
