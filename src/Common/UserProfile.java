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
		this.friends = new ArrayList<Integer>();
		this.commonFriends = new HashMap<Integer, Integer>();
	}
	
	private Integer user;
	
	private ArrayList<Integer> friends;
	
	private HashMap<Integer, Integer> commonFriends;
	
	public void setUser(Integer uid)
	{
		this.user = uid;
	}
	
	public Integer getUser()
	{
		return this.user;
	}
	
	public void addFriend(Integer friend)
	{
		this.friends.add(friend);
	}
	
	public boolean isFriend(Integer friend)
	{
		for (Integer f : this.friends)
            if (f == friend)
            	return true;
		
		return false;
        
	}
	
	public void addCommonFriend(Integer friend)
	{
		if(friend < 0)
		{
			this.addFriend(friend);
			this.commonFriends.remove(-1 * friend); 
			return;
		}
		
		if(this.isFriend(-1 * friend))
		{
			this.commonFriends.remove(-1 * friend);
			return;
		}
		
		this.commonFriends.put(friend, this.commonFriends.getOrDefault(friend, 0) + 1);
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
		for (Integer f : this.friends)
			sb.append(f + " ");
        
		return sb.toString().trim();
	}
	
	
	
}
