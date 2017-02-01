package Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class UserProfile 
{
	public UserProfile(Integer uid)
	{
		this.setUser(uid);
		this.followers = new ArrayList<Integer>();
		this.followings = new ArrayList<Integer>();
		this.commonFollowers = new HashMap<Integer, Integer>();
	}
	
	private Integer user;
	
	private ArrayList<Integer> followers;
	private ArrayList<Integer> followings;
	
	public HashMap<Integer, Integer> commonFollowers;
	
	public void setUser(Integer uid)
	{
		this.user = uid;
	}
	
	public Integer getUser()
	{
		return this.user;
	}
	
	public void addFollower(int follower)
	{
		if( this.isFollower(follower))
			return;
		
		this.followers.add(follower);
	}
	
	public boolean isFollower(int follower)
	{
		int idx = this.followers.indexOf(follower);
		return idx > -1;
	}
	
	public void addFollowing(int following)
	{
		if( this.isFollowing(following))
			return;
		
		this.followings.add(following);
	}
	
	public boolean isFollowing(int following)
	{
		int idx = this.followings.indexOf(following);
		return idx > -1;
	}
	
	
	public void addCommonFollower(Integer follower)
	{
		if( follower < 0)
		{
			this.addFollower(-1 * follower);
			this.commonFollowers.remove(-1 * follower); 
			return;
		}
		
		if(this.isFollower(follower))
		{
			this.commonFollowers.remove(-1 * follower);
			return;
		}
			
		this.commonFollowers.put(follower, this.commonFollowers.getOrDefault(follower, 0) + 1);
	}


	public String toStringCommonFriends()
	{
		Comparator<Entry<Integer, Integer>> valueComparator = new Comparator<Entry<Integer,Integer>>() 
		{
			@Override public int compare(Entry<Integer, Integer> e1, Entry<Integer, Integer> e2) 
			{ 
				return e2.getValue().compareTo(e1.getValue());
			} 
		};

		 Set<Entry<Integer, Integer>> entries = this.commonFollowers.entrySet();
		 List<Entry<Integer, Integer>> listOfEntries = new ArrayList<Entry<Integer, Integer>>(entries);
		 Collections.sort(listOfEntries, valueComparator);

		 
		StringBuffer sb = new StringBuffer(""); 
		listOfEntries.forEach((commonFollower) -> sb.append(commonFollower.getKey() + "(" + commonFollower.getValue() + ") "));
		return sb.toString();
	}
	
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer("");
		for (Integer f : this.followers)
			sb.append(f + " ");
        
		for (Integer f : this.followings)
			sb.append(f + " ");
		
		return sb.toString().trim();
	}
	
	
	
}
