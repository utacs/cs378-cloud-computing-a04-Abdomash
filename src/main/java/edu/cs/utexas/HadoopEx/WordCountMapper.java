package edu.cs.utexas.HadoopEx;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordCountMapper extends Mapper<Object, Text, IntWritable, IntWritable> {
	private static final Logger LOGGER = LogManager.getLogger(WordCountMapper.class.getName());

	// Constants to parse the input file
	private static final int   NUM_COLUMNS = 17;
	private static final int   PICKUP_DATE_INDEX = 2;
	private static final int   DROPOFF_DATE_INDEX = 3;
	private static final int   TRIP_DURATION_INDEX = 4;
	private static final int   PICKUP_LAT_INDEX = 6;
	private static final int   PICKUP_LONG_INDEX = 7;
	private static final int   DROPOFF_LAT_INDEX = 8;
	private static final int   DROPOFF_LONG_INDEX = 9;
	private static final int[] NUMERIC_COLUMNS = { 11, 12, 13, 14, 15 };
	private static final int   TOTAL_AMOUNT_INDEX = 16;
	private static final float FLT_EPSILON = 0.001f;
	private static final int   NUM_HOURS = 24;

	private final IntWritable counter = new IntWritable(1);

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		
		int[] hours = getHoursWithInvalidGPS(value.toString());
		if (hours == null) return;
		for (int hour : hours) {
			context.write(new IntWritable(hour), counter);
		}
	}
	
	public int[] getHoursWithInvalidGPS(String line) {
		String[] columns = line.split(",");

		// validate number of columns
		if (columns.length != NUM_COLUMNS) {
			LOGGER.error("Invalid number of columns: " + columns.length);
			return null;
		}

		// validate trip duration
		if (!validateTripDuration(columns[TRIP_DURATION_INDEX])) {
			LOGGER.error("Invalid trip duration: " + columns[TRIP_DURATION_INDEX]);
			return null;
		}

		// validate subamounts and total amount match
		String[] numericColumns = new String[NUMERIC_COLUMNS.length];
		for (int i = 0; i < NUMERIC_COLUMNS.length; i++) {
			numericColumns[i] = columns[NUMERIC_COLUMNS[i]];
		}
		boolean isValidTotalAmounts = validateAmounts(numericColumns, columns[TOTAL_AMOUNT_INDEX]);
		if (!isValidTotalAmounts) {
			LOGGER.error("Invalid total amount: " + columns[TOTAL_AMOUNT_INDEX]);
			return null;
		}

		// validate pickup and dropoff hours
		int pickupHour = getHour(columns[PICKUP_DATE_INDEX]);
		int dropoffHour = getHour(columns[DROPOFF_DATE_INDEX]);
		if (pickupHour < 1 || pickupHour > NUM_HOURS || dropoffHour < 1 || dropoffHour > NUM_HOURS) {
			LOGGER.error("Invalid pickup/dropoff hour: " + pickupHour + " " + dropoffHour);
			return null;
		}

		// Parse pickup and dropoff GPS coordinates
		boolean isValidPickupLocation = validateLocation(columns[PICKUP_LAT_INDEX], columns[PICKUP_LONG_INDEX]);
		boolean isValidDropoffLocation = validateLocation(columns[DROPOFF_LAT_INDEX], columns[DROPOFF_LONG_INDEX]);

		if (isValidPickupLocation && isValidDropoffLocation) {
			return new int[] { pickupHour, dropoffHour };
		} 
		
		if (isValidPickupLocation) {
			return new int[] { pickupHour };
		} 
		
		if (isValidDropoffLocation) {
			return new int[] { dropoffHour };
		}

		return null;
	}

	private boolean validateTripDuration(String tripDuration) {
		try {
			int duration = Integer.parseInt(tripDuration);
			return duration > 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean validateLocation(String latitude, String longitude) {
		try {
			Float lat = Float.parseFloat(latitude);
			Float lon = Float.parseFloat(longitude);
			return Math.abs(lat) < FLT_EPSILON && Math.abs(lon) < FLT_EPSILON;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean validateAmounts(String[] numericColumns, String totalAmountString) {
		try {
			float totalAmount = Float.parseFloat(totalAmountString);
			float sum = 0;
			for (String column : numericColumns) {
				sum += Float.parseFloat(column);
			}
			return (totalAmount < 500) && (Math.abs(sum - totalAmount) < FLT_EPSILON);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private int getHour(String date) {
		try {
			String[] parts = date.split(" ");
			String[] time = parts[1].split(":");
			int hour = Integer.parseInt(time[0]);
			if (hour < 0 || hour >= NUM_HOURS) {
				return -1;
			}
			return Integer.parseInt(time[0]) + 1;
		} catch (NumberFormatException e) {
			return -1;
		}
	}
}