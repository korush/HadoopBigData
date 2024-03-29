package WhoToFollow.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class InvertedPairsMapper extends Mapper<Object, Text, IntWritable, IntWritable>
{
	public void map(Object key, Text values, Context context) throws IOException, InterruptedException
	{
    	
        StringTokenizer st = new StringTokenizer(values.toString());
        
        IntWritable user = new IntWritable();
               
        IntWritable friend = new IntWritable();
        
        user.set(Integer.parseInt(st.nextToken()));
        
        while (st.hasMoreTokens()) {
        	friend.set(Integer.parseInt(st.nextToken()));
        	context.write(friend, user);
        	context.write(user, new IntWritable(-1 * friend.get()));
        }
    }
	

}


