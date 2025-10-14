package expenseTrackerCLI;

import entities.Expense;

public class ExpenseTrackerCLI {

	public static void main (String args []) {
		Expense exp1 = new Expense("shoes", 40.25);
		
		System.out.println(exp1.getId() + exp1.getDescription() + exp1.getAmount() + exp1.getCreatedAt());
	}
}
