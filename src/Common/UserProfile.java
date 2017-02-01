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
		this.commonFriends = new HashMap<Integer, Integer>();
	}
	
	private Integer user;
	
	private ArrayList<Integer> followers;
	
	public HashMap<Integer, Integer> commonFriends;
	
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
		if( this.isFollwer(follower))
			return;
		
		this.followers.add(follower);
	}
	
	public boolean isFollwer(int follower)
	{
		int idx = this.followers.indexOf(follower);
		return idx > -1;
	}
	
	
	public void addCommonFriend(Integer friend, Integer count)
	{
		this.commonFriends.put(friend, count);
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

		 Set<Entry<Integer, Integer>> entries = this.commonFriends.entrySet();
		 List<Entry<Integer, Integer>> listOfEntries = new ArrayList<Entry<Integer, Integer>>(entries);
		 Collections.sort(listOfEntries, valueComparator);

		 
		StringBuffer sb = new StringBuffer(""); 
		listOfEntries.forEach((commonFriend) -> sb.append(commonFriend.getKey() + "(" + commonFriend.getValue() + ") "));
		return sb.toString();
	}
	
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer("");
		for (Integer f : this.followers)
			sb.append(f + " ");
        
		return sb.toString().trim();
	}
	
	
	
}
