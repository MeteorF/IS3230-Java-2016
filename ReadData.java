import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class ReadData {
	
	final int dataSize = 10000;
	public Date tempDate, startDate, endDate;
	public Date[] date = new Date[dataSize];
	public Double[] open = new Double[dataSize];
	public Double[] high = new Double[dataSize];
	public Double[] low = new Double[dataSize];
	public Double[] close = new Double[dataSize];
	public Double[] body = new Double[dataSize];
	public Double[] upper = new Double[dataSize];
	public Double[] lower = new Double[dataSize];
	public int elementCount = 0;
	
	//Constructor
	//public ReadCSV(Object config, Boolean debugMode, String startDateStr, String endDateStr) throws Exception {
	public ReadData(ReadConfig config, String startDateStr, String endDateStr) throws Exception {
        
        //Handle Date format
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("HKT"));
		
		startDate = sdf.parse(startDateStr);
		endDate = sdf.parse(endDateStr);
		
		try{
            //Read CSV
			File file = new File(config.getPath());
			Scanner inputFile = new Scanner(file);
			
			inputFile.next(); //ignore the first line;
			inputFile.next(); //ignore the second line;
			while (inputFile.hasNext()){
				
				String data_row = inputFile.next();
				String[] data = data_row.split(",");
				
				try{
					tempDate = sdf.parse(data[0]);
				}
				//If there is any unexpected error, then print error msg and quit the program
				catch(Exception e){
					System.out.println(e);
					System.exit(0); //terminate the program
				}
				
				//If the data is within the date range
				if ( ((tempDate.after(startDate)) && (tempDate.before(endDate))) || 
					 (endDate.compareTo(tempDate) == 0) || 
					 (startDate.compareTo(tempDate) == 0)
					){
					
					try{
						date[elementCount] = tempDate;
						open[elementCount] = Double.parseDouble(data[1]);
						high[elementCount] = Double.parseDouble(data[2]);
						low[elementCount] = Double.parseDouble(data[3]);
						close[elementCount] = Double.parseDouble(data[4]);
					}
					catch(Exception e){
						System.out.println (e);
						System.exit(0); //terminate the program
					}
					
					//check whether the data is up market & calculate body, upper and lower
					if (close[elementCount] > open[elementCount]) {
						body[elementCount] = close[elementCount] - open[elementCount];
						lower[elementCount] = open[elementCount] - low[elementCount];
						upper[elementCount] = high[elementCount] - close[elementCount];
					}else{
						body[elementCount] = open[elementCount] - close[elementCount];
						lower[elementCount] = close[elementCount] - low[elementCount];
						upper[elementCount] = high[elementCount] - open[elementCount];
					}
					elementCount++;
				}
			}
			inputFile.close();
		}
		catch (FileNotFoundException e){
			System.out.println (config.getPath() + " not found, ");
			System.out.println ("please make sure the raw data is in folder 'data/'");
			System.exit(0); //terminate the program
		}
	}
}
