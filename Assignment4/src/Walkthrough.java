import java.util.*;
import java.sql.*;


public class Walkthrough {
	

	
	public static void main (String args[]){
		Scanner dick = new Scanner (System.in);
		int attrNumber = 0;
		ArrayList TBNameArr = new ArrayList();
		ArrayList<Integer> TBSizeArr = new ArrayList<Integer>();
		int[] attrCountArr;
		
		while(true){
			
			System.out.printf("Enter a command: \n");
			String command = dick.nextLine().toUpperCase();
			//System.out.printf("Are you sure you would like to run %s? \n", command);
			
//EXIT
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//	
			
			if(command.equals("EXIT"))
			{
				System.out.println("System Exiting...\nDone.");
				System.exit(0);
			}
			
//HELP
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//	
			
			else if(command.equals("HELP")){
				System.out.println("The options for commands are: \nCREATE\nINSERT\nSELECT\nEXIT");
			}
		
//EXISTS
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//		
			
			else if(command.equals("EXISTS")){
				
				System.out.println("Table Name: ");
				String tableName = dick.nextLine();
				
				if (Trans.exist(tableName) == true){
					System.out.printf("%s does not exist", tableName);
				}
				else{
					System.out.print("Table Does not exist!");
				}
				System.out.println("Inserting record...");
			}
//CREATE
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//	
			
			else if(command.equals("CREATE")){
				
			String tableName = "";
			String atts = "";
			
			//Takes in user input to create name
			System.out.println("Table Name: ");
			tableName = dick.nextLine();
			System.out.println("Attributes: ");
			atts = dick.nextLine();
			
				//split attributes
				String[] result = atts.split(",");
				for(int x = 1; x < result.length; x++){
					attrNumber = x + 1;
				}
					
				//Store table info to validate select and insert functions
				TBNameArr.add(tableName);
				TBSizeArr.add(attrNumber);
				
				//Creates the table and outputs success
				Trans.create(tableName, atts);
				System.out.println("Creating table...");
				System.out.println(TBNameArr + "\n" + TBSizeArr);
			}
//INSERT		
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//	
			
			else if(command.equals("INSERT")){
				
				String tableName = "";
				int tableNamePos = 0;
				
				//Takes in user input to create name
				System.out.println("Table Name: ");
				tableName = dick.nextLine();
				
	
//				for(int i = 0; i < TBNameArr.size(); i++){
//					if (TBNameArr.get(i) == tableName){
//						tableNamePos = (int) TBSizeArr.get(i);
//					}
//				}
				
				Iterator i = TBNameArr.iterator();
				while(i.hasNext()){
					if(i.equals(tableName)){
						tableNamePos = TBNameArr.indexOf(i);
						break;
					}
					else{
						break;
					}
				}
				
				int attrLimit = TBSizeArr.get(tableNamePos);
				System.out.println("\n$$" + attrLimit + "$$\n");
				
				System.out.println("Attributes: ");
				String[] atts = new String[attrLimit];
				for(int j = 0; j < atts.length; j++){
					atts[j] = dick.nextLine();
				}
				
				//add a for loop using attribute count to continually ask the user for inputs for all fields.
					Trans.write(tableName, atts);
					System.out.println("Inserting record...");
				
				if(atts.length > attrLimit){
					System.out.printf("Attribute count mismatch: there are %d values in %s\n", tableNamePos, tableName);
				}
				
			}
//SELECT			
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//			
			else if(command.equals("SELECT")){
				String tableName;
				String attrPick;
				String condition;
				String preparedCondition;
				String[] output;
				
				System.out.println("Table Name: ");
				tableName = dick.nextLine();
				
				System.out.println("Attribute: ");
				attrPick = dick.nextLine();
				
				System.out.println("Condition: ");
				condition = dick.nextLine();
				
				preparedCondition = "where " + condition;
				
				output = Trans.read(tableName, attrPick, preparedCondition);
				System.out.println("Searching for record...");
				for(int p = 0; p < output.length; p++){
					System.out.print(output[p]);
				}
			}
//VALIDATIONS
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//				
			else{
				System.out.printf("%s is not a valid command. Enter HELP for command options\n", command);
			}
		}
	}
}