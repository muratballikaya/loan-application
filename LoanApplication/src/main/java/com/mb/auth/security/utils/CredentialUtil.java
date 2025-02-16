package com.mb.auth.security.utils;

import java.security.SecureRandom;

public class CredentialUtil {

    public static String generatePassword(){
        int length = 10;
        String cap_letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String small_letter = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";


        String allChars = cap_letter + small_letter +
                numbers;

        SecureRandom random = new SecureRandom();

        char[] password = new char[length];

        for (int i = 0; i < length; i++)
        {
            password[i] =
                    allChars.charAt(random.nextInt(allChars.length()));

        }

        return new String(password);
    }
}
