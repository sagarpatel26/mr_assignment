package sagarpatel.mrassignment;

import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class InverseIndexerReducer extends
		Reducer<Text, Text, Text, Text> {

	private static Text fileList = new Text(); 
	
	public void reduce(Text key,Iterable<Text> values, Context context) 
			throws IOException, InterruptedException {
		
		
		HashSet<String> tmpList = new HashSet<String>();
		for (Text value : values) {
			
			tmpList.add(value.toString());
		}
		
		StringBuilder result = new StringBuilder("{");
		
		for (String file:tmpList) {
			
			result.append(file+",");
		}
		result.replace(result.length()-1, result.length(), "}");
		fileList.set(result.toString());
		context.write(key, fileList);
	}
}
