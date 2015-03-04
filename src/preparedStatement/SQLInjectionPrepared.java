/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package preparedStatement;

import java.sql.*;
import java.util.Scanner;
import statementDemo.DB;

// JDBC demo
// Demonstrate SQL injection protection
//  By separating SQL code and input parameters
//  the Database is able to distinguish between SQL and data
// hau
public class SQLInjectionPrepared {

    public static void main(String[] args) throws SQLException {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection connection = null;

        try {

           Class.forName(DB.driver);
           connection = DriverManager.getConnection(DB.URL, DB.ID, DB.PW);
            String query = "SELECT * FROM persons WHERE id = ? AND name = ?";
            statement = connection.prepareStatement(query); // SQL code is supplied

            //=== prompt the user for id and pw
            System.out.println("Enter id: ");
            int id = new Scanner(System.in).nextInt();
            System.out.println("Enter pw: ");
            String pw = new Scanner(System.in).nextLine();
            System.out.println("You entered : \nid: " + id + "\npw. " + pw);

            //=== exchange placeholders for data values
            statement.setInt(1, id);
            statement.setString(2, pw);

            //=== Execute the query and receive the result
            rs = statement.executeQuery();

            if (rs.next()) {
                System.out.println("logged on!");
            } else {
                System.out.println("Wrong id or password");
            }
        } catch (Exception ee) {
            System.out.println("fail");
            System.err.println(ee);
        } finally {
            rs.close();
            statement.close();
            connection.close();
        }
    }
}
