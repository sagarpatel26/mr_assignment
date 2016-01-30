package sagarpatel.mrassignment;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class Q6Solution {

	public static void main(String[] args) 
			throws IOException, InterruptedException, ClassNotFoundException {
		
		Configuration conf = new Configuration();
		String otherArgs[] = new GenericOptionsParser(conf, args).getRemainingArgs();
		
		conf.set("mapred.textoutputformat.separator", "+");
		
		
		/*******************************************************************************
		 * For Problem 7:- 
		 * uncomment next line 
		 * 
		 * conf.set("mapred.reduce.tasks","4");
		 *******************************************************************************/
		
		if (otherArgs.length<2) {
			System.err.println("usage: Q6Solution <input path> <output path>");
			System.exit(1);
		} 
		
		Job job = new Job(conf);  
		
		job.setJarByClass(Q6Solution.class);
		job.setJobName("InverseIndexer:sagarpatel");
		
		/*******************************************************************************
		 * For Problem 8:- 
		 * uncomment next line and comment previous line
		 * 
		 * job.setJobName("Problem8");
		 *******************************************************************************/
		
		job.setMapperClass(Q6SolutionMapper.class);
		job.setReducerClass(Q6SolutionReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		//System.out.println(otherArgs[0] + " " + otherArgs[1]);
		
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		
		if (job.waitForCompletion(true)) {
			System.out.println("----------------------------\nJob Done Successfully\n----------------------------");
		}
		else {
			System.out.println("----------------------------\nSome Error Occurred!!\n----------------------------");
		}
	}

}
