package MR;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;

import java.io.IOException;

/**
 * @author loop
 * @version 1.0
 */
public class MyReduce extends Reducer<Text, IntWritable, Text, IntWritable>{

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context)
            throws IOException, InterruptedException {
        super.reduce(key, values, context);
        int total = 0;
        for (IntWritable v : values){
            total += v.get();
        }
        context.write(key, new IntWritable(total));
    }
}
