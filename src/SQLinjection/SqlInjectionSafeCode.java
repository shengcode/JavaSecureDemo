
package SQLinjection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInjectionSafeCode {
    public static void main (String[]args){
        //PreparedStatement  preparedStatement=conn.prepareStatement('SELECT * FROM  usercheck where username=?') ;
        //preparedStatement.setString(1, user);
        Connection myOracleConnection=null;
        Statement myOracleStatement=null;
        ResultSet myOracleRS;     
        try {            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            myOracleConnection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","hr");
            myOracleStatement=myOracleConnection.createStatement(); 
            String BadInput= "Bob' OR '1'='1";
            PreparedStatement preparedStatement =myOracleConnection.prepareStatement("SELECT * FROM safeUSER where username= ?");
            preparedStatement.setString(1, BadInput);
            //preparedStatement.
            System.out.println(preparedStatement.toString());
            myOracleRS= preparedStatement.executeQuery();
      
            while(myOracleRS.next()){
                  String user_name=myOracleRS.getString("username");
                  String password=myOracleRS.getString("password");
                  System.out.println("Bob's information username is + "+ user_name+ " password is "+ password);
            }
           
        } catch (SQLException ex) {
            System.out.println("Exception:"+ex.getMessage());
        }catch (ClassNotFoundException ex) {
            System.out.println("Driver class not found");
            }
        finally{            
                try {
                    if(myOracleStatement!=null) myOracleStatement.close();
                    if(myOracleConnection!=null)myOracleConnection.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage()); 
            }                
        }
       
    
    }
}
