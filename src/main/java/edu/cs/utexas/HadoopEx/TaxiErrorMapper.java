package edu.cs.utexas.HadoopEx;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TaxiErrorMapper extends Mapper<Object, Text, Text, BooleanWritable> {
	private static final Logger LOGGER = LogManager.getLogger(TaxiErrorMapper.class.getName());

	// Constants to parse the input file
	private static final int   NUM_COLUMNS = 17;
	private static final int   TAXI_ID_INDEX = 0;
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
	private static final int   TAXI_ID_LENGTH = 32;
	private static final int   NUM_HOURS = 24;

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] columns = value.toString().split(",");
		if (!isValidLine(columns)) return;
		Text taxiID = new Text();
		taxiID.set(columns[TAXI_ID_INDEX].trim());

		boolean isValidPickupLocation = validateLocation(columns[PICKUP_LAT_INDEX], columns[PICKUP_LONG_INDEX]);
		boolean isValidDropoffLocation = validateLocation(columns[DROPOFF_LAT_INDEX], columns[DROPOFF_LONG_INDEX]);
		BooleanWritable isValidGPS = new BooleanWritable(isValidPickupLocation && isValidDropoffLocation);
		context.write(taxiID, isValidGPS);
	}
	
	public boolean isValidLine(String[] columns) {
		if (columns.length != NUM_COLUMNS) {
			LOGGER.error("Invalid number of columns: " + columns.length);
			return false;
		}

		if (columns[TAXI_ID_INDEX].length() != TAXI_ID_LENGTH) {
			LOGGER.error("Invalid taxi ID: " + columns[TAXI_ID_INDEX]);
			return false;
		}

		if (!validateTripDuration(columns[TRIP_DURATION_INDEX])) {
			LOGGER.error("Invalid trip duration: " + columns[TRIP_DURATION_INDEX]);
			return false;
		}

		String[] numericColumns = new String[NUMERIC_COLUMNS.length];
		for (int i = 0; i < NUMERIC_COLUMNS.length; i++) {
			numericColumns[i] = columns[NUMERIC_COLUMNS[i]];
		}
		boolean isValidTotalAmounts = validateAmounts(numericColumns, columns[TOTAL_AMOUNT_INDEX]);
		if (!isValidTotalAmounts) {
			LOGGER.error("Invalid total amount: " + columns[TOTAL_AMOUNT_INDEX]);
			return false;
		}

		return true;
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
}