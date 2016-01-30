package sagarpatel.mrassignments;

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

public class Q9CSolution {

	public static void main(String[] args) 
			throws IOException, InterruptedException, ClassNotFoundException {
		
		Configuration conf = new Configuration();
		conf.set("mapred.textoutputformat.separator", "|");
		
		String otherArgs[] = (new GenericOptionsParser(conf,args)).getRemainingArgs();
		if (otherArgs.length!=2) {
			
			System.err.println("usage: Q9CSolution <input path> <output path>");
			System.exit(1);
		}
		
		Job job1 = new Job(conf,"TotalMarksjob:sagarpatel");
		job1.setJarByClass(Q9CSolution.class);
		
		job1.setMapperClass(TotalMarksMapper.class);
		job1.setReducerClass(TotalMarksReducer.class);
		
		job1.setOutputKeyClass(StudentClassWritable.class);
		job1.setOutputValueClass(IntWritable.class);
		
		String tmpOutput = (otherArgs[0] + ((otherArgs[0].charAt(otherArgs[0].length()-1))=='/'?"tmpop":"/tmpop") );
		
		FileInputFormat.addInputPath(job1, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job1, new Path(tmpOutput));
		
		if (job1.waitForCompletion(true)) {
			System.out.println("----------------------------\nJob1 Done Successfully!!\n----------------------------");
		}
		else {
			System.out.println("----------------------------\nJob1 Some Error Occurred\n----------------------------");
		}
		
		
		/********************************** JOB 2 Sorting ***********************************************************/
		
		Job job2 = new Job(conf, "SortingJob:Q9C");
		job2.setJarByClass(Q9CSolution.class);
		
		job2.setMapperClass(MarksSortMapper.class);
		job2.setReducerClass(MarksSortReducer.class);
		
		job2.setOutputKeyClass(SchoolMarksWritable.class);
		job2.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job2, new Path(tmpOutput));
		FileOutputFormat.setOutputPath(job2, new Path(otherArgs[1]));
		
		if (job2.waitForCompletion(true)) {
			System.out.println("----------------------------\nJob2 Done Successfully!!\n----------------------------");
		}
		else {
			System.out.println("----------------------------\nJob2 Some Error Occurred\n----------------------------");
		}
		
		FileSystem.get(conf).delete(new Path(tmpOutput), true);
 	}

}
