package WhoToFollow.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class AllPairMapper extends Mapper<Object, Text, IntWritable, IntWritable> 
{
    public void map(Object key, Text values, Context context) throws IOException, InterruptedException {

    	StringTokenizer st = new StringTokenizer(values.toString());

        ArrayList<Integer> seenFollowers = new ArrayList<>(); 

        IntWritable f1 = new IntWritable();
        IntWritable f2 = new IntWritable();
        IntWritable user = new IntWritable(Integer.parseInt(st.nextToken()));
        

        while (st.hasMoreTokens()) 
        {
        	f1.set(Integer.parseInt(st.nextToken()));
            
        	if(f1.get() < 0 )
        	{
        		context.write(user, f1);
        		continue;
        	}
        	
            for (Integer seenFollower : seenFollowers) {
            	
            	f2.set(seenFollower);
                context.write(f1, f2);
                context.write(f2, f1);
            
            }
            
            seenFollowers.add(f1.get());
            
        }
    }
}

	

