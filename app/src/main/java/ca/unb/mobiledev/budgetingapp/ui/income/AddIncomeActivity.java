package ca.unb.mobiledev.budgetingapp.ui.income;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import ca.unb.mobiledev.budgetingapp.MainActivity;
import ca.unb.mobiledev.budgetingapp.R;
import ca.unb.mobiledev.budgetingapp.entity.Income;

public class AddIncomeActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etAmount;
    private EditText etDate;

    private Income income;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);


        // ========================================================================================
        // Getting references to the Edit Texts on the income form
        etName = findViewById(R.id.etIncomeName);
        etAmount = findViewById(R.id.etIncomeAmount);
        etDate = findViewById(R.id.etIncomeDate);


        // ========================================================================================
        // Button handler for saving an income
        Button btnSaveNewIncome = findViewById(R.id.btnSaveNewIncome);
        btnSaveNewIncome.setOnClickListener(v -> saveNewIncome());


        // ========================================================================================
        // Button handler for displaying a date picker
        ImageButton ibDatePicker = findViewById(R.id.ibIncomeDatePicker);
        ibDatePicker.setOnClickListener(v -> showDatePicker());


        // Set the back button on the Action Bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }



    public void saveNewIncome() {

        try {

            String name = etName.getText().toString().trim();
            double amount = Double.parseDouble(etAmount.getText().toString().trim());

            String date = etDate.getText().toString();
            String[] arrDate = date.split("/");
            int day = Integer.parseInt(arrDate[0]);
            int month = Integer.parseInt(arrDate[1]);
            month--;
            int year = Integer.parseInt(arrDate[2]);

            // Insert the new income
            MainActivity.incomeViewModel.insert(name, amount, day, month, year);


            // Notify the user that the income was saved
            Toast.makeText(this, "New income saved", Toast.LENGTH_SHORT).show();


            // Close the activity
            finish();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showDatePicker() {
        DialogFragment dialogFragment = new IncomeDatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "datePicker");
    }


}
