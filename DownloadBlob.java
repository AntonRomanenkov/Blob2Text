import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DownloadBlob {

	public static void main(String[] args) {
		downloadBlob();
	}

	private static void downloadBlob() {
		Connection conn = null;
		
		PreparedStatement pst = null;
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/laravel";
			String user = "root";
			String password = "";
			String query = "select file from migrations";
			
			String fileName = "output.txt";
			File file = new File(fileName);
			
			conn = DriverManager.getConnection(url, user, password);
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			FileOutputStream outputStream = new FileOutputStream(file);
			while (rs.next()) {
				InputStream is = rs.getBinaryStream(1);
				String str = convert(is);

				outputStream.write(str.getBytes("UTF-8"));
			}
			outputStream.close();
			System.out.println("File Generated: " + fileName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static String convert(InputStream is) {
		BufferedInputStream bis = new BufferedInputStream(is);
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		int result;
		String str = null;
		try {
			result = bis.read();

			while (result != -1) {
				System.out.print(result);
				System.out.print("\n");
				buf.write((byte) result);
				result = bis.read();
			}
			str = buf.toString().replaceAll("\\P{Print}", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}