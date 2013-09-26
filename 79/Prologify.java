import java.util.Scanner;
import java.io.*;

public class Prologify {
    public static void main(String[] args) throws IOException {
	Scanner scanner = new Scanner(System.in);
	String fileName = "";
	BufferedReader in = null;
	PrintWriter out = null;

	System.out.println("Enter data file name: ");
	if (scanner.hasNext())
	    fileName = scanner.next();

	try {
	    in = new BufferedReader(new FileReader(fileName));
	    out = new PrintWriter(new FileWriter(fileName.split("\\.")[0] + ".pl"));

	    String line;
	    while ((line = in.readLine()) != null) {
		char[] chars = line.toCharArray();
		out.println("before(" + chars[0] + "," + chars[1] +
			    "," + chars[2] + ").");
	    }
	}
	finally {
	    if (in != null) { in.close(); }
	    if (out != null) { out.close(); }
	}
    }
}
