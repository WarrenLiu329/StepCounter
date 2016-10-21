
public class StepCounter {

	public StepCounter(String filepath, String[] columnNames, int linesToIgnore) {
		CSVData data = new CSVData(filepath, columnNames, 1);
		String[] columnHeaders = {"accel x", "accel Y", "accel Z"};
		double[][] accelData = data.getMultipleColumns(columnHeaders);
	}

	private static int countSteps(double[] times, double[][] sensorData) {
		
	}

	private static double calculateMagnitude(double x, double y, double z) {
		return Math.sqrt(x * x + y * y + z * z);
	}

	private static double[] calculateMagnitudesFor(double[][] sensorData) {
		double[] magnitudes = new double[sensorData[0].length];
		for (int i = 0; i < sensorData[0].length; i++){
			magnitudes[i] = calculateMagnitude(sensorData[i][0], sensorData[i][1], sensorData[i][2]);
		}
		return magnitudes;
	}

	private static double calculateStandardDeviation(double[] arr, double mean) {
		double standardDeviation = 0;
		for (int i = 0; i < arr.length; i ++){
			standardDeviation += (arr[i] - mean)*(arr[i] - mean);
		}
		standardDeviation = standardDeviation/(arr.length-1);
		
		return Math.sqrt(standardDeviation);
	}

	private static double calculateMean(double[] arr) {
		int mean = 0;
		for (int i = 0; i < arr.length; i++) {
			mean += arr[i];
		}

		return mean / arr.length;
	}
}
