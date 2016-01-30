package sagarpatel.mrassignment;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Q9AMapper extends Mapper<Object, Text, ClassSchoolNameWritable, StudentCompositeMarksInfoWritable> {

	private static ClassSchoolNameWritable classSchoolName = new ClassSchoolNameWritable();
	private static StudentCompositeMarksInfoWritable student = new StudentCompositeMarksInfoWritable();
	
	public void map(Object key, Text value, Context context) 
			throws IOException, InterruptedException {
		
		String line = value.toString();
		if (!line.isEmpty()) {
			
			StringTokenizer st = new StringTokenizer(line,"|");
			// format rollNo | name | schoolname | _class | marks
			student.setRollNo(st.nextToken());
			student.setName(st.nextToken());
			classSchoolName.setSchoolName(st.nextToken());
			classSchoolName.set_class(st.nextToken());
			student.setMarks(Integer.parseInt(st.nextToken()));
			System.out.println("[REDUCE]:" + classSchoolName.toString());
			context.write(classSchoolName, student);
		}
	}
	
}
