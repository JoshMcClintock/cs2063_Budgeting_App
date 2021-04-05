package ca.unb.mobiledev.budgetingapp.ui.income;

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

import ca.unb.mobiledev.budgetingapp.IncomeViewModel;
import ca.unb.mobiledev.budgetingapp.MainActivity;
import ca.unb.mobiledev.budgetingapp.R;
import ca.unb.mobiledev.budgetingapp.entity.Income;

public class IncomeFragment extends Fragment {

    public static IncomeViewModel incomeViewModel;
    private RecyclerView rvIncome;
    private ArrayList<Income> incomes;

    public static IncomeAdapter incomeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_income, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ====================================================================================
        // Loading recycler view with incomes
        incomeViewModel = MainActivity.incomeViewModel;

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        incomes = new ArrayList<>(incomeViewModel.getMonthlyIncome(month, year));

        rvIncome = getView().findViewById(R.id.rvIncome);
        incomeAdapter = new IncomeAdapter(getContext(), incomes);
        rvIncome.setAdapter(incomeAdapter);


        // ====================================================================================
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


}
