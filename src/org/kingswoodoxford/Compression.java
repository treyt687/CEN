package org.kingswoodoxford;

import java.io.*;
import java.util.zip.*;

public class Compression extends Encryption{

	/**
	 * @param args
	 * @return 
	 * @throws IOException 
	 */
	
	public static char[] compress(String originalFile, String compressFile) throws IOException {
	    char[] buffer = new char[8192];
	    try (
	            FileReader reader = new FileReader(originalFile);
	            Writer writer = new OutputStreamWriter(
	                    new GZIPOutputStream(new FileOutputStream(compressFile)));
	    ) {
	        for (int len; (len = reader.read(buffer)) > 0; )
	            writer.write(buffer, 0, len);
	    }
		return buffer;
	}
	
	public static String decompress(String compressFile, String decompressFile) throws IOException {
		File decompress = new File(compressFile);
		byte[] compressed = readContentIntoByteArray(decompress);
		
		ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
		GZIPInputStream gis = new GZIPInputStream(bis);
		BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		gis.close();
		bis.close();
		try {
			FileWriter file = new FileWriter(decompressFile);
			BufferedWriter bufwr = new BufferedWriter(file);
			
			bufwr.write(sb.toString());
			
			bufwr.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}
	
	private static byte[] readContentIntoByteArray(File file)
	   {
	      FileInputStream fileInputStream = null;
	      byte[] bFile = new byte[(int) file.length()];
	      try
	      {
	         //convert file into array of bytes
	         fileInputStream = new FileInputStream(file);
	         fileInputStream.read(bFile);
	         fileInputStream.close();
	         System.out.println(bFile.toString());
	      }
	      catch (Exception e)
	      {
	         e.printStackTrace();
	      }
	      return bFile;
	   }
}