package expenseTrackerCLI;

import controllers.ExpenseTrackerController;
import entities.Expense;

public class ExpenseTrackerCLI {
	
	private static ExpenseTrackerController etc = new ExpenseTrackerController();
	
	public static void main (String args []) {
		etc.addExpense("screen", 285.99);
		etc.addExpense("onion", 0.80);
		etc.list();
		etc.summary();
		etc.deleteExpense(1);
		etc.updateExpense(1, "life", 1000.00);
		etc.list();
		etc.summary();
		etc.summaryByMonth(20);
	}
}
