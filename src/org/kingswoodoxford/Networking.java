package org.kingswoodoxford;

import java.net.*;
import java.io.*;

public class Networking {

	public static void generatePort() 
	{   
	    int x = (int)(Math.random() * 9);
	    x = x + 1;
	    String randomPIN = (x + "") + ( ((int)(Math.random()*1000)) + "" );
	    System.out.println(randomPIN);
	    SOCKET_PORT = Integer.parseInt(randomPIN);
	}

	
	public static Integer SOCKET_PORT = 6066;
	public static String FILE_TO_SEND = "CMPFiles/compressed.txt";
	
	public static void serverSendFile(String FILE_TO_SEND, Integer SOCKET_PORT) throws IOException {
		Compression.compress(FILE_TO_SEND, "CMPFiles/compressedtext.gz");
		FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    OutputStream os = null;
	    ServerSocket servsock = null;
	    Socket sock = null;
	    try {
	      servsock = new ServerSocket(SOCKET_PORT);
	      while (true) {
	        System.out.println("Waiting...");
	        try {
	          sock = servsock.accept();
	          System.out.println("Accepted connection : " + sock);
	          // send file
	          File myFile = new File ("CMPFiles/compressedtext.gz");
	          byte [] mybytearray  = new byte [(int)myFile.length()];
	          fis = new FileInputStream(myFile);
	          bis = new BufferedInputStream(fis);
	          bis.read(mybytearray,0,mybytearray.length);
	          os = sock.getOutputStream();
	          System.out.println("Sending " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)");
	          os.write(mybytearray,0,mybytearray.length);
	          os.flush();
	          System.out.println("Done.");
	        }
	        finally {
	          if (bis != null) bis.close();
	          if (os != null) os.close();
	          if (sock!=null) sock.close();
	        }
	      }
	    }
	    finally {
	      if (servsock != null) servsock.close();
	    }
	}

	public static Integer FILE_SIZE = 10000;
	public static String FILE_TO_RECEIVED = "compressedtext.gz";
	
	public static void clientReceive(String FILE_TO_RECEIVED, Integer SOCKET_PORT, Integer FILE_SIZE) throws IOException {
		int bytesRead;
	    int current = 0;
	    FileOutputStream fos = null;
	    BufferedOutputStream bos = null;
	    Socket sock = null;
	    try {
	      sock = new Socket(InetAddress.getLocalHost(), SOCKET_PORT);
	      System.out.println("Connecting...");

	      // receive file
	      byte [] mybytearray  = new byte [FILE_SIZE];
	      InputStream is = sock.getInputStream();
	      fos = new FileOutputStream(FILE_TO_RECEIVED);
	      bos = new BufferedOutputStream(fos);
	      bytesRead = is.read(mybytearray,0,mybytearray.length);
	      current = bytesRead;

	      do {
	         bytesRead =
	            is.read(mybytearray, current, (mybytearray.length-current));
	         if(bytesRead >= 0) current += bytesRead;
	      } while(bytesRead > -1);

	      bos.write(mybytearray, 0 , current);
	      bos.flush();
	      System.out.println("File " + FILE_TO_RECEIVED + " downloaded (" + current + " bytes read)");
	    }
	    finally {
	      if (fos != null) fos.close();
	      if (bos != null) bos.close();
	      if (sock != null) sock.close();
	    }
	}
}
