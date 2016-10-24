
public class StepCounter {

	public StepCounter(String filepath, String[] columnNames, int linesToIgnore) {
		CSVData data = new CSVData(filepath, columnNames, 1);
		String[] columnHeaders = { "accel x", "accel Y", "accel Z" };
		double[][] accelData = data.getMultipleColumns(columnHeaders);
	}

	private static int countSteps(double[] times, double[][] sensorData) {
		int steps = 0;
		double[] magnitudes = calculateMagnitudesFor(sensorData);
		double mean = calculateMean(magnitudes);
		double standardDeviation = calculateStandardDeviation(magnitudes, mean);
		for (int i = 1; i < magnitudes.length - 1; i++) {
			if (isPeak(magnitudes, i)) {
				if (Math.abs(magnitudes[i] - mean) < standardDeviation
						&& Math.abs(magnitudes[i] - mean) > -standardDeviation) {
					steps++;
				}
			}
		}
		return steps;

	}

	private static double calculateMagnitude(double x, double y, double z) {
		return Math.sqrt(x * x + y * y + z * z);
	}

	private static double[] calculateMagnitudesFor(double[][] sensorData) {
		double[] magnitudes = new double[sensorData[0].length];
		for (int i = 0; i < sensorData[0].length; i++) {
			magnitudes[i] = calculateMagnitude(sensorData[i][0], sensorData[i][1], sensorData[i][2]);
		}
		return magnitudes;
	}

	private static double calculateStandardDeviation(double[] arr, double mean) {
		double standardDeviation = 0;
		for (int i = 0; i < arr.length; i++) {
			standardDeviation += (arr[i] - mean) * (arr[i] - mean);
		}
		standardDeviation = standardDeviation / (arr.length - 1);

		return Math.sqrt(standardDeviation);
	}

	private static double calculateMean(double[] arr) {
		int mean = 0;
		for (int i = 0; i < arr.length; i++) {
			mean += arr[i];
		}

		return (double) mean / arr.length;
	}

	/****
	 * checks if a value is greater than its values next to it
	 * 
	 * @param index
	 * @param arr
	 * @param magnitudes
	 * @return whether an index is considered a peak
	 */
	private static boolean isPeak(double[] arr, int index) {
		if (arr[index] > arr[index - 1] && arr[index] > arr[index + 1]) {
			return true;
		}
		return false;
	}
}
