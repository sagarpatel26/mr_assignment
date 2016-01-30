package sagarpatel.assignment;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class Q10BSolution {

	public static void main(String[] args) 
			throws IOException, InterruptedException, ClassNotFoundException {

		Configuration conf = new Configuration();
		String otherArgs[] = new GenericOptionsParser(conf, args).getRemainingArgs();
		
		conf.set("mapred.textoutputformat.separator", "|");
		
		if (otherArgs.length<3) {
			System.err.println("usage: Q10Solution <input path> <output path>");
			System.exit(1);
		} 
		
		Job job = new Job(conf); 
		
		job.setJarByClass(Q10BSolution.class);
		job.setJobName("Q10B:sagarpatel");

		job.setReducerClass(Q10BReducer.class);
		
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		MultipleInputs.addInputPath(job, new Path(otherArgs[0]), TextInputFormat.class, MovieMapper.class);
		MultipleInputs.addInputPath(job, new Path(otherArgs[1]), TextInputFormat.class, MovieRatingsMapper.class);
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[2]));
		
		if (job.waitForCompletion(true)) {
			System.out.println("----------------------------\nJob Done Successfully\n----------------------------");
		}
		else {
			System.out.println("----------------------------\nSome Error Occurred!!\n----------------------------");
		}
	}

}
