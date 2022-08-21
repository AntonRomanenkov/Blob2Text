import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ReadFile {
    public static void main(String[] args) {

        try {
            String curDir = System.getProperty("user.dir");
            System.out.println(curDir);
            /*
             * Sets up a file reader to read the file passed on the command
             * line one character at a time
             */
            File input = new File(args[0]);

            FileInputStream fis = new FileInputStream(input);

            String result = new BufferedReader(new InputStreamReader(fis))
            .lines().collect(Collectors.joining("\n"));
         
            fis.close();
            FileWriter myWriter = new FileWriter("output.txt");
            myWriter.write(result);
            myWriter.close();
        } catch (ArrayIndexOutOfBoundsException e) {
            /*
             * If no file was passed on the command line, this expception is
             * generated. A message indicating how to the class should be
             * called is displayed
             */
            System.out.println("Usage: java ReadFile filename\n");

        } catch (IOException e) {
            // If another exception is generated, print a stack trace
            e.printStackTrace();
        }

    }// end main
}
