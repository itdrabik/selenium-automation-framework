package com.demoblaze.utils;

import java.util.UUID;

public class DataGenerator {
    public static String generateRandomMail() {
        return "user_" + UUID.randomUUID().toString().substring(0,5) + "@example.com";
    }

    public static String generateRandomPassword() {
        return "Pass_" + UUID.randomUUID().toString().substring(0,5);
    }
}
