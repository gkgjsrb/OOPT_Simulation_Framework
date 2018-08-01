package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class DataReader {
	private Connection connection;
	private String dbFileName;
	private boolean isOpened = false;
	
	//private final static String QUERY_SELECT_BYNAME = "SELECT * FROM media WHERE FilePath=?;";
	//private final static String QUERY_SELECT_BYNAME_HASHCODE = "SELECT * FROM media WHERE FilePath=? AND CheckSum=?;";
	//private final static String QUERY_SELECT_THUMBNAIL = "SELECT Thumbnail FROM media WHERE FilePath=?;";
	
	static {
		try{
			Class.forName("org.sqlite.JDBC");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public DataReader(String databaseFileName) {
		this.dbFileName = databaseFileName;
	}
	
	public boolean open() {
		try{
			SQLiteConfig config = new SQLiteConfig();
			config.setReadOnly(true);
			this.connection = DriverManager.getConnection("jdbc:sqlite:" + this.dbFileName, config.toProperties());
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		isOpened = true;
		return true;
	}
	public boolean close() {
		if(this.isOpened == false) {
			return true;
		}
		
		try {
			this.connection.close();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	public boolean readMeta(String filePath, String hashCode) throws SQLException {
		if(this.isOpened == false) {
			return false;
		}
		boolean result = false;
		String query = "SELECT * FROM media WHERE FilePath=? AND CheckSum=?;";
		PreparedStatement prep = this.connection.prepareStatement(query);
		prep.setString(1, filePath);
		prep.setString(2, hashCode);
		
		ResultSet row = prep.executeQuery();
		if(row.next()) {
			row.getString(1);
			row.getString("FileSize");
			
			result = true;
		}
		row.close();
		prep.close();
		return result;
	}
}
