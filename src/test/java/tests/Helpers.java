package tests;

import com.github.javafaker.Faker;

public class Helpers {

    private Faker faker;

    public String generate_random_local_phone_number() {
        return "+48" + faker.number().digits(9).toString();
    }
}
