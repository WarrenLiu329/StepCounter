import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/***
 * 
 * @author the boo king
 *
 */

public class CSVData {
	private double[][] data;
	private String[] columnNames;
	private String filePathToCSV;
	private int numRows, numColumns;

	public CSVData(String filepath, String[] columnNames, int startRow) {
		this.filePathToCSV = filepath;

		String dataString = readFileAsString(filepath);
		String[] lines = dataString.split("\n");

		// number of data points
		int n = lines.length - startRow;
		this.numRows = n;
		this.numColumns = columnNames.length;

		// create storage for column names
		this.columnNames = columnNames;

		// create storage for data
		this.data = new double[n][numColumns];
		for (int i = 0; i < lines.length - startRow; i++) {
			String line = lines[startRow + i];
			String[] coords = line.split(",");
			for (int j = 0; j < numColumns; j++) {
				if (coords[j].endsWith("#"))
					coords[j] = coords[j].substring(0, coords[j].length() - 1);
				double val = Double.parseDouble(coords[j]);
				data[i][j] = val;
			}
		}
	}

	/***
	 * Returns a CSVData object for a file ignoring lines as the top. First row
	 * are column names, all other data is stored as doubles.
	 * 
	 * @param filename
	 *            the file to read
	 * @param numLinesToIgnore
	 *            the number of lines at the top to ignore
	 * @return a CSVData object for that file
	 */
	public static CSVData readCSVFile(String filename, String[] columnNames, int numLinesToIgnore) {
		return new CSVData(filename, columnNames, numLinesToIgnore);
	}
	
	public static CSVData readCSVFile(String filename, int numLinesToIgnore) {
		// write your own overloaded cnstructor, which uses the fist line of the file as the column names
		return null;
	}

	public double[] getRow(int index) {
		double[] row = data[index];
		return row;
	}

	public double[] getColumn(int index) {
		double[] column = new double[data.length];
		for(int i = 0; i < data.length; i++){
			column[i] = data[i][index];
		}
		return column;
	}
	
	public double[][] getColumns(int[] indexes){
		double[][] output = new double[indexes.length][data.length];
		int nextFreePosition = 0;
		for (int i = 0; i < data[0].length; i++){
			if(indexes[nextFreePosition] == i){
				output[nextFreePosition] = getColumn(indexes[nextFreePosition]);
				nextFreePosition++;
			}
		}
		return output;
	}

	public double[] getColumn(String columnName) {
		int index = 0;
		for(int i = 0; i < columnNames.length; i++){
			if(columnNames[i].equals(columnName))index = i;
		}
		return getColumn(index);
	}

	public double[][] getData(int firstRow, int lastRow, int firstColumn, int lastColumn) {
		double[][] excerpt = new double[lastRow-firstRow][lastColumn-firstColumn];
		for(int r = 0; r < excerpt.length; r++){
			for (int c = 0; c < excerpt[0].length; c++){
				excerpt[r][c] = data[r+firstRow][c+firstColumn];
			}
		}
		return excerpt;
	}

	public void setData(double newData, int row, int column) {
		data[row][column] = newData;
	}

	public void setRow(double[] newData, int row) {
		data[row] = newData;
	}

	public void setColumn(double[] newData, int column) {
		for(int i = 0; i < newData.length; i++){
			data[i][column] = newData[i];
		}
	}

	public String[] getColumnNames() {
		return columnNames;
	}
	
	public static double[] getPartOfArray(double[] data, int start, int end){
		int nextFreeIndex = 0;
		double[] arr = new double[end-start];
		for(int i = start; i < end+1; i++){
			arr[nextFreeIndex] = data[i];
		}
		return arr;
	}

	private String readFileAsString(String filepath) {
		StringBuilder output = new StringBuilder();
		try (Scanner scanner = new Scanner(new File(filepath))) {
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				output.append(line + System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output.toString();
	}
}
