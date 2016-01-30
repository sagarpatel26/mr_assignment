package sagarpatel.mrassignment;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class Q9BSolution {

	public static void main(String args[]) 
			throws IOException,InterruptedException, ClassNotFoundException {
		
		Configuration conf = new Configuration();
		conf.set("mapred.textoutputformat.separator", "|");
		String otherArgs[] = (new GenericOptionsParser(conf, args)).getRemainingArgs();
		if (otherArgs.length!=2) {
			System.err.println("usage: Q9 <input path> <output path>");
			System.exit(1);
		}
		
		Job job = new Job(conf, "Q9ASolutionJob1:sagarpatel");
		job.setJarByClass(Q9BSolution.class);
		
		job.setMapperClass(TotalMarksMapper.class);
		job.setReducerClass(TotalMarksReducer.class);
		
		job.setOutputKeyClass(StudentClassWritable.class);
		job.setOutputValueClass(IntWritable.class);
		
		String tmpOutput = (otherArgs[0] + ((otherArgs[0].charAt(otherArgs[0].length()-1))=='/'?"tmpop":"/tmpop") ); 
		
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(tmpOutput));
		
		if (job.waitForCompletion(true)) {
			System.out.println("----------------------------\nJob1 Done Successfully!!\n----------------------------");
		}
		else {
			System.out.println("----------------------------\nJob1 Some Error Occurred\n----------------------------");
		}
		
		Job job1 = new Job(conf, "Q9ASolutionJob2:sagarpatel");
		job1.setJarByClass(Q9BSolution.class);
		
		job1.setMapperClass(MaxMarksMapper.class);
		job1.setReducerClass(MaxMarksReducer.class);
		
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(StudentMarksWritable.class); 
		
		FileInputFormat.addInputPath(job1, new Path(tmpOutput));
		FileOutputFormat.setOutputPath(job1, new Path(otherArgs[1]));
		
		if (job1.waitForCompletion(true)) {
			System.out.println("----------------------------\nJob1 Done Successfully!!\n----------------------------");
		}
		else {
			System.out.println("----------------------------\nJob1 Some Error Occurred\n----------------------------");
		}
			
		FileSystem.get(conf).delete(new Path(tmpOutput), true);
	}
}
