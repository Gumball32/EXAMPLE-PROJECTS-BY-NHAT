package api;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class MD5 {
	public String encrypt(String password) throws NoSuchAlgorithmException {
		try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean compare(String inputPassword, String hashPassWord) throws NoSuchAlgorithmException {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(inputPassword.getBytes());
	        byte[] digest = md.digest();
	        String myChecksum = DatatypeConverter
	                .printHexBinary(digest).toUpperCase();
	        return hashPassWord.equals(myChecksum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
