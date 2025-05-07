package com.drivetrue;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class PayHereHashGenerator {

    // Method to generate the hash as per PayHere's instructions
    public static String generateHash(String merchantId, String orderId, String amount, String currency, String merchantSecret) {
        try {
            // Hash the merchant_secret using MD5 and convert it to uppercase
            String merchantSecretHash = toUpperCaseMD5(merchantSecret);

            // Concatenate merchant_id, order_id, amount, currency, and the hashed merchant_secret
            String data = merchantId + orderId + String.format("%.2f", Double.parseDouble(amount)) + currency + merchantSecretHash;

            // Hash the concatenated string using MD5 and convert it to uppercase
            return toUpperCaseMD5(data);
        } catch (Exception e) {
            throw new RuntimeException("Error generating PayHere hash", e);
        }
    }

    // Helper method to compute MD5 and convert the result to uppercase
    private static String toUpperCaseMD5(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
        
        // Convert bytes to hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        
        return hexString.toString().toUpperCase();
    }
}