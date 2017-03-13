package com.github.high_level_brainfuck.compiler.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BfFileWriter {
	public void writeFile(String bfCode, String fileName) throws IOException {
		Files.write(Paths.get(fileName), bfCode.getBytes());
	}
}
