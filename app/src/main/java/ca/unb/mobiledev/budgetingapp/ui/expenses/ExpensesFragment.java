package ca.unb.mobiledev.budgetingapp.ui.expenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

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

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_expenses, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ====================================================================================
        // Loading recycler view with expenses

        expenseViewModel = MainActivity.expenseViewModel;
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        expenses = new ArrayList<>(expenseViewModel.getMonthlyExpenses(month, year));

        rvExpenses = getView().findViewById(R.id.rvExpenses);
        expenseAdapter = new ExpenseAdapter(getContext(), expenses);
        rvExpenses.setAdapter(expenseAdapter);


        // ====================================================================================
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
}
