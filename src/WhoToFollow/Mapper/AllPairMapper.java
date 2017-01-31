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

        ArrayList<Integer> seenFriends = new ArrayList<>(); 

        IntWritable friend1 = new IntWritable();
        IntWritable friend2 = new IntWritable();
        IntWritable user = new IntWritable(Integer.parseInt(st.nextToken()));
        

        while (st.hasMoreTokens()) 
        {
            friend1.set(Integer.parseInt(st.nextToken()));
            for (Integer seenFriend : seenFriends) {
                friend2.set(seenFriend);
                context.write(friend1, friend2);
                context.write(friend2, friend1);
            }
            seenFriends.add(friend1.get());
            
            context.write(user, new IntWritable(-1 * friend1.get()));
            
        }
    }
}

	

