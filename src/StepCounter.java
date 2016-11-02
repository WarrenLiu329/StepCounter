
public class StepCounter {

	public static int countSteps(double[][] sensorData) {
		int steps = 0;
		double[] magnitudes = calculateMagnitudesFor(sensorData);
		double mean = calculateMean(magnitudes);
		double standardDeviation = calculateStandardDeviation(magnitudes, mean);
		for (int i = 1; i < magnitudes.length - 1; i++) {
			if (isPeak(magnitudes[i], magnitudes[i-1], magnitudes[i+1])) {
				if (Math.abs(magnitudes[i] - mean) > standardDeviation) steps++;
			}
		}
		return steps;
	}

	private static double calculateMagnitude(double x, double y, double z) {
		return Math.sqrt((x*x) + (y*y) + (z*z));
	}

	private static double[] calculateMagnitudesFor(double[][] sensorData) {
		double[] magnitudes = new double[sensorData.length];
		for (int i = 0; i < sensorData.length; i++) {
			magnitudes[i] = calculateMagnitude(sensorData[i][0], sensorData[i][1], sensorData[i][2]);
		}
		return magnitudes;
	}

	private static double calculateStandardDeviation(double[] arr, double mean) {
		double standardDeviation = 0;
		for (double i : arr) {
			standardDeviation += ((i - mean) * (i - mean));
		}
		standardDeviation /= (arr.length - 1);

		return Math.sqrt(standardDeviation);
	}

	private static double calculateMean(double[] arr) {
		double sum = 0;
		for (double i : arr) {
			sum += i;
		}
		double mean = sum/arr.length;
		return mean;
	}

	/****
	 * checks if a value is greater than its values next to it
	 * 
	 * @param index
	 * @param arr
	 * @param magnitudes
	 * @return whether an index is considered a peak
	 */
	private static boolean isPeak(double possiblePeak, double valueBefore, double valueAfter) {
		if(possiblePeak > valueBefore && possiblePeak > valueAfter)return true;
		return false;
	}
	
	
}
