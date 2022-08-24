import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.String;
import java.io.ByteArrayOutputStream;
import javax.script.ScriptException;
import java.io.InputStream;

public class ReadFile {
    public static void main(String[] args) throws ScriptException {
        try {
            /*
             * Sets up a file reader to read the file passed on the command
             * line one character at a time
             */

            String fileName = args[0];
            File input = new File(args[0]);
            String extension = "";

            int i = fileName.lastIndexOf('.');
            int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

            if (i > p) {
                extension = fileName.substring(i + 1);
            }

            FileInputStream fin = new FileInputStream(input);
            FileOutputStream fout = new FileOutputStream("output.txt");
            ByteArrayOutputStream buf = new ByteArrayOutputStream();

            String str;
            if (extension != "bin") {
                // assumed the file is long raw and review the code.
            }

            int c;

            while ((c = fin.read()) != -1) {
                buf.write((byte) c);
            }

            // InputStream ascStream = InputStream getAsciiStream();

            str = buf.toString();
            str = clearUndefined(str);

            fout.write(str.getBytes("UTF-8"));
            if (fin != null) {
                fin.close();
            }
            if (fout != null) {
                fout.close();
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

    private static String clearUndefined(String str) {
        str = str.replaceAll("[\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}]", "?");
        str = str.replaceAll("\\ ", "*");
        str = str.replaceAll("\\?", " ");
        str = str.replaceAll("\\�", "");
        str = str.replaceAll("\\     ", "*");
        str = str.replaceAll("\\   ", "*");
        str = str.replaceAll("\\    ", "*");
        str = str.replaceAll("\\ ", "");
        str = str.replaceAll("\\*", " ");

        return str;
    }
}
