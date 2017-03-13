package com.github.high_level_brainfuck.compiler.file;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BfGenFileReader {
	public String readBfGenFile(String filePath) throws URISyntaxException, IOException {
		// TODO - Support absolute paths
		byte[] encoded = Files.readAllBytes(Paths.get("", filePath));
		return new String(encoded, StandardCharsets.UTF_8);
	}
}
