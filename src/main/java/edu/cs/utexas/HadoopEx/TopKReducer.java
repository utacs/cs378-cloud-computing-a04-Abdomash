package edu.cs.utexas.HadoopEx;

import java.io.IOException;
import java.util.PriorityQueue;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.cs.utexas.HadoopEx.TopKTaxiMap.Entry;

public class TopKReducer extends  Reducer<Text, Text, Text, FloatWritable> {
     private static final Logger LOGGER = LogManager.getLogger(TaxiErrorReducer.class.getName());
     
     private static final int K = 10;
     private TopKTaxiMap topK = new TopKTaxiMap(K);

     public void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {

          for (FloatWritable value : values) {
               topK.add(key.toString(), value.get());
          }

          for (Entry entry : topK.getTopK()) {
               Text taxiID = new Text(entry.getKey());
               FloatWritable errorRate = new FloatWritable(entry.getValue());
               context.write(taxiID, errorRate);
          }
     }
}