# CS2063 Team 17 Budgeting Application

## Release Notes:

### Implemented Features:
#### Dashboard
- Summary of finances 
- View monthly income and expenses
- Displays monthly savings
- Pie chart breaks down expenses
#### Income
- Add and delete income objects
- View income in a RecyclerView: click individual income to view details
- Option to delete income on the details page
#### Expenses
- Add and delete expense objects
- View expenses in a RecyclerView: click individual expense to view details
- Option to delete expense on the details page
#### Calendar
- Click any day of the calendar to view that days income and expenses
-  List all upcoming income and expenses up to 5 days for the current month


### Known Issues:
Layout and spacing is not optimized for different phone sizes and orientations. No major known issues.


## Supported API Levels:

Runs on API levels 28-30. Has not been tested on other API levels.


## Testing:

#### Dashboard
- Monthly income and expenses value should not be changed when adding items for different months or years
- Monthly savings should be green when non-negative and red when negative
#### Income
- Adding income for current month shows in recycler view
- Adding income for current month, but different year, should not appear in recycler view
- Deleting income removes it from recycler view
#### Expenses
- Adding expenses for current month shows in recycler view
- Adding expenses for current month, but different year, should not appear in recycler view
- Deleting an expense removes it from recycler view
#### Calendar
- Selecting a day in the calendar should only show items for that day
- Upcoming items should not show anything for the same days of different years and months



