package Tool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFunction {
	 public static String hash(String input) {
	        try {
	           
	            MessageDigest md = MessageDigest.getInstance("MD5");

	            md.update(input.getBytes());

	            byte[] bytes = md.digest();

	            StringBuilder sb = new StringBuilder();
	            for (byte b : bytes) {
	                sb.append(String.format("%02x", b));
	            }
	            return sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }

}
