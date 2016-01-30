package sagarpatel.mrassignment;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class Q10ASolution {

	public static void main(String[] args) 
			throws IOException, InterruptedException, ClassNotFoundException {
		
		Configuration conf = new Configuration();
		String otherArgs[] = new GenericOptionsParser(conf, args).getRemainingArgs();
		
		conf.set("mapred.textoutputformat.separator", "|");
		
		if (args.length<2) {
			System.err.println("usage: Q10Solution <input path> <output path>");
			System.exit(1);
		} 
		
		Job job = new Job(conf); 
		
		job.setJarByClass(Q10ASolution.class);
		job.setJobName("Q10A:sagarpatel");
		
		job.setMapperClass(Q10AMapper.class);
		job.setCombinerClass(Q10AReducer.class);
		job.setReducerClass(Q10AReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(MovieInfoWritable.class);
		job.setOutputValueClass(Text.class);
		job.setOutputValueClass(MovieInfoWritable.class);
		
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
