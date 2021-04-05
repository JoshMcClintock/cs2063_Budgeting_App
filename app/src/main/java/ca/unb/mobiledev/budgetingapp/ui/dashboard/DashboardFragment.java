package ca.unb.mobiledev.budgetingapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import ca.unb.mobiledev.budgetingapp.ExpenseViewModel;
import ca.unb.mobiledev.budgetingapp.IncomeViewModel;
import ca.unb.mobiledev.budgetingapp.MainActivity;
import ca.unb.mobiledev.budgetingapp.R;
import ca.unb.mobiledev.budgetingapp.entity.Expense;
import ca.unb.mobiledev.budgetingapp.entity.Income;

public class DashboardFragment extends Fragment {

    private static IncomeViewModel incomeViewModel;
    private static ExpenseViewModel expenseViewModel;
    private ArrayList<Income> incomes;
    private ArrayList<Expense> expenses;
    private NumberFormat numberFormat;

    private double totalIncome;
    private double totalExpenses;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Getting references to IncomeViewModel and ExpenseViewModel for calculating statistics
        incomeViewModel = MainActivity.incomeViewModel;
        expenseViewModel = MainActivity.expenseViewModel;

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        incomes = new ArrayList<>(incomeViewModel.getMonthlyIncome(month, year));
        expenses = new ArrayList<>(expenseViewModel.getMonthlyExpenses(month, year));

        numberFormat = NumberFormat.getCurrencyInstance();

        loadPage();

    }

    private void loadPage() {
        loadTotalIncome();
        loadTotalExpenses();
        loadSavings();
    }

    private void loadTotalIncome() {
        totalIncome = 0.0;

        for (Income i :
                incomes) {
            totalIncome += i.getAmount();
        }

        TextView label = getView().findViewById(R.id.tvTotalIncomeLabel);
        TextView amount = getView().findViewById(R.id.tvTotalIncomeAmount);

        label.setText(String.format((String) label.getText(), getCurrentMonth()));
        amount.setText(numberFormat.format(totalIncome));

    }

    private void loadTotalExpenses() {
        totalExpenses = 0.0;

        for (Expense e :
                expenses) {
            totalExpenses += e.getAmount();
        }

        TextView label = getView().findViewById(R.id.tvTotalExpensesLabel);
        TextView amount = getView().findViewById(R.id.tvTotalExpenseAmount);

        label.setText(String.format((String) label.getText(), getCurrentMonth()));
        amount.setText(numberFormat.format(totalExpenses));

    }


    private void loadSavings() {

        double amount = totalIncome - totalExpenses;

        TextView saveAmount = getView().findViewById(R.id.tvMoneySavedAmount);
        saveAmount.setText(numberFormat.format(amount));

    }

    private String getCurrentMonth() {

        String strMonth = "";

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int intMonth = calendar.get(Calendar.MONTH);

        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();

        if (intMonth >= 0 && intMonth <= 11 ) {
            strMonth = months[intMonth];
        }
        return strMonth;
    }

}