package sagarpatel.mrassignments;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MarksSortMapper 
		extends Mapper<Object, Text, SchoolMarksWritable, Text> {

	private static SchoolMarksWritable schoolmarks = new SchoolMarksWritable();
	private static Text RollNoName = new Text(); 
	
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		if (!line.isEmpty()) {
			
			StringTokenizer st = new StringTokenizer(line,"|");
			// format:- Rollno | Name | Schoolname | class | marks
			
			String rollNo = st.nextToken();
			String name = st.nextToken();
			schoolmarks.setSchoolName(st.nextToken());
			String _class = st.nextToken();
			schoolmarks.setMarks(Integer.parseInt(st.nextToken()));
			RollNoName.set(_class + "|" + rollNo + "|" + name);
			
			context.write(schoolmarks, RollNoName);
		}
	}
}
