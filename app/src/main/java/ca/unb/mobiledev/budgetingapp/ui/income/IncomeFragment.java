package ca.unb.mobiledev.budgetingapp.ui.income;

import android.content.Context;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import ca.unb.mobiledev.budgetingapp.IncomeViewModel;
import ca.unb.mobiledev.budgetingapp.MainActivity;
import ca.unb.mobiledev.budgetingapp.R;
import ca.unb.mobiledev.budgetingapp.entity.Expense;
import ca.unb.mobiledev.budgetingapp.entity.Income;

public class IncomeFragment extends Fragment {

    public static IncomeViewModel incomeViewModel;
    private static RecyclerView rvIncome;
    private static ArrayList<Income> incomes;

    public static IncomeAdapter incomeAdapter;

    private Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    private NumberFormat numberFormat = NumberFormat.getCurrencyInstance();


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_income, container, false);
        return root;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Loading recycler view with incomes
        loadRecyclerView();
        loadHeader();

        // Button handler for adding a new income
        Button addButton = getView().findViewById(R.id.btnAddIncome);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIncomeIntent = new Intent(getContext(), AddIncomeActivity.class);
                startActivity(addIncomeIntent);
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

        incomeViewModel = MainActivity.incomeViewModel;

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        incomes = new ArrayList<>(incomeViewModel.getMonthlyIncome(month, year));
        rvIncome = getView().findViewById(R.id.rvIncome);
        incomeAdapter = new IncomeAdapter(getContext(), incomes);

        rvIncome.setAdapter(incomeAdapter);
    }


    private void loadHeader() {
        double totalIncome = 0.0d;

        for (Income i : incomes) {
            totalIncome += i.getAmount();
        }

        TextView header = getView().findViewById(R.id.tvIncomeDesc);
        String label = getString(R.string.tv_income_label);
        String month = getCurrentMonth();
        String expenses = numberFormat.format(totalIncome);

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
