import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.String;
import java.io.ByteArrayOutputStream;
import javax.script.ScriptException;

/*
import java.sql.DriverManager;
import oracle.jdbc.driver.OracleDriver;
import java.sql.Connection;
import java.sql.Statement;
 * 
 */

public class ReadFile {
    public static void main(String[] args) throws ScriptException {
        try {
            /*
             * Sets up a file reader to read the file passed on the command
             * line one character at a time
             */
            String fileName = args[0];
            File input = new File(args[0]);
            String ext = getFileExtension(fileName);
            String str = "";
            if (new String("bin").equals(ext)) {
                FileInputStream fin = new FileInputStream(input);
                ByteArrayOutputStream buf = new ByteArrayOutputStream();
                int c;

                while ((c = fin.read()) != -1) {
                    buf.write((byte) c);
                }
                if (fin != null) {
                    fin.close();
                }

                str = buf.toString();
                str = clearUndefined(str);
                System.out.print(str);
            } else {
                /*
                // Registering the Driver
                DriverManager.registerDriver(new OracleDriver());
                // Getting the connection
                String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
                Connection con = DriverManager.getConnection(oracleUrl, "system", "password");
                System.out.println("Connection established......");
                // Creating the Statement
                Statement stmt = con.createStatement();
                // Query to create a table
                String query = "CREATE TABLE DISPATCHES("
                        + "ProductName VARCHAR (20) NOT NULL, "
                        + "CustomerName VARCHAR (20) NOT NULL, "
                        + "DispatchDate date, "
                        + "DeliveryTime timestamp, "
                        + "Price INT, "
                        + "Location varchar(20))";
                stmt.execute(query);
                System.out.println("Table Created......");
                */
            }
            FileOutputStream fout = new FileOutputStream("output.txt");
            fout.write(str.getBytes("UTF-8"));
            if (fout != null) {
                fout.close();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            /*
             * If no file was passed on the command line, this expception is generated.
             * A message indicating how to the class should be called is displayed
             */
            System.out.println("Usage: java ReadFile filename\n");

        } catch (IOException e) {
            // If another exception is generated, print a stack trace
            e.printStackTrace();
        }

    }

    private static String getFileExtension(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

        if (i > p) {
            extension = fileName.substring(i + 1);
        }
        return extension;
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
