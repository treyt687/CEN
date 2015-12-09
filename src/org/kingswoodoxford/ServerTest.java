package org.kingswoodoxford;

import java.io.IOException;

public class ServerTest extends Compression {
	public static void main(String[] args) throws IOException {
		Networking.serverSendFile("CMPFiles/original.txt", 6062);
	}
}
