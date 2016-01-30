package sagarpatel.mrassignment;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Q9DMapper 
		extends Mapper<Object, Text, ClassSchoolNameWritable, BoysGirlsMarksWritable> {

	private static BoysGirlsMarksWritable boysgirlsmarks = new BoysGirlsMarksWritable();
	private static ClassSchoolNameWritable classschool = new ClassSchoolNameWritable();
	
	@Override
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		if (!line.isEmpty()) {

			boysgirlsmarks.setBoysTotalMarks(0);
			boysgirlsmarks.setGirlsTotalMarks(0);
			// Roll Number | School Name | Name | Age | Gender | Class | Subject | Marks
			StringTokenizer st = new StringTokenizer(line,"|");
			st.nextToken();
			classschool.setSchoolName(st.nextToken());
			st.nextToken();
			st.nextToken();
			char g = st.nextToken().charAt(0);
			classschool.set_class(st.nextToken());
			st.nextToken();
			int marks = Integer.parseInt(st.nextToken());
			if (g=='F')
				boysgirlsmarks.setGirlsTotalMarks(marks);
			else
				boysgirlsmarks.setBoysTotalMarks(marks);

			context.write(classschool, boysgirlsmarks);
		}
	}
}
