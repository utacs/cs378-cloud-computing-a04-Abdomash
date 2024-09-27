package edu.cs.utexas.HadoopEx;

import java.io.IOException;

import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TaxiErrorReducer extends  Reducer<Text, BooleanWritable, Text, Text> {
    private static final Logger LOGGER = LogManager.getLogger(TaxiErrorReducer.class.getName());
    
    public void reduce(Text key, Iterable<BooleanWritable> values, Context context) throws IOException, InterruptedException {
          int valid = 0;
          int invalid = 0;
          for (BooleanWritable value : values) {
        	  if (value.get()) valid++;
        	  else invalid++;
          }
          context.write(key, new Text(valid + "\t" + invalid));
     }
}