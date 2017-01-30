package WhoToFollow.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class AllPairMapper extends Mapper<Object, Text, IntWritable, IntWritable> {
    // Emits (a,b) *and* (b,a) any time a friend common to a and b is found.
    public void map(Object key, Text values, Context context) throws IOException, InterruptedException {
        // Key is ignored as it only stores the offset of the line in the text file
        StringTokenizer st = new StringTokenizer(values.toString());
        // seenFriends will store the friends we've already seen as we walk through the list of friends
        ArrayList<Integer> seenFriends = new ArrayList<>(); 
        // friend1 and friend2 will be the elements in the emitted pairs.
        IntWritable friend1 = new IntWritable();
        IntWritable friend2 = new IntWritable();
        IntWritable user = new IntWritable(Integer.parseInt(st.nextToken())); 
        while (st.hasMoreTokens()) {
            // For every friend Fi found in the values,
            // we emit (Fi,Fj) and (Fj,Fi) for every Fj in the 
            // friends we have seen before. You can convince yourself
            // that this will emit all (Fi,Fj) pairs for i!=j.
            friend1.set(Integer.parseInt(st.nextToken()));
            for (Integer seenFriend : seenFriends) {
                friend2.set(seenFriend);
                context.write(friend1, friend2);
                context.write(friend2, friend1);
                
               // System.out.println("(" + friend1 + "," + friend2 + ")");
                //System.out.println("(" + friend2 + "," + friend1 + ")");
            }
            seenFriends.add(friend1.get());
            
            context.write(user, new IntWritable(-1 * friend1.get()));
           // System.out.println("(" + user + "," + new IntWritable(-1 * friend1.get()) + ")");
            
        }
    }
}

	

