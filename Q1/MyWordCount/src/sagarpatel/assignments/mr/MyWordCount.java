package sagarpatel.assignments.mr;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyWordCount {

	public static void main(String[] args) 
					throws IOException, InterruptedException, ClassNotFoundException {

		if (args.length<2) {
			
			System.err.println("usage: MyWordCount <input path> <output path>");
			System.exit(1);
		}
		
		Job job = new Job();
		
		job.setJarByClass(MyWordCount.class);
		job.setJobName("Wordcount:sagarpatel");
		
		job.setMapperClass(MyWordCountMapper.class);
		job.setReducerClass(MyWordCountReducer.class);
		job.setCombinerClass(MyWordCountReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		if (job.waitForCompletion(true)) {
	       System.out.println("--------------------------------\nJob Done Successfully\n--------------------------------");
	       System.exit(0);
		}
		else {
			System.out.println("--------------------------------\nSome Error Occured\n--------------------------------");
			System.exit(1);
		}		
	}

}
