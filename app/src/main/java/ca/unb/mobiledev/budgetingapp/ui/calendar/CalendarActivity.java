package ca.unb.mobiledev.budgetingapp.ui.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.ArrayList;

import ca.unb.mobiledev.budgetingapp.ExpenseViewModel;
import ca.unb.mobiledev.budgetingapp.IncomeViewModel;
import ca.unb.mobiledev.budgetingapp.MainActivity;
import ca.unb.mobiledev.budgetingapp.R;
import ca.unb.mobiledev.budgetingapp.entity.Expense;
import ca.unb.mobiledev.budgetingapp.entity.Income;

public class CalendarActivity extends AppCompatActivity {

    private ArrayList<Income> income;
    private ArrayList<Expense> expenses;

    private IncomeViewModel incomeViewModel;
    private ExpenseViewModel expenseViewModel;

    private NumberFormat numberFormat;

    private TextView incomeName, incomeAmount, expenseName, expenseAmount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_show_daily);

        Intent intent = getIntent();
        int day = intent.getIntExtra("day", 0);
        int month = intent.getIntExtra("month", 0);
        int year = intent.getIntExtra("year", 0);
        getSupportActionBar().setTitle(day + "/" + (month + 1) + "/" + year);

        incomeViewModel = MainActivity.incomeViewModel;
        expenseViewModel = MainActivity.expenseViewModel;

        income = new ArrayList<>(incomeViewModel.getDailyIncome(year, month, day));
        expenses = new ArrayList<>(expenseViewModel.getDailyExpenses(year, month, day));

        incomeName = findViewById(R.id.tvDailyIncomeName);
        incomeAmount = findViewById(R.id.tvDailyIncomeAmount);
        expenseName = findViewById(R.id.tvDailyExpenseName);
        expenseAmount = findViewById(R.id.tvDailyExpenseAmount);


        numberFormat = NumberFormat.getCurrencyInstance();

        loadIncome();
        loadExpenses();

        // Set the back button on the Action Bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    private void loadIncome() {
        String name = "";
        String amount = "";

        if (income.isEmpty()) {
            name = "No income";
        } else {

            for (Income i : income) {
                name += i.getName();
                name += "\n";

                amount += numberFormat.format(i.getAmount());
                amount += "\n";
            }

        }

        incomeName.setText(name);
        incomeAmount.setText(amount);
    }



    private void loadExpenses() {
        String name = "";
        String amount = "";

        if (expenses.isEmpty()) {
            name = ("No expenses");
        } else {

            for (Expense e : expenses) {
                name += e.getName();
                name += "\n";

                amount += numberFormat.format(e.getAmount());
                amount += "\n";
            }

        }

        expenseName.setText(name);
        expenseAmount.setText(amount);
    }



    // Handle the back button on the Action Bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }


}
