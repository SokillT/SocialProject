package net.social;
import java.io.*;

public class EditFile{
	// The name of the file to open.
	
	public static BufferedReader Read(String filename){
		File file = new File(filename);
		BufferedReader bufferedReader = null ;
		try {
			// This will reference one line at a time
	        String line = null;
	        
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(filename);

            // Always wrap FileReader in BufferedReader.
            bufferedReader = new BufferedReader(fileReader);         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + filename + "'");                   
            // Or we could just do this: 
            // ex.printStackTrace();
        }
		return bufferedReader;     
	}
	
	public static void Write(String filename,String s,boolean mode){
		File file = new File(filename);
        try{
            FileWriter filew = new FileWriter(filename, mode); //mode=true append text last line
            filew.write(s+"\n");
            filew.flush();
            filew.close();

          }
          catch(FileNotFoundException ex) {
              System.out.println("Unable to open file '" + filename + "'");                
          }
          catch(IOException ex) {
              System.out.println("Error reading file '" + filename + "'");                   
              // Or we could just do this: 
              // ex.printStackTrace();
          }
        
        
		
	}
    public static void main(String [] args) {
    	new EditFile().Write("test.txt","test1",false);
    	new EditFile().Read("test.txt");
    }
}