package sagarpatel.assignment;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Q10BReducer extends Reducer<IntWritable, Text, Text, Text> {

	private static Text Name = new Text();
	private static Text Year = new Text();
	
	@Override
	public void reduce(IntWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		
		boolean toWrite = true;
		String name=null,year=null;
		
		for (Text value:values) {
			
			String cValue = value.toString();
			if(cValue.equals("B")) {
				toWrite = false;
				break;
			}
			if(cValue.charAt(0)=='A') {
				StringTokenizer st = new StringTokenizer(cValue,"|");
				name = st.nextToken();
				year = st.nextToken();
			}
		}
		
		if (toWrite) {
			Name.set(name.substring(1));
			Year.set(year);
			context.write(Name, Year);
		}
	}
}
