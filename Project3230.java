import java.io.IOException;

/*	This is the starting point of the whole program,
 	All classes will be called here.
 */

public class Project3230 {
	public static String startDateStr;
	public static String endDateStr;
	public static String optionVal;
	
	public static void main(String[] args) throws Exception {
		//Parameter passed from the cmd "java Project3230 [startDate YYYY-MM-DD] [endDate YYYY-MM-DD]"
		startDateStr = args[0];
		endDateStr = args[1];
		
		//Step1. Read System config data
		//(config.txt Contains 1.debugMode, 2.raw data folder name, 3.raw data file name)
		ReadConfig configData = new ReadConfig();
		if (configData.debugMode) {
			System.out.println("DebugMode: " + configData.debugMode);
			System.out.println("RawDataFolder: " + configData.rawDataFolder);
			System.out.println("RawDataFile: " + configData.rawDataFile);
			System.out.println("FullPath: " + configData.getPath());
			System.out.println("Date Range from: " + startDateStr + " to " + endDateStr);
			System.out.println();
		}

		//Step2. Read CSV data and extract all data to internal memory
		ReadData data = new ReadData(configData, startDateStr, endDateStr);
		
		//Step3. Display UserInterface to get the choice from user
		UserInterface ui = new UserInterface();
		//Display options for user to choose, selected option is stored at ui.optionChosen
		ui.OptionRequest();
		
		//Step4. Execute corresponding analysis (using ui.optionChosen)
		if (ui.optionChosen == 1){
			Hammer findHammer = new Hammer(data, configData.debugMode);
			ui.PrintRecord(findHammer.record, findHammer.recordCount);
		}
		
		if (ui.optionChosen == 2){
			ThreeWhiteSoldiers findTWS = new ThreeWhiteSoldiers(data, configData.debugMode);
			ui.PrintRecord(findTWS.record, findTWS.recordCount);
		}
		
		if (ui.optionChosen == 3){
			BullishKicker findBK = new BullishKicker(data, configData.debugMode);
			ui.PrintRecord(findBK.record, findBK.recordCount);
		}
	}
}
