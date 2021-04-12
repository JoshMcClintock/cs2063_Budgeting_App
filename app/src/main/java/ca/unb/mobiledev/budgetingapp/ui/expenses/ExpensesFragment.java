package ca.unb.mobiledev.budgetingapp.ui.expenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import ca.unb.mobiledev.budgetingapp.ExpenseViewModel;
import ca.unb.mobiledev.budgetingapp.MainActivity;
import ca.unb.mobiledev.budgetingapp.R;
import ca.unb.mobiledev.budgetingapp.entity.Expense;

public class ExpensesFragment extends Fragment {

    private ExpenseViewModel expenseViewModel;
    private RecyclerView rvExpenses;
    private ArrayList<Expense> expenses;

    public static ExpenseAdapter expenseAdapter;

    private Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    private NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_expenses, container, false);
        return root;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Loading recycler view with expenses
        loadRecyclerView();
        loadHeader();

        // Button handler for adding a new expense
        Button addButton = getView().findViewById(R.id.btnAddExpenses);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addExpenseIntent = new Intent(getContext(), AddExpenseActivity.class);
                startActivity(addExpenseIntent);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        loadRecyclerView();
        loadHeader();
    }

    private void loadRecyclerView() {
        expenseViewModel = MainActivity.expenseViewModel;

        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        expenses = new ArrayList<>(expenseViewModel.getMonthlyExpenses(month, year));

        rvExpenses = getView().findViewById(R.id.rvExpenses);
        expenseAdapter = new ExpenseAdapter(getContext(), expenses);
        rvExpenses.setAdapter(expenseAdapter);
    }

    private void loadHeader() {
        double totalExpenses = 0.0d;

        for (Expense e : expenses) {
            totalExpenses += e.getAmount();
        }

        TextView header = getView().findViewById(R.id.tvExpensesDesc);
        String label = getString(R.string.tv_expenses_label);
        String month = getCurrentMonth();
        String expenses = numberFormat.format(totalExpenses);

        header.setText(String.format(label, month, expenses));

    }

    private String getCurrentMonth() {

        String strMonth = "";

        int intMonth = calendar.get(Calendar.MONTH);

        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();

        if (intMonth >= 0 && intMonth <= 11 ) {
            strMonth = months[intMonth];
        }
        return strMonth;
    }

}
