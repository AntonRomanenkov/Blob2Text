import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.String;
import java.io.ByteArrayOutputStream;

import javax.script.ScriptException;
// import javax.print.StreamPrintService;
// import javax.script.ScriptEngineManager;
// import javax.script.ScriptEngine;
// import java.util.regex.Pattern;

// import java.util.regex.Matcher;

public class ReadFile {
    public static void main(String[] args) throws ScriptException {
        try {
            /*
             * Sets up a file reader to read the file passed on the command
             * line one character at a time
             */

            File input = new File(args[0]);
            FileInputStream in = new FileInputStream(input);
            FileOutputStream out = new FileOutputStream("output.txt");
            ByteArrayOutputStream buf = new ByteArrayOutputStream();

            int c;
            String str;

            while ((c = in.read()) != -1) {
                buf.write((byte) c);
            }

            str = buf.toString().replaceAll("[\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}]", "?");
            str = str.replaceAll("\\ ", "*");
            str = str.replaceAll("\\?", " ");
            str = str.replaceAll("\\�", "");
            str = str.replaceAll("\\     ", "*");
            str = str.replaceAll("\\   ", "*");
            str = str.replaceAll("\\    ", "*");
            str = str.replaceAll("\\ ", "");
            str = str.replaceAll("\\*", " ");

            // ScriptEngineManager manager = new ScriptEngineManager();
            // ScriptEngine engine = manager.getEngineByName("JavaScript");
            // String returnValue = (String) engine.eval("return regularExpStr(`" + str + "`)");
            // System.out.print(returnValue);
            out.write(str.getBytes("UTF-8"));
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
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

    }
}
