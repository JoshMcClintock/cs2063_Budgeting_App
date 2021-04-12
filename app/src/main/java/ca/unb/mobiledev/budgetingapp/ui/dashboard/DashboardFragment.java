package ca.unb.mobiledev.budgetingapp.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormatSymbols;
import java.text.NumberFormat;
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

    private PieChart pieChart;

    private double totalIncome;
    private double totalExpenses;

    private Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

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

        pieChart = getView().findViewById(R.id.pieChart);

        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        incomes = new ArrayList<>(incomeViewModel.getMonthlyIncome(month, year));
        expenses = new ArrayList<>(expenseViewModel.getMonthlyExpenses(month, year));

        numberFormat = NumberFormat.getCurrencyInstance();

        loadPage();

    }

    private void loadPage() {

        TextView header = getView().findViewById(R.id.tvDashboardHeader);
        header.setText(getCurrentMonth());

        loadTotalIncome();
        loadTotalExpenses();
        loadSavings();

        loadPieChart();
    }

    private void loadTotalIncome() {
        totalIncome = 0.0d;

        for (Income i : incomes) {
            totalIncome += i.getAmount();
        }

        TextView amount = getView().findViewById(R.id.tvTotalIncomeAmount);

        if (totalIncome == 0.0d) {
            amount.setText("No Income");
        }
        else {
            amount.setText(numberFormat.format(totalIncome));
        }

    }

    private void loadTotalExpenses() {
        totalExpenses = 0.0d;

        for (Expense e : expenses) {
            totalExpenses += e.getAmount();
        }

        TextView amount = getView().findViewById(R.id.tvTotalExpenseAmount);

        if (totalExpenses == 0.0d) {
            amount.setText("No Expenses");
        }
        else {
            amount.setText(numberFormat.format(totalExpenses));
        }
    }


    private void loadSavings() {

        double amount = totalIncome - totalExpenses;
        TextView saveAmount = getView().findViewById(R.id.tvMoneySavedAmount);
        saveAmount.setText(numberFormat.format(amount));

        if (amount >= 0.0d) {
            saveAmount.setTextColor(Color.rgb(34, 139, 34));
        } else {
            saveAmount.setTextColor(Color.RED);
        }

    }


    private void loadPieChart() {
        ArrayList<PieEntry> slices = new ArrayList<>();
        int colors[];

        for (Expense e : expenses) {
            String label = e.getName();
            float value = (float) e.getAmount();
            slices.add(new PieEntry(value, label));
        }

        if (expenses.isEmpty()) {
            slices.add(new PieEntry(1, "No expenses"));
            colors = new int[]{Color.rgb(64, 64, 64)};
        } else {

            colors = new int[]{
                    Color.rgb(153, 0, 76),
                    Color.rgb(76, 0, 153),
                    Color.rgb(0, 76, 153),
                    Color.rgb(0, 153, 153),
                    Color.rgb(0, 153, 76),
                    Color.rgb(76, 153, 0),
                    Color.rgb(153, 153, 0),
                    Color.rgb(153, 76, 0),
                    Color.rgb(153, 0, 0)
            };
        }


        PieDataSet pieDataSet = new PieDataSet(slices, "Expense Breakdown");
        pieDataSet.setColors(colors);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Expense Breakdown");
        pieChart.animateY(1000);
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