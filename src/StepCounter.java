
public class StepCounter {

	public StepCounter(double[] times, double[][] sensorData) {

	}

	private static int countSteps(double[] times, double[][] sensorData) {

	}

	private static double calculateMagnitude(double x, double y, double z) {
		return Math.sqrt(x * x + y * y + z * z);
	}

	private static double[] calculateMagnitudesFor(double[][] sensorData) {

	}

	private static double calculateStandardDeviation(double[] arr, double mean) {

	}

	private static double calculateMean(double[] arr) {
		int mean = 0;
		for (int i = 0; i < arr.length; i++) {
			mean += arr[i];
		}

		return mean / arr.length;
	}
}
