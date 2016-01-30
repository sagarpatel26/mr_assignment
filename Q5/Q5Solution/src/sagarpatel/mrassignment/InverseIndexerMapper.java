package sagarpatel.mrassignment;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class InverseIndexerMapper extends
		Mapper<Object, Text, Text, Text> {

	private static Text filename = new Text();
	private static Text word = new Text();
	
	public void map(Object key, Text value, Context context) 
			throws IOException, InterruptedException {
		
		String line = value.toString();
		if (!line.isEmpty()) {
			
			StringTokenizer st = new StringTokenizer(line);
			
			filename.set( ((FileSplit) context.getInputSplit()).getPath().getName().toLowerCase() );
			
			while(st.hasMoreTokens()) {
				
				word.set(st.nextToken().toLowerCase());
				context.write(word, filename);
			}
		}
	}
	
}
