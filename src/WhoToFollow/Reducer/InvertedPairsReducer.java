package WhoToFollow.Reducer;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import Common.UserProfile;

public class InvertedPairsReducer  extends Reducer<IntWritable, IntWritable, IntWritable, Text> 
{

    public void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException 
    {
    	UserProfile user = new UserProfile(key.get());

    	
	    while (values.iterator().hasNext()) {
	        int follower = values.iterator().next().get();
	        	user.addFollower(follower);
	    }
	
	    
	    Text result = new Text(user.toString());
	    context.write(key, result);
}

    

}
