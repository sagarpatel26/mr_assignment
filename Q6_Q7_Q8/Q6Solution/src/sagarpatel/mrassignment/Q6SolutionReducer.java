package sagarpatel.mrassignment;

import java.io.IOException;
import java.util.HashMap;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Q6SolutionReducer extends
		Reducer<Text, Text, Text, Text> {
	
	private static Text result = new Text(); 
	
	public void reduce(Text key,Iterable<Text> values, Context context) 
			throws IOException, InterruptedException {
			
		HashMap<String, Long> wc = new HashMap<>(); 
		for (Text value : values) {
			
			String word = value.toString();
			long pv;
			if (!wc.containsKey(word)) {
				pv = 0;
			}
			else {
				pv = (wc.get(word));
			}
			pv+=1;
			wc.put(word, pv);
		}
		
		for (String word : wc.keySet()) {
			
			result.set(word + "," + wc.get(word));
			context.write(key, result);
		}
	}
	
}
