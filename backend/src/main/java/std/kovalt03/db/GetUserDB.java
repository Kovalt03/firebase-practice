package std.kovalt03.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class GetUserDB {

    private static final String DB_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12793642?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul";
    private static final String DB_USER = "sql12793642";
    private static final String DB_PASSWORD = "pTEREHzZ33";

    public static Map<String, String> getSession(String username) {
        String role = null;
        String sessionID = null;
        Map<String, String> userData = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT role, sessionID FROM users WHERE username = ?")) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString("role");
                sessionID = resultSet.getString("sessionID");
            }
            userData.put("role", role);
            userData.put("sessionID", sessionID);
            return userData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}