package controllers;

import java.util.ArrayList;
import java.util.List;

import entities.Expense;

public class ExpenseTrackerController {
	
	List<Expense> listExpenses = new ArrayList<>();
	
	public void addExpense (String description, double amount) {
		Expense expense = new Expense (description, amount);
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
}
