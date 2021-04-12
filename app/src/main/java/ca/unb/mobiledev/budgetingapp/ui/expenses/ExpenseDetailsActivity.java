package ca.unb.mobiledev.budgetingapp.ui.expenses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ca.unb.mobiledev.budgetingapp.MainActivity;
import ca.unb.mobiledev.budgetingapp.R;
import ca.unb.mobiledev.budgetingapp.entity.Expense;

public class ExpenseDetailsActivity extends AppCompatActivity {

    private String name, amount, date;
    private int id;

    private TextView tvName, tvAmount, tvDate;

    private Button btnDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);

        // Capturing intent and all data passed from the expense fragment
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        amount = intent.getStringExtra("amount");
        date = intent.getStringExtra("date");
        id = intent.getIntExtra("id", -1);


        // Getting references to Text Views and the delete button
        tvName = findViewById(R.id.tvExpenseDetailName);
        tvAmount = findViewById(R.id.tvExpenseDetailAmount);
        tvDate = findViewById(R.id.tvExpenseDetailDateValue);

        btnDelete = findViewById(R.id.btnDeleteExpense);


        // Setting text views to captured intent values
        tvName.setText(name);
        tvAmount.setText(amount);
        tvDate.setText(date);


        // Handling button click
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Expense expense = MainActivity.expenseViewModel.getExpenseById(id);
                openDeleteConfirmationDialog(ExpenseDetailsActivity.this, expense);
            }
        });


        // Set the back button on the Action Bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void openDeleteConfirmationDialog(Context context, Expense expense) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.title_delete_expense)
                .setMessage(R.string.msg_delete_expense);

        builder.setPositiveButton(R.string.btn_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.expenseViewModel.delete(expense);
                Toast.makeText(context, "Expense deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

}
