package controllers;

import entities.Expense;

public class ExpenseTrackerController {
	
	public void addExpense (String description, double amount) {
		System.out.println("addExpense");
	}
	
	public void list () {
		System.out.println("list");
	}
	
	public void summary () {
		System.out.println("summary");
	}
	
	public void deleteExpense (int id) {
		System.out.println("deleteExpense");
	}
	
	public void updateExpense (int id, String description, double amount) {
		System.out.println("updateExpense");
	}
	
	public void summaryByMonth(int month) {
		System.out.println("summaryByMonth");
	}
}
