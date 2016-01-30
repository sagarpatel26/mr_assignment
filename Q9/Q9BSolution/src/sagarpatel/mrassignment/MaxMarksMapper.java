package sagarpatel.mrassignment;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxMarksMapper 
		extends Mapper<Object, Text, Text, StudentMarksWritable> {

	
	private static StudentMarksWritable student = new StudentMarksWritable(); 
	private static Text _class = new Text();
	
	public void map(Object key, Text value, Context context) 
			throws IOException, InterruptedException {
		
		String line = value.toString();
		if (!line.isEmpty()) {
			
			StringTokenizer st = new StringTokenizer(line,"|");

			// format:- Roll Number | Name | School Name | class | Marks
			student.setRollNo(st.nextToken());
			student.setName(st.nextToken());
			student.setSchoolName(st.nextToken());
			_class.set(st.nextToken());
			student.setMarks(Integer.parseInt(st.nextToken()));
			context.write(_class,student);
		}
	}
}
