package expenseTrackerCLI;

import java.util.Scanner;

import controllers.ExpenseTrackerController;
import entities.Budget;

public class ExpenseTrackerCLI {
	
	private static ExpenseTrackerController etc = new ExpenseTrackerController();
	private static Budget budget = new Budget();
	
	public static void main (String args []) {
		Scanner scanner = new Scanner(System.in); 
	    System.out.println("Enter your order (or type quit)");

	    while (scanner.hasNextLine()){
		    String inputString = scanner.nextLine();  
		    if (inputString.equals("quit")) {
		    	break;
		    }
		    String[] arguments = inputString.split(" ");
		    if (arguments.length > 0) {
		    	switch (arguments[0]) {
		    		case "add":
		    			if (arguments.length > 4) {
		    				String description = "";
			    			String amount = "0";
			    			String category = "";
			    			for (int i = 1; i < arguments.length - 1; i++) {
			    				if (arguments[i].equals("--description")) {
			    					description = arguments [i+1];
			    				}
			    				if (arguments[i].equals("--amount")) {
			    					amount = arguments [i+1];
			    				}
			    				if (arguments[i].equals("--category")) {
			    					category = arguments [i+1];
			    				}
			    			}
			    			etc.addExpense(description, Double.valueOf(amount), category, budget);
		    			}
		    			break;
		    		case "delete":
		    			if (arguments.length > 2) {
		    				int id = 0;
		    				for (int i = 1; i < arguments.length - 1; i++) {
			    				if (arguments[i].equals("--id")) {
			    					id = Integer.valueOf(arguments [i+1]);
			    				}
			    			}
			    			etc.deleteExpense(id);
		    			}
		    			break;
		    		case "update":
		    			if (arguments.length > 6) {
		    				int id = 0;
		    				String description = "";
		    				double amount = 0;
		    				String category = "";
		    				for (int i = 1; i < arguments.length - 1; i++) {
			    				if (arguments[i].equals("--id")) {
			    					id = Integer.valueOf(arguments [i+1]);
			    				}
			    				if (arguments[i].equals("--description")) {
			    					description = arguments [i+1];
			    				}
			    				if (arguments[i].equals("--amount")) {
			    					amount = Double.valueOf(arguments [i+1]);
			    				}
			    				if (arguments[i].equals("--category")) {
			    					category = arguments [i+1];
			    				}
			    			}
			    			etc.updateExpense(id, description, amount, category, budget);
		    			}
		    			break;
		    		case "list":
		    			etc.list(budget);
		    			break;
		    		case "summary":
		    			if (arguments.length > 1) {
		    				int month = 0;
		    				String category = "";
		    				for (int i = 1; i < arguments.length - 1; i++) {
		    					if (arguments[i].equals("--month")) {
			    					month = Integer.valueOf(arguments [i+1]);
			    				}
		    					if (arguments[i].equals("--category")) {
		    						category = arguments [i+1];
			    				}
		    				}
		    				etc.summaryByMonthAndByCategory(month, category);
		    			} else {
		    				etc.summary();
		    			}
		    			break;
		    		case "budget":
		    			if (arguments.length > 1) {
		    				int month = 0;
		    				double newLimit = 0;
		    				for (int i = 1; i < arguments.length - 1; i++) {
		    					if (arguments[i].equals("--limit")) {
		    						newLimit = Double.valueOf(arguments [i+1]);
			    				}
		    					if (arguments[i].equals("--month")) {
		    						month = Integer.valueOf(arguments [i+1]);
			    				}
		    				}
		    				etc.setBudget(month, newLimit, budget);
		    			}
		    			break;
		    		default:
		    			System.out.println("ERROR. Invalid command.");
		    			break;		    				
			    }
		    etc.saveExpenses();
		    }
	    }
	}
}
