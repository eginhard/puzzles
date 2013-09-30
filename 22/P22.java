import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class P22 {
    public static void main(String[] args) throws IOException {
	Scanner scanner = new Scanner(System.in);
	String fileName = "";
	BufferedReader in = null;

	int lineCount = 0;
	int sum = 0;

	System.out.println("Enter sorted data file name: ");
	if (scanner.hasNext())
	    fileName = scanner.next();
	
	
	try {
	    in = new BufferedReader(new FileReader(fileName));

	    String line;
	    while ((line = in.readLine()) != null) {
		lineCount++;
		int letterSum = 0;
		char[] letters = line.toCharArray();
		for (char letter : letters)
		    letterSum += score(letter);
		sum += letterSum * lineCount;
	    }
	}
	finally {
	    if (in != null) { in.close(); }
	}

	System.out.println(sum);
    }

    public static int score(char letter) {
	int ascii = (int) letter;
	return (ascii - 64);
    }
}