package ConnectToMySQL;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectMySql {

    public static Connection connect= null;
    public static Statement statement = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;

    public static Properties loadProperties() throws IOException{
        Properties prop = new Properties();
        InputStream ism = new FileInputStream("src/secret.properties");
        prop.load(ism);
        ism.close();
        return prop;
    }

    public static Connection connectToMySQL() throws Exception{
        Properties prop = loadProperties();
        String driverClass = prop.getProperty("MYSQLJDBC.driver");
        String url = prop.getProperty("MYSQLJDBC.url");
        String user = prop.getProperty("MYSQLJDBC.user");
        String password = prop.getProperty("MYSQLJDBC.password");
        Class.forName(driverClass);
        connect = DriverManager.getConnection(url,user,password);
        System.out.println("Database is connected");
        return connect;
    }

    public static void main(String[] args) {
        //get the connection from the database

       try{
           Connection mycon = connectToMySQL();
           //create statement
           Statement statement =mycon.createStatement();
           //write sql query
           ResultSet rs = statement.executeQuery("Select * from students");

           while(rs.next()){
               System.out.println("Data"+ "---->  " + rs.getString("studentID") +
                 " "   +   rs.getString("studentName")
               + " " + rs.getString("studentDob"));
           }
       }catch (Exception ex){
           ex.printStackTrace();
       }
    }

    }


