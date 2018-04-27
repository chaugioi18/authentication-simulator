package vn.simulator.common.helper;

import vn.simulator.exception.CustomException;

import java.security.MessageDigest;

public class Generator {

    public static String generateSha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new CustomException.GenerateException(e.getMessage());
        }
    }

}
