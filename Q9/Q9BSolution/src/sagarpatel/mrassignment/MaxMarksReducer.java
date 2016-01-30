package sagarpatel.mrassignment;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxMarksReducer 
		extends Reducer<Text, StudentMarksWritable, Text, StudentMarksWritable> {

	private static StudentMarksWritable rstu = new StudentMarksWritable();
	
	public void reduce(Text key, Iterable<StudentMarksWritable> values, Context context)
			throws IOException, InterruptedException {
		
		for (StudentMarksWritable stu : values) {
			
			if (stu.getMarks()>rstu.getMarks()) {
				rstu=stu;
			}
		}
		context.write(key, rstu);
	}
}
