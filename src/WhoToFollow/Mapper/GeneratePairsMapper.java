package WhoToFollow.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GeneratePairsMapper extends Mapper<Object, Text, IntWritable, IntWritable>
{
	public void map(Object key, Text values, Context context) throws IOException, InterruptedException
	{
    	
        StringTokenizer st = new StringTokenizer(values.toString());
        
        IntWritable profileOwner = new IntWritable();
        IntWritable friend = new IntWritable();
        
        profileOwner.set(Integer.parseInt(st.nextToken())); 
        while (st.hasMoreTokens()) {

        	friend.set(Integer.parseInt(st.nextToken()));
        	context.write(profileOwner, friend);
        	context.write(friend, profileOwner);
        }
    }
	

}


