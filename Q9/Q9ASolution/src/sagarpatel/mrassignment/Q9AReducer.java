package sagarpatel.mrassignment;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;

public class Q9AReducer 
		extends Reducer<ClassSchoolNameWritable, StudentCompositeMarksInfoWritable, 
						ClassSchoolNameWritable, StudentCompositeMarksInfoWritable> {
	
	private StudentCompositeMarksInfoWritable maxmarks;
	
	public void reduce(ClassSchoolNameWritable key, Iterable<StudentCompositeMarksInfoWritable> values, Context context) 
			throws IOException, InterruptedException {
		
		maxmarks = new StudentCompositeMarksInfoWritable();
		for (StudentCompositeMarksInfoWritable student : values) {
			
			if (student.getMarks()>maxmarks.getMarks()) {
				
				maxmarks=student;
			}
		}
		//System.out.println("[REDUCE]:" + key.toString());
		context.write(key,maxmarks);
	}
}
