import java.util.Date;

public class Hammer {
	final int dataSize = 10000;
	private double open, close, body, lower, upper;
	public Date[] record = new Date[dataSize];
	public int recordCount = 0;
	
	public Hammer (ReadData data, Boolean debugMode){
		for (int i = data.elementCount-1; i >= 0; i-- ){
			open = data.open[i];
			close = data.close[i];
			body = data.body[i];
			lower = data.lower[i];
			upper = data.upper[i];
			
			//Condition 1 : check Up market (Close > Open)
			if (close > open) {
				
				//Condition 2 : check shape (Long lower & short upper)
				if ( (lower > (body*2)) && (lower > (upper*2)) ){
					//Store it in array
					record[recordCount] = data.date[i];
					recordCount++;
				}
			}
		}
	}
}
