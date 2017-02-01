package WhoToFollow;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import WhoToFollow.Mapper.AllPairMapper;
import WhoToFollow.Mapper.InvertedPairsMapper;
import WhoToFollow.Reducer.CountReducer;
import WhoToFollow.Reducer.InvertedPairsReducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Main {
	
	private static boolean runJob(String jobName, String input,
								  String output, Class<? extends Mapper> mapperClass,
								  Class<? extends Reducer> reducerClass )
			throws Exception, IOException, InterruptedException, ClassNotFoundException
	{
		Configuration conf = new Configuration();
		
		FileSystem fs = FileSystem.get(conf);
		fs.delete(new Path(output), true);
		Job job = Job.getInstance(conf, jobName);
        job.setJarByClass(Main.class);
        job.setMapperClass(mapperClass);
        job.setReducerClass(reducerClass);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));
        return job.waitForCompletion(true);

	}
	
	public static void main(String args[]) throws Exception, IOException, InterruptedException, ClassNotFoundException
	{
		if(runJob("InvertedList", args[0], "FullList", InvertedPairsMapper.class, InvertedPairsReducer.class))
			System.exit(runJob("Who to Follow", "FullList", args[1], AllPairMapper.class, CountReducer.class) ? 0 : 1);
	}
}