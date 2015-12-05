package org.kingswoodoxford;

import java.io.IOException;

public class TestEC extends Compression {
  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
				// compressed byte array
				char[] compressed = null;
				// decompressed string
				String decompressed = null;
				// compress the string to a byte array
				try {
					compressed = Compression.compress("CMPFiles/originaltext.gz", "CMPFiles/compressedtext.gz");
					System.out.println(compressed);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// decompress the byte array to a a string
				try {
					decompressed = Compression.decompress("CMPFiles/compressedtext.gz", "CMPFiles/decompressedtext.gz");
					System.out.println(decompressed);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					String encrypted = Compression.encrypt(decompressed);
					System.out.println("Encrypted: " + encrypted);
					String decrypted = Compression.decrypt(encrypted);
					System.out.println("Decrypted: " + decrypted);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
}
