import java.util.Date;

public class ThreeWhiteSoldiers {
	final int dataSize = 10000;
	
	public Date[] record = new Date[dataSize];
	public int recordCount = 0;
	
	public Date[] recordCon2 = new Date[dataSize];
	public int recordCon2Count = 0;
	
	public ThreeWhiteSoldiers (ReadData data, Boolean debugMode){
		if (debugMode){
			System.out.println("This is ThreeWhiteSoldiers checking.");
		}
		
		//Raw data are in reverse chronological order
		/*
						[0] 2014-11-01
						[1] 2014-10-31
						[2] 2014-10-30
		Start here->	[3] 2014-10-29
		//*/
		for (int i = data.elementCount-1; i >= 2; i-- ){
			
			//Condition 1 : check Up market (Close > Open)
			// 3 days are in up market & Each day's close > previous day's
			if ( (data.close[i] > data.open[i]) &&
					 (data.close[i-1] > data.open[i-1]) &&
					 (data.close[i-2] > data.open[i-2]) )
				{
				
				//Condition 2 : check shape (Litter lower & litter upper = Long Body)
				// 3 days are in the similar shape
				if ( ((data.body[i] > (data.upper[i]*1.5)) && (data.body[i] > (data.lower[i]*1.5))) &&
					 ((data.body[i-1] > (data.upper[i-1]*1.5)) && (data.body[i-1] > (data.lower[i-1]*1.5))) &&
					 ((data.body[i-2] > (data.upper[i-2]*1.5)) && (data.body[i-2] > (data.lower[i-2]*1.5))) )
				{

					//////////////////////////////////////////////////////////////////rEMEMBER TO DELETE					
					if (debugMode){
						System.out.println("Below records fulfil condition 2 and proceeding to condition 3.");
						System.out.println(
							"data " + 
							"Date" + " | " + 
							"Open" + " | " +
							"High" + " | " +
							"Low" + " | " +
							"Close"
						);
						System.out.println(
							"data[i]: " + 
							data.date[i] + " | " + 
							data.open[i] + " | " +
							data.high[i] + " | " +
							data.low[i] + " | " +
							data.close[i]
						);
						System.out.println(
							"data[i-1]: " + 
							data.date[i-1] + " | " + 
							data.open[i-1] + " | " +
							data.high[i-1] + " | " +
							data.low[i-1] + " | " +
							data.close[i-1]
						);
						System.out.println(
							"data[i-2]: " + 
							data.date[i-2] + " | " + 
							data.open[i-2] + " | " +
							data.high[i-2] + " | " +
							data.low[i-2] + " | " +
							data.close[i-2] 
						);
						System.out.println();
					}
					//////////////////////////////////////////////////////////////////rEMEMBER TO DELETE
					
					//Condition 3 : each Open within previous Body 
					//(n th's open > n-1 th's open) & (n th's open < n-1 th's close)
					if ( ((data.open[i-2] > data.open[i-1]) && (data.open[i-2] < data.close[i-1])) &&
						 ((data.open[i-1] > data.open[i]) && (data.open[i-1] < data.close[i])) )
						{
						//Record the latest date
						record[recordCount] = data.date[i-2];
						recordCount++;
					}
					
				}
			}
		}
	}
}
