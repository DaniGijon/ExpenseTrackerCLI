package controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entities.Expense;

public class ExpenseTrackerController {
	
	private static final Path FILE_PATH = Path.of("expensesJSON.json");
	private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	
	private int lastId = 0;
	private List<Expense> listExpenses = new ArrayList<>();
	
	public ExpenseTrackerController () {
		listExpenses = loadExpenses();
		lastId = listExpenses.size();
	}
	
	private List <Expense> loadExpenses () {
		List<Expense> storedExpenses = new ArrayList<>();
		
		if (Files.exists(FILE_PATH)){
			try {
	            String jsonContent = Files.readString(FILE_PATH);
	            String[] expenseList = jsonContent.replace("[", "")
	                                            .replace("]", "")
	                                            .split("},");
	            for (String expenseJson : expenseList){
	                if (!expenseJson.endsWith("}")){
	                	expenseJson = expenseJson + "}";
	                    storedExpenses.add(expenseFromJSON(expenseJson));
	                } else {
	                	storedExpenses.add(expenseFromJSON(expenseJson));
	                }
	            }
	        } catch (IOException e){
	            e.printStackTrace();
	        }
        }
		
		return storedExpenses;
	}
	
	public void addExpense (String description, double amount) {
		Expense expense = new Expense (++lastId, description, amount);
		listExpenses.add(expense);
		System.out.println("New expense added (ID:" + expense.getId() + ")");
	}
	
	public void list () {
		for (Expense expense : listExpenses) {
			System.out.println("(ID:" + expense.getId() + ") - " + expense.getDescription() + " costs " +
					expense.getAmount() + " €. Created at " + expense.getCreatedAt() + 
					" . Modified at " + expense.getModifiedAt());
		}
	}
	
	public void summary () {
		double amountcount = 0;
		for (Expense expense : listExpenses) {
			amountcount += expense.getAmount();
		}
		System.out.println("Total expenses: " + amountcount + "€");
	}
	
	public void deleteExpense (int id) {
		int i = 0;
		for (Expense expense : listExpenses) {
			if (expense.getId() == id) {
				listExpenses.remove(i);
				System.out.println("Expense deleted successfully (ID:" + expense.getId() + ")");
				break;
			}
			i++;
		}
	}
	
	public void updateExpense (int id, String description, double amount) {
		for (Expense expense : listExpenses) {
			if (expense.getId() == id) {
				expense.setDescription(description);
				expense.setAmount(amount);
				expense.setModifiedAt(LocalDateTime.now());
				System.out.println("Expense updated successfully (ID:" + expense.getId() + ")");
				break;
			}
		}
	}
	
	public void summaryByMonth(int month) {
		if (month >= 1 && month <= 12) {
			double monthAccount = 0;
			String monthDescription = getMonthDescription(month);
			for (Expense expense : listExpenses) {
				if (String.valueOf(expense.getCreatedAt().getMonth()).equals(monthDescription)) {
					monthAccount += expense.getAmount();
				}
			}
			System.out.println("Total expenses for " + monthDescription + ": " + monthAccount + "€");
			
		}
		else {
			System.out.println("ERROR: Month " + month + " does not exist.");
		}
	}
	
	private String getMonthDescription (int month) {
		switch (month) {
			case 1: 
				return "JANUARY";
			case 2: 
				return "FEBRUARY";
			case 3: 
				return "MARCH";
			case 4: 
				return "APRIL";
			case 5: 
				return "MAY";
			case 6: 
				return "JUNE";
			case 7: 
				return "JULY";
			case 8: 
				return "AUGUST";
			case 9: 
				return "SEPTEMBER";
			case 10: 
				return "OCTOBER";
			case 11: 
				return "NOVEMBER";
			case 12: 
				return "DECEMBER";
			default:
				return "MONTH NOT EXIST";
		}
	}
	
	private Expense expenseFromJSON (String expenseJSON) {
		expenseJSON = expenseJSON.replace("{", "").replace("}", "").replace("\"", "");
        String[] json1 = expenseJSON.split(",");

        String id = json1[0].split(":")[1].strip();
        String description = json1[1].split(":")[1].strip();
        String amountStr = json1[2].split(":")[1].strip();
        String createdAtStr = json1[3].split("[a-z]:")[1].strip();
        String modifiedAtStr = json1[4].split("[a-z]:")[1].strip();

        Expense expense = new Expense(Integer.valueOf(id), description, Double.valueOf(amountStr));
  
        expense.setCreatedAt(LocalDateTime.parse(createdAtStr, formatter));
        expense.setModifiedAt(LocalDateTime.parse(modifiedAtStr, formatter));

        return expense;
	    
	}
}
