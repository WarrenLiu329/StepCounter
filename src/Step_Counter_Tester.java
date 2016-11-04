
public class Step_Counter_Tester {
	public static double[][] sampleData;
	public static void main(String[] args) {
		String filepath ="/Volumes/WARREN_LIU/Java/NoiseSmoothing/data/walkingSampleData-out.csv";
		String[] headers = {"time(ms)", "accelX", "accelY", "accelZ"};
		CSVData data = new CSVData(filepath, headers, 1);
		sampleData = data.getData();
		
		System.out.print();
	}

}
