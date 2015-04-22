package net.social;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import java.io.*;

public class GET_Details {
	
	public static void start(long id){
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(Global.CONSUMER_KEY, Global.CONSUMER_SECRET);
		
		AccessToken oathAccessToken = new AccessToken(Global.accessToken, Global.accessTokenSecret);
		twitter.setOAuthAccessToken(oathAccessToken);
		
		try{
			User user = twitter.showUser(id);
			if(user.getLocation().toLowerCase().contains("thailand")){
				System.out.println(user.getLocation());
				Global.fileName = user.getScreenName() + ".txt";
				new EditFile().Write(Global.fileName,"ID : " + Long.toString(id),false);
				new EditFile().Write(Global.fileName,"ScreenName : " + user.getScreenName(),true);
				new EditFile().Write(Global.fileName,"Location : " + user.getLocation(),true);
				new EditFile().Write(Global.fileName,"StatusCount : " + user.getStatusesCount(),true);
				new EditFile().Write(Global.fileName,"FollowerCount : " + user.getFollowersCount(),true);
				new EditFile().Write(Global.fileName,"FollowingCount : " + user.getFriendsCount(),true);
				new EditFile().Write(Global.fileName,"List of FollowingID : ",true);
			}
			else{
				Global.inThai = false;
				return;
			}
		}catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get following' ids: " + te.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GET_Details().start(Global.currentID);
	}

}
