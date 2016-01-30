package sagarpatel.mrassignments;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class TotalMarksReducer 
		extends	Reducer<StudentClassWritable, IntWritable, StudentClassWritable, IntWritable> {
	
	private static IntWritable totalMarks = new IntWritable();
	
	public void reduce(StudentClassWritable key, Iterable<IntWritable> values, Context context) 
			throws IOException, InterruptedException {
		
		int sum = 0;
		for (IntWritable marks : values) {
			
			sum+=marks.get();
		}
		totalMarks.set(sum);
		
		context.write(key, totalMarks);
	}
}
