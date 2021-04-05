package ca.unb.mobiledev.budgetingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.unb.mobiledev.budgetingapp.entity.Income;
import ca.unb.mobiledev.budgetingapp.ui.income.IncomeAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static IncomeViewModel incomeViewModel;
    public static ExpenseViewModel expenseViewModel;

    private IncomeAdapter incomeAdapter;
    private static ArrayList<Income> incomes;
    public static NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, "onCreate() called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_dashboard, R.id.navigation_income,
                R.id.navigation_expenses, R.id.navigation_calendar)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        incomeViewModel = new ViewModelProvider(this).get(IncomeViewModel.class);
        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);
    }

}