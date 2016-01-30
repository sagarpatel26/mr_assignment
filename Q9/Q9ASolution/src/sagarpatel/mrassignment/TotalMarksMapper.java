package sagarpatel.mrassignment;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TotalMarksMapper 
		extends Mapper<Object, Text, StudentClassWritable, IntWritable> {

	private static StudentClassWritable student = new StudentClassWritable();
	private static IntWritable marks = new IntWritable();
	
	public void map(Object key, Text value, Context context) 
			throws IOException, InterruptedException {
		
		String line = value.toString();
		if (!line.isEmpty()) {
			
			StringTokenizer st = new StringTokenizer(line,"|");

			// format:- Roll Number | School Name | Name | Age | Gender | Class | Subject | Marks
			student.setRollNo(st.nextToken());
			student.setSchoolName(st.nextToken());
			student.setName(st.nextToken());
			st.nextToken();
			st.nextToken();
			student.set_class(st.nextToken());
			st.nextToken();
			marks.set(Integer.parseInt(st.nextToken()));
			
			context.write(student, marks);
		}
	}
}
