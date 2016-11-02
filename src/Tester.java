
public class Tester {
	
	public static void main(String[] args){
		String filepath = "C:\\Users\\tkaufmangomez866\\StepCounter\\data\\walkingSampleData-out.csv";
		String columnHeaders = "time (ms),  accel x,  accel y,  accel z,  gryo x,  gyro y,  gyro z,  linear accel x,  linear accel y,  linear accel z,  orientation x,  orientation y,  orientation z";
		String[] names = columnHeaders.split(", ");
		
		
		CSVData sample = new CSVData(filepath, names, 1);
		double[][] data = sample.getData(1, 319, 1, 4);
		int steps = StepCounter.countSteps(data);
		
		System.out.println(steps);
	}

}
