package controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entities.Budget;
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
	
	public void saveExpenses () {
		if (listExpenses.size() > 0) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("[\n");
	        for (int i = 0; i < listExpenses.size(); i++){
	            sb.append(expenseToJSON(listExpenses.get(i)));
	            if (i < listExpenses.size() - 1){
	                sb.append(",\n");
	            }
	        }
	        sb.append("\n]");

	        String jsonContent = sb.toString();
	        try {
	            Files.writeString(FILE_PATH, jsonContent);
	        } catch (IOException e){
	            e.printStackTrace();
	        }
		    
		}
	}
	
	public void addExpense (String description, double amount, String category, Budget budget) {
		double alreadySpent = getAlreadySpent(LocalDateTime.now().getMonthValue());
		Expense expense = new Expense (++lastId, description, amount, category);
		listExpenses.add(expense);
		System.out.println("New expense added (ID:" + expense.getId() + ")");
		checkMonthBudgetExceed(amount, alreadySpent, budget, 0, LocalDateTime.now().getMonthValue());
	}
	
	public void list (Budget budget) {
		for (Expense expense : listExpenses) {
			System.out.println("(ID:" + expense.getId() + ") - " + expense.getDescription() + " [" + expense.getCategory() + "] costs " +
					expense.getAmount() + " €. Created at " + expense.getCreatedAt() + 
					" . Modified at " + expense.getModifiedAt() + ".");
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
	
	public void updateExpense (int id, String description, double amount, String category, Budget budget) {
		
		for (Expense expense : listExpenses) {
			if (expense.getId() == id) {
				double alreadySpent = getAlreadySpent(expense.getCreatedAt().getMonthValue());
				checkMonthBudgetExceed(amount, alreadySpent, budget, expense.getAmount(), expense.getCreatedAt().getMonthValue());

				expense.setDescription(description);
				expense.setAmount(amount);
				expense.setModifiedAt(LocalDateTime.now());
				expense.setCategory(category);
				System.out.println("Expense updated successfully (ID:" + expense.getId() + ")");
				
				break;
			}
		}	
	}
	
	public void summaryByMonthAndByCategory(int month, String category) {
		if (month > 0 && month <= 12 && category.trim().equals("")) {
			double monthAccount = 0;
			String monthDescription = getMonthDescription(month);
			for (Expense expense : listExpenses) {
				if (String.valueOf(expense.getCreatedAt().getMonth()).equals(monthDescription)) {
					monthAccount += expense.getAmount();
				}
			}
			System.out.println("Total expenses for " + monthDescription + ": " + monthAccount + "€");
			
		} else if (month == 0 && !category.trim().equals("")) {
			double categoryAccount = 0;
			for (Expense expense : listExpenses) {
				if (expense.getCategory().equals(category)) {
					categoryAccount += expense.getAmount();
				}
			}
			System.out.println("Total expenses for [" + category + "]: " + categoryAccount + "€");
		} else if (month > 0 && month <= 12 && !category.trim().equals("")) {
			double totalAccount = 0;
			String monthDescription = getMonthDescription(month);
			for (Expense expense : listExpenses) {
				if (expense.getCategory().equals(category) &&
						String.valueOf(expense.getCreatedAt().getMonth()).equals(monthDescription)) {
					totalAccount += expense.getAmount();
				}
			}
			System.out.println("Total expenses for [" + category + "] in the month of " + monthDescription + ": " + totalAccount + "€");
		}
		else {
			System.out.println("ERROR: Month " + month + " does not exist.");
		}
	}
	
	public void setBudget (int month, double newLimit, Budget budget) {
		if (month > 0 && month <= 12) {
			switch (month) {
				case 1: 
					budget.setLimitJanuary(newLimit);
					break;
				case 2: 
					budget.setLimitFebruary(newLimit);
					break;
				case 3: 
					budget.setLimitMarch(newLimit);
					break;
				case 4: 
					budget.setLimitApril(newLimit);
					break;
				case 5: 
					budget.setLimitMay(newLimit);
					break;
				case 6: 
					budget.setLimitJune(newLimit);
					break;
				case 7: 
					budget.setLimitJuly(newLimit);
					break;
				case 8: 
					budget.setLimitAugust(newLimit);
					break;
				case 9: 
					budget.setLimitSeptember(newLimit);
					break;
				case 10: 
					budget.setLimitOctober(newLimit);
					break;
				case 11: 
					budget.setLimitNovember(newLimit);
					break;
				case 12: 
					budget.setLimitDecember(newLimit);
					break;
			}
			System.out.println ("Budget limit for " + getMonthDescription(month) + " updated: " + newLimit);
		} else {
			System.out.println("ERROR. Month must be a value between 1 to 12.");
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
        String category = json1[5].split(":")[1].strip();

        Expense expense = new Expense(Integer.valueOf(id), description, Double.valueOf(amountStr), category);
  
        expense.setCreatedAt(LocalDateTime.parse(createdAtStr, formatter));
        expense.setModifiedAt(LocalDateTime.parse(modifiedAtStr, formatter));

        return expense;
	    
	}
	
	public String expenseToJSON(Expense expense) {
        return "{\"id\":\"" + expense.getId() + "\", \"description\":\"" + expense.getDescription().strip() + "\", \"amount\":\"" + String.valueOf(expense.getAmount()) +
                "\", \"createdAt\":\"" + expense.getCreatedAt().format(formatter) + "\", \"modifiedAt\":\"" + expense.getModifiedAt().format(formatter) + "\", \"category\":\"" + 
        		expense.getCategory().strip() + "\"}";
    }
	
	private double getAlreadySpent (int month) {
		double alreadySpent = 0;
		for (Expense expense : listExpenses) {
			if (expense.getCreatedAt().getMonthValue() == month) {
				alreadySpent += expense.getAmount();
			}
		}	
		return alreadySpent;
	}
	
	private void checkMonthBudgetExceed (double amount, double alreadySpent, Budget budget, double expensePreviousAmount, int month) {
		switch (month) {
			case 1: 
				if (amount + alreadySpent - expensePreviousAmount > budget.getLimitJanuary()) {
					System.out.println("WARNING. You are exceeding the January budget [" + budget.getLimitJanuary() + "].");
				}
				break;
			case 2: 
				if (amount + alreadySpent - expensePreviousAmount > budget.getLimitFebruary()) {
					System.out.println("WARNING. You are exceeding the February budget [" + budget.getLimitFebruary() + "].");
				}
				break;
			case 3: 
				if (amount + alreadySpent - expensePreviousAmount > budget.getLimitMarch()) {
					System.out.println("WARNING. You are exceeding the March budget [" + budget.getLimitMarch() + "].");
				}
				break;
			case 4: 
				if (amount + alreadySpent - expensePreviousAmount > budget.getLimitApril()) {
					System.out.println("WARNING. You are exceeding the April budget [" + budget.getLimitApril() + "].");
				}
				break;
			case 5: 
				if (amount + alreadySpent - expensePreviousAmount > budget.getLimitMay()) {
					System.out.println("WARNING. You are exceeding the May budget [" + budget.getLimitMay() + "].");
				}
				break;
			case 6: 
				if (amount + alreadySpent - expensePreviousAmount > budget.getLimitJune()) {
					System.out.println("WARNING. You are exceeding the June budget [" + budget.getLimitJune() + "].");
				}
				break;
			case 7: 
				if (amount + alreadySpent - expensePreviousAmount > budget.getLimitJuly()) {
					System.out.println("WARNING. You are exceeding the July budget [" + budget.getLimitJuly() + "].");
				}
				break;
			case 8: 
				if (amount + alreadySpent - expensePreviousAmount > budget.getLimitAugust()) {
					System.out.println("WARNING. You are exceeding the August budget [" + budget.getLimitAugust() + "].");
				}
				break;
			case 9: 
				if (amount + alreadySpent - expensePreviousAmount > budget.getLimitSeptember()) {
					System.out.println("WARNING. You are exceeding the September budget [" + budget.getLimitSeptember() + "].");
				}
				break;
			case 10: 
				if (amount + alreadySpent - expensePreviousAmount > budget.getLimitOctober()) {
					System.out.println("WARNING. You are exceeding the October budget [" + budget.getLimitOctober() + "].");
				}
				break;
			case 11: 
				if (amount + alreadySpent - expensePreviousAmount > budget.getLimitNovember()) {
					System.out.println("WARNING. You are exceeding the November budget [" + budget.getLimitNovember() + "].");
				}
				break;
			case 12: 
				if (amount + alreadySpent - expensePreviousAmount > budget.getLimitDecember()) {
					System.out.println("WARNING. You are exceeding the December budget [" + budget.getLimitDecember() + "].");
				}
				break;
		}
	}
}
