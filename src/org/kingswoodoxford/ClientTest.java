package org.kingswoodoxford;

import java.io.IOException;

public class ClientTest extends Compression{
	public static void main(String[] args) throws IOException {
		Networking.clientReceive("CMPFiles/compressedtext.gz", 6062, 100000);
		Compression.decompress("CMPFiles/compressedtext.gz", "CMPFiles/decompressedtext.txt");
	}
}
