package edu.cs.utexas.HadoopEx;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordCountReducer extends  Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {
    private static final Logger LOGGER = LogManager.getLogger(WordCountReducer.class.getName());

   public void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {	   
       
        int count = 0;
        for (IntWritable value : values) count += value.get();
        context.write(key, new IntWritable(count));
   }
}