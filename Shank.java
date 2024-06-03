//Haolin Wang
//CIS311, assignment 3
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Shank {
	public static void main(String[] args) throws Exception {
		/*
		 * if there are none or more than 1 argument (args) print an appropriate error
		 * message and exit
		 */
		if (args.length != 1) {
			System.out.println("There's none or more than 1 args");
			System.exit(1);
		}

		/*
		 * Using Files.ReadAllLines to read all of the lines from the file denoted by
		 * filename
		 */
		Lexer lexer = new Lexer();
		Path myPath = Paths.get("src/file.shank.txt");
		try {
			List<String> lines = Files.readAllLines(myPath, StandardCharsets.UTF_8);

			for (String line : lines) {
				try {
					lexer.lex(line);
				} catch (Exception e) {
					System.out.println("There is an exception: " + e.getMessage());
				}
			}
		} catch (IOException e) {
			System.out.println("There is an error with the file. ");
		}


		
		// for each loop, print out all from the tokenlist
		for (Token token1234: Lexer.tokenList) {
			System.out.println(token1234);
		}
		System.out.println("----[End of tokenList and lexer]----\n\n\n");
		
		
		
		
		
		
		// parser
		System.out.println("---- [Parser starts] ----");
		
		Parser parser = new Parser(Lexer.tokenList);
		parser.parse();
		
		
		
		
		
		ProgramNode programNode = new ProgramNode();
		System.out.println("\n\n\n");
		System.out.println("---[ProgramNode in main:]--- \n"+programNode);
	}

}
