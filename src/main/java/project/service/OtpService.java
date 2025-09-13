package project.service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class OtpService {

    private static final int OTP_LENGTH = 6;
    private static final int OTP_VALIDITY_DURATION_MINUTES = 5;
    private final Random random = new SecureRandom();
    
    // Store OTPs with expiration timestamps
    private Map<String, OtpDetails> otpStorage = new HashMap<>();

    // Generate OTP
    public String generateOtp(String key) {
        String otp = String.format("%0" + OTP_LENGTH + "d", random.nextInt(1000000));
        long expirationTime = System.currentTimeMillis() + OTP_VALIDITY_DURATION_MINUTES * 60 * 1000;
        otpStorage.put(key, new OtpDetails(otp, expirationTime));
        return otp;
    }

    // Validate OTP
    public boolean validateOtp(String key, String otp) {
        if (!otpStorage.containsKey(key)) {
            return false;
        }
        OtpDetails details = otpStorage.get(key);
        if (System.currentTimeMillis() > details.getExpirationTime()) {
            otpStorage.remove(key); // Remove expired OTP
            return false;
        }
        if (details.getOtp().equals(otp)) {
            otpStorage.remove(key); // Remove OTP after successful validation
            return true;
        }
        return false;
    }

    // Inner class to store OTP and expiration time
    private static class OtpDetails {
        private final String otp;
        private final long expirationTime;

        public OtpDetails(String otp, long expirationTime) {
            this.otp = otp;
            this.expirationTime = expirationTime;
        }

        public String getOtp() {
            return otp;
        }

        public long getExpirationTime() {
            return expirationTime;
        }
    }
}
