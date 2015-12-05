package org.kingswoodoxford;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

public class Encryption {
	
	private static final String ALGO = "AES";
	private static final byte[] keyValue = new byte[] {'M', 'I', 'K', 'E', 'I', 'S', 'S', 'H', 'I', 'T', 'L', 'O', 'L', 'L', 'O', 'L'};
	public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        @SuppressWarnings("restriction")
		String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        @SuppressWarnings("restriction")
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }	
}
