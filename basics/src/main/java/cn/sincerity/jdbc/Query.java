package cn.sincerity.jdbc;



import java.sql.*;

/**
 * Query
 *
 * @author Ht7_Sincerity
 * @date 2023/5/29
 */
public class Query {

    private static final String host = "jdbc:mysql://localhost:3306/draft?characterEncoding=UTF-8&useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";

    private static final String username = "root";

    private static final String password = "hutengqi1228.?";


    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(host, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement("select student_name from student");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("student_name"));
        }
    }
}
