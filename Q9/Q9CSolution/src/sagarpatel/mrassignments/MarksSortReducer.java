package sagarpatel.mrassignments;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MarksSortReducer 
		extends Reducer<SchoolMarksWritable, Text, SchoolMarksWritable, Text> {

	public void reduce(SchoolMarksWritable key,Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		
		for (Text student : values) {
			context.write(key, student);
		}
	}
	
}
