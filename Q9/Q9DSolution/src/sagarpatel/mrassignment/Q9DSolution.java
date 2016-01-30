package sagarpatel.mrassignment;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class Q9DSolution {

	public static void main(String[] args) 
			throws IOException, InterruptedException, ClassNotFoundException {

		Configuration conf = new Configuration();
		String otherArgs[] = new GenericOptionsParser(conf, args).getRemainingArgs();
		
		conf.set("mapred.textoutputformat.separator", "|");
		
		if (otherArgs.length<2) {
			System.err.println("usage: Q9DSolution <input path> <output path>");
			System.exit(1);
		} 
		
		Job job = new Job(conf, "Q9DSolution");
		
		job.setJarByClass(Q9DSolution.class);
		
		job.setMapperClass(Q9DMapper.class);
		job.setReducerClass(Q9DReducer.class);
		
		job.setMapOutputKeyClass(ClassSchoolNameWritable.class);
		job.setMapOutputValueClass(BoysGirlsMarksWritable.class);
		job.setOutputKeyClass(ClassSchoolNameWritable.class);
		job.setOutputValueClass(BoysGirlsMarksWritable.class);
		
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
