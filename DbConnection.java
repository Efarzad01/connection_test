package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.stream.StreamSupport;

public class DbConnection {
    public static void main(String[] args) {

        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/";
        String driver = "com.mysql.jdbc.Driver"; //1
        String dbName = "students";
        String user = "root";
        String pass = "6112022Fa@";


        try {
            Class.forName(driver).newInstance();

            connection = DriverManager.getConnection(url + dbName, user, pass);
            System.out.println(  connection.isClosed());
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from students");

            while (rs.next()) {
                System.out.println("Data" + "---->  " + rs.getString("studentID") +
                        " " + rs.getString("studentName")
                        + " " + rs.getString("studentDob"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();


        }

        try{
            if(connection!=null)
                connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }}
