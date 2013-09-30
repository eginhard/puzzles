import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Sort {
    public static void main(String[] args) throws IOException {
	Scanner scanner = new Scanner(System.in);
	String fileName = "";
	BufferedReader in = null;
	PrintWriter out = null;
	ArrayList<String> names = new ArrayList<String>();

	System.out.println("Enter data file name: ");
	if (scanner.hasNext())
	    fileName = scanner.next();
	
	
	try {
	    in = new BufferedReader(new FileReader(fileName));
	    out = new PrintWriter(new FileWriter("sorted_" + fileName));

	    String line;
	    while ((line = in.readLine()) != null) {
		scanner = new Scanner(line);
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
		    String name = scanner.next();
		    names.add(name.substring(1, name.length() - 1));
		}
	    }
	    Collections.sort(names);
	    for (String name : names)
		out.println(name);
	}
	finally {
	    if (in != null) { in.close(); }
	    if (out != null) { out.close(); }
	}
    }
}