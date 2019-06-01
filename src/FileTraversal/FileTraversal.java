package FileTraversal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;



public class FileTraversal {
    public static void CreateFilesForDemo(){
        try {
            String fileData = "Content the program should see";            
            FileOutputStream fos;            
            fos = new FileOutputStream("SecureTest.txt");
            fos.write(fileData.getBytes());
            
            
            
            String secretData = "Password information that you shouldn't see";
            FileOutputStream fos_password;            
            fos_password = new FileOutputStream("../../SecureTest.txt");
            fos_password.write(secretData.getBytes());
            
           
            
            fos.flush();
            fos.close();
            fos_password.flush();
            fos_password.close();
            
            //File file = new File("SecureTest.txt");
            //System.out.println("the file you are allowed to read is stored at: "+ file.getAbsolutePath());
            
            //File password_file = new File("../../SecureTest.txt");
            //System.out.println("the password file is stored at: "+ password_file.getAbsolutePath());
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    public static void ReadFile(String path) throws PathTraversalException, IOException{
        BufferedReader br = null;
         /* ############safe code check #################*/
         //path=path.replace("../", "");
         //if(path.contains("../")) throw new PathTraversalException(path + " is not valid");
           
          try{  
            File file = new File("path");
            br = new BufferedReader(new FileReader(path));
            String st; 
            while ((st = br.readLine()) != null)
                System.out.println(st);
          }catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
   }
    
    public static void main(String[] args){
        /*the function to create the files for this DEMO */
        CreateFilesForDemo();
        String good_fileName="SecureTest.txt";
        String bad_fileName="../../SecureTest.txt";
        
        try {
            ReadFile(bad_fileName);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (PathTraversalException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
