Project URL: https://roadmap.sh/projects/expense-tracker

List of Commands:
add [--description] [--amount] [--category]
example: add --description TV --amount 289.99 --category item
output: New expense added (ID:1)

update [--id] [--description] [--amount] [--category]
example: update --id 1 --description Pizza --amount 17.5 --category food
output: Expense updated successfully (ID:1)

delete [--id]
example: delete --id 1
output: Expense deleted successfully (ID:1)

list
example: list
output: 
(ID:1) - TV [item] costs 500.0 €. Created at 2025-08-19T17:12:24.510706 . Modified at 2025-10-15T14:46:22.922375898.
(ID:2) - Pillow [item] costs 39.99 €. Created at 2025-08-27T17:12:24.510706 . Modified at 2025-10-15T13:01:40.077288065.
(ID:3) - Madrid [travel] costs 50.0 €. Created at 2025-10-14T17:49:10.339819942 . Modified at 2025-10-15T12:21:30.097411372.
(ID:4) - Sushi [food] costs 32.65 €. Created at 2025-10-15T09:42:31.213368889 . Modified at 2025-10-15T11:03:48.340736009.
(ID:5) - Beer [food] costs 1.8 €. Created at 2025-10-15T14:50:52.733407940 . Modified at 2025-10-15T14:50:52.733419953.
(ID:6) - T-Shirt [clothes] costs 24.99 €. Created at 2025-10-15T16:41:46.062589391 . Modified at 2025-10-15T16:41:46.062600845.

summary [--month] [--category]
example: summary
output: Total expenses: 649.43€
example: summary --month 10
output: Total expenses for OCTOBER: 109.44€
example: summary --month 10 --category food
output: Total expenses for [food] in the month of OCTOBER: 34.45€

budget [--limit] [--month]
example: budget --limit 600 --month 10
output: Budget limit for OCTOBER updated: 600.0

export <filename>
example: export exit
output: export to a new file named exit.csvBudget limit for OCTOBER updated: 600.0

quit
example: quit
output: (terminate)

