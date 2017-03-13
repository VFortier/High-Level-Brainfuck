package com.github.high_level_brainfuck;

import java.util.logging.Logger;

import com.github.high_level_brainfuck.compiler.Compiler;
import com.github.high_level_brainfuck.compiler.file.BfFileWriter;
import com.github.high_level_brainfuck.compiler.BfProgram;
import com.github.high_level_brainfuck.compiler.CompileException;

public class Main {
	
	private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		MainArgs mainArgs = new MainArgs(args);
		Compiler compiler = new Compiler();
		BfFileWriter fileWriter = new BfFileWriter();
		
		try {
			BfProgram bfProgram = compiler.compileFile(mainArgs.getBfCodeFilePath());
			fileWriter.writeFile(bfProgram.toString(), "bfcode.b");
		} catch (CompileException e) {
			LOGGER.severe(e.getMessage());
		}
	}

	private static class MainArgs {
		
		private String bfCodeFilePath;

		public MainArgs(String[] args) {
			if (args.length == 1) {
				bfCodeFilePath = args[0];
			} else {
				// TODO - Print args usage
				LOGGER.severe("Invalid args.\n");
			}
		}
		
		public String getBfCodeFilePath() {
			return bfCodeFilePath;
		}
	}
}
