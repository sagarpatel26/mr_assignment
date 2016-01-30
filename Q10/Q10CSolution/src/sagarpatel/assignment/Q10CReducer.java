package sagarpatel.assignment;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Q10CReducer extends Reducer<IntWritable, Text, Text, Text> {
	
	private static Text _key = new Text();
	private static Text _value = new Text();
	
	@Override
	public void reduce(IntWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		
		long sum = 0;
		long len =0;
		String nameyear = "";
		for (Text value:values) {
			String cValue = value.toString();
			if (cValue.charAt(0)=='A') {
				nameyear = cValue.substring(1);
			}
			else {
				sum+=Long.parseLong(cValue.substring(1));
				len++;
			}
		}
		double avg;
		if (len!=0)
			avg = (sum*1.0)/(len*1.0);
		else
			avg=0;
		
		_value.set(nameyear + "|" + sum + "|" + String.format("%f", avg));
		_key.set(key.toString());
		context.write(_key, _value);
	}
}
