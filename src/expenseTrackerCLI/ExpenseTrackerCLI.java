package expenseTrackerCLI;

import controllers.ExpenseTrackerController;
import entities.Expense;

public class ExpenseTrackerCLI {
	
	public static void main (String args []) {
		ExpenseTrackerController etc = new ExpenseTrackerController();
		etc.list();
		etc.summary();
		etc.summaryByMonth(4);
		etc.updateExpense(2, "balon", 23.00);
	}
}
