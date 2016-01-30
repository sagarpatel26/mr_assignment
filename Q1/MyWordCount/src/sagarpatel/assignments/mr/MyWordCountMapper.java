package sagarpatel.assignments.mr;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyWordCountMapper 
		extends Mapper<Object, Text, Text, LongWritable> {
	
	private static final LongWritable ONE = new LongWritable(1);    
	private static Text word = new Text();
	
	public void map(Object key, Text value, Context context) 
			throws IOException, InterruptedException {
		
		String line = value.toString();
		if (!line.isEmpty()) {
		
			StringTokenizer st = new StringTokenizer(line," ");
			while(st.hasMoreTokens()) {
				
				word.set(st.nextToken().toLowerCase());
				context.write(word, ONE);
			}
		}
	}
	
}
