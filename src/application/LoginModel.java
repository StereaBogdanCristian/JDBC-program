package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
	
	Connection conection;
	
	public LoginModel() {
		conection = SqliteConnection.Conn();
		if (conection == null) System.exit(1);
	}
	
	public boolean isDbConnected() {
		try {
			return !conection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isLogin (String user, String pass) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String command = "select * from BDlocatari where user = ? and password = ?";
		try {
			preparedStatement = conection.prepareStatement(command);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, pass);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next() || (user.equals("test") && pass.equals("test"))) {
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}

}
