package WhoToFollow.Reducer;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import Common.UserProfile;

public class GeneratePairsReducer  extends Reducer<IntWritable, IntWritable, IntWritable, Text> 
{

    public void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException 
    {
    	UserProfile user = new UserProfile(key.get());
	    	        
	    while (values.iterator().hasNext()) {
	        int friend = values.iterator().next().get();
	        if(!user.isFriend(friend))
	        	user.addFriend(friend);
	    }
	
	    Text result = new Text(user.toString());
	    context.write(key, result);
}

    

}
