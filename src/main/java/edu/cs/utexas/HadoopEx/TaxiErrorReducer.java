package edu.cs.utexas.HadoopEx;

import java.io.IOException;

import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TaxiErrorReducer extends  Reducer<Text, Text, Text, Text> {
     private static final Logger LOGGER = LogManager.getLogger(TaxiErrorReducer.class.getName());
    
     public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
          float totalAmount = 0;
          float totalTripDuration = 0;     
     
          for (Text value : values) {               
               String[] columns = value.toString().split("\t");
               totalAmount += Float.parseFloat(columns[0]);
               totalTripDuration += Float.parseFloat(columns[1]);
          }
          context.write(key, new Text(totalAmount + "\t" + totalTripDuration));
     }
}