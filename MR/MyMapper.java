package MR;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


/**
 * @author loop
 * @version 1.0
 */
public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key , Text value, Context context)
            throws IOException, InterruptedException {
        String data = value.toString();
        String[] words = data.split(" ");
        for (String w: words){
            context.write(new Text(w), new IntWritable(1));
        }
    }
}
