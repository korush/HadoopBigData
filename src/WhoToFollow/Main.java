package WhoToFollow;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import WhoToFollow.Mapper.GeneratPairsMapper;
import WhoToFollow.Reducer.GeneratePairsReducer;

public class Main {
	
	public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException
	{
			Configuration conf = new Configuration();
	        Job job = Job.getInstance(conf, "people you may know");
	        job.setJarByClass(Main.class);
	        job.setMapperClass(GeneratPairsMapper.class);
	        job.setReducerClass(GeneratePairsReducer.class);
	        job.setOutputKeyClass(IntWritable.class);
	        job.setOutputValueClass(IntWritable.class);
	        FileInputFormat.addInputPath(job, new Path(args[0]));
	        FileOutputFormat.setOutputPath(job, new Path(args[1]));
	        System.exit(job.waitForCompletion(true) ? 0 : 1);
	    
	
	}

}
