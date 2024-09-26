package edu.cs.utexas.HadoopEx;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordCountReducer extends  Reducer<Text, IntWritable, Text, IntWritable> {
    private static final Logger LOGGER = LogManager.getLogger(WordCount.class.getName());

   public void reduce(Text text, Iterable<IntWritable> values, Context context)
           throws IOException, InterruptedException {
	   
       int sum = 0;
       
       for (IntWritable value : values) {
           sum += value.get();
       }
       
       context.write(text, new IntWritable(sum));
   }
}