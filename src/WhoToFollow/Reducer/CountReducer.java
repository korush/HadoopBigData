package WhoToFollow.Reducer;

import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import Common.UserProfile;

public class CountReducer extends Reducer<IntWritable, IntWritable, IntWritable, Text> {

    public void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
    {
    	UserProfile user = new UserProfile(key.get());
    	
    	HashMap<Integer, Integer> tempDic = new HashMap<Integer, Integer>();
    	
    	
    	
    	
        while (values.iterator().hasNext()) 
        {
            int userWithCommonFriend = values.iterator().next().get();
            if(userWithCommonFriend < 0)
            {
            	user.addCommonFriend(-1 * userWithCommonFriend, 0);
            	continue;
            }
            
            
            tempDic.put(userWithCommonFriend, tempDic.getOrDefault(userWithCommonFriend, 0) + 1);
        }
        
        user.commonFriends.forEach((k, v)-> {
        	
//        	System.out.println(k + "  " + tempDic.getOrDefault(key, 0));
        	user.addCommonFriend(k, tempDic.get(k));
        });
        
        
                
        Text result = new Text(user.toStringCommonFriends());
        context.write(new IntWritable(user.getUser()), result);
    }
}
