import java.text.SimpleDateFormat;
import java.util.*;

public class UserInterface {
	private boolean valid = false;
	public int optionChosen = -1;
	
	//Empty Constructor
	public UserInterface(){
	}
	
	public void PrintRecord(Date[] record, int count){
		if (count > 0){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			for (int i = 0; i < count; i++){
				System.out.println("Pattern Found: " + sdf.format(record[i]));
			}
		}else{
			System.out.println("Pattern not found.");
		}
	}
	
	public void OptionValidation(int optionChosen, int minVal, int maxVal, boolean valid){
		if ( (optionChosen <= maxVal) && (optionChosen >= minVal) ) {
			//If optionChosen is in the range
			this.valid = true;
		}else{
			//If optionChosen is not in the range
			this.valid = false;
			System.out.println("Please input an option between 1 to 3.");
			System.out.println();
		}
	}
	
	public void OptionRequest() throws Exception {
		do {
			try{
				Scanner keyboard = new Scanner (System.in);
				System.out.println("Select the analysis you need.");
				System.out.println("1. Hammer");
				System.out.println("2. Three White Soldiers");
				System.out.println("3. Bullish Kicker");
				System.out.print("Pattern: ");
				this.optionChosen = keyboard.nextInt();
				OptionValidation(optionChosen, 1, 3, valid);
			}
			catch (Exception e){
				System.out.println("Invalid input, please try again.");
				System.out.println();
				valid = false;
			}
		}
		while (!valid);
	}
}

