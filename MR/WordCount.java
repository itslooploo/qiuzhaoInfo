package MR;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author loop
 * @version 1.0
 */
public class WordCount {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        // 1. create a Job ，并且指定任务的入口
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(WordCount.class);
        // 2. 指定任务的mapper以及mapper的输出类型
        job.setMapperClass(MyMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        // 3. 指定任务的reduce以及指定reduce的输出类型
        job.setReducerClass(MyReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        // 4. 指定任务的输入路径和输出类型
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        // 5. 执行任务

        job.waitForCompletion(true);
    }
}
