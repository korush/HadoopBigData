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
        
        IntWritable user = new IntWritable();
               
        int friend;
        
        user.set(Integer.parseInt(st.nextToken()));
        
        while (st.hasMoreTokens()) {
        	friend = Integer.parseInt(st.nextToken());
        	context.write(user, new IntWritable(-1 * friend)  );
        	context.write(new IntWritable(friend), user);
        }
    }
	

}


