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
    	
        while (values.iterator().hasNext()) 
        {
            int f = values.iterator().next().get();
            user.addCommonFollower(f);
        }
        
        Text result = new Text(user.toStringCommonFriends());
        context.write(new IntWritable(user.getUser()), result);
    }
}
