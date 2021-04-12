package ca.unb.mobiledev.budgetingapp.ui.expenses;

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

public class AddExpenseActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etAmount;
    private EditText etDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        // ========================================================================================
        // Button handler for saving a new income
        Button btnSaveNewExpense = findViewById(R.id.btnSaveNewExpense);
        btnSaveNewExpense.setOnClickListener(v -> saveNewExpense());


        // ========================================================================================
        // Button handler for displaying a date picker
        ImageButton ibDatePicker = findViewById(R.id.ibExpenseDatePicker);
        ibDatePicker.setOnClickListener(v -> showDatePicker());


        // ========================================================================================
        // Getting references to the Edit Texts on the income form
        etName = findViewById(R.id.etExpenseName);
        etAmount = findViewById(R.id.etExpenseAmount);
        etDate = findViewById(R.id.etExpenseDate);

        // Set the back button on the Action Bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void saveNewExpense() {

        try {

            String name = etName.getText().toString().trim();
            double amount = Double.parseDouble(etAmount.getText().toString().trim());

            if (name.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else {

                String date = etDate.getText().toString();
                String[] arrDate = date.split("/");
                int day = Integer.parseInt(arrDate[0]);
                int month = Integer.parseInt(arrDate[1]);
                month--;
                int year = Integer.parseInt(arrDate[2]);

                // Insert the new income
                MainActivity.expenseViewModel.insert(name, amount, day, month, year);

                // Notify the user that the income was saved
                Toast.makeText(this, "New expense saved", Toast.LENGTH_SHORT).show();

                // Close the activity
                finish();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDatePicker() {
        DialogFragment dialogFragment = new ExpenseDatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "datePicker");
    }


}
