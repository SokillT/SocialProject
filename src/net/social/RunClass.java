package net.social;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import java.io.*;

public class RunClass {
	public static int GET_LineCurrent(){
		BufferedReader bufferedReader = null;
		String line = null;
		int linenumber = 0;
		String fileName = "current.txt";
		try {
			bufferedReader = new EditFile().Read(fileName);
			while((line = bufferedReader.readLine()) != null) {
				linenumber = Integer.valueOf(line);
				System.out.println(linenumber);
			}    
			bufferedReader.close(); 
		}catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
		return linenumber;
	}
	
	public static void GET_CurrentID(int linenumber){
		BufferedReader bufferedReader = null;
		String fileName = "queue.txt";
		String line = null;
		try {
			bufferedReader = new EditFile().Read(fileName);
			int i = 1;
			while((line = bufferedReader.readLine()) != null) {
				if(i == linenumber){
					Global.currentID = Long.parseLong(line, 10);
					System.out.println(Global.currentID);
					break;
				}
				else{
					i=i+1;
				}
			}    
			bufferedReader.close(); 
		}catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
	} 
	
	public static void GET_FollowingID(){
		Global.rateFriendID = new CheckLimit().start("/friends/ids");
		new GET_following().start();
		new EditFile().Read(Global.fileName);
	}
	
	public static void GET_Details(){
		new GET_Details().start(Global.currentID);
	}

	public static void main(String[] args){
		while(Global.start){
			Global.linenumber = GET_LineCurrent();			//GET linenumber current
			GET_CurrentID(Global.linenumber);				//GET CurrentID
		
			//CHECK NOT GET in finish.txt
			
			GET_Details();							//GET Details of CurrentID
			if(Global.inThai == false){
				Global.linenumber += 1;
				System.out.println(Global.inThai);
			}
			else{
				GET_FollowingID();					//GET FollowingID of currentID
				Global.linenumber += 1;
			}						
		new EditFile().Write("current.txt",Integer.toString(Global.linenumber),false);
		System.out.println(Global.rateFriendID);
		}
	}

}
