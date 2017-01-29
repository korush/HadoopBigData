package Common;

import java.util.ArrayList;

public class UserProfile 
{
	public UserProfile(Integer uid)
	{
		this.setUser(uid);
		this.friends = new ArrayList<Integer>();
	}
	
	private Integer user;
	
	private ArrayList<Integer> friends;
	
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

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer("");
		for (Integer f : this.friends)
			sb.append(f + " ");
        
		return sb.toString().trim();
	}
	
	
	
}
