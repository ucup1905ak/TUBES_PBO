package utility;

/**
 *
 * @author farel
 */



public class PasswordHasher {
    private static final int BCRYPT_STRENGTH = 12;

    public String hash(String plainPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        try {
            String salt = BCrypt.gensalt(BCRYPT_STRENGTH);
            return BCrypt.hashpw(plainPassword, salt);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password with BCrypt", e);
        }
    }

    public boolean verify(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }

        try {
            return BCrypt.checkpw(plainPassword, hashedPassword);
        } catch (Exception e) {
            System.err.println("Error verifying password: " + e.getMessage());
            return false;
        }
    }

    public boolean needsRehash(String hashedPassword) {
        if (hashedPassword == null || hashedPassword.isEmpty()) {
            return false;
        }

        try {
            int workFactor = Integer.parseInt(hashedPassword.substring(4, 6));
            return workFactor < BCRYPT_STRENGTH;
        } catch (Exception e) {
            return false;
        }
    }
}