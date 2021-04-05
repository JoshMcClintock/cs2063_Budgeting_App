package ca.unb.mobiledev.budgetingapp.ui.income;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;

import ca.unb.mobiledev.budgetingapp.MainActivity;
import ca.unb.mobiledev.budgetingapp.R;
import ca.unb.mobiledev.budgetingapp.entity.Income;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHolder> {

    private ArrayList<Income> mDataset;
    private Context context;

    public IncomeAdapter(Context context, ArrayList<Income> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    // ViewHolder represents an individual item to display. In this case
    // it will just be a single TextView (displaying the title of a course)
    // but RecyclerView gives us the flexibility to do more complex things
    // (e.g., display an image and some text).
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TableRow mRelativeLayout;

        public ViewHolder(TableRow v) {
            super(v);
            mRelativeLayout = v;
        }
    }

    // The inflate method of the LayoutInflater class can be used to obtain the
    // View object corresponding to an XML layout resource file. Here
    // onCreateViewHolder inflates the TextView corresponding to item_layout.xml
    // and uses it to instantiate a ViewHolder.
    @Override
    public IncomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        TableRow v = (TableRow) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_layout, parent, false);

        return new ViewHolder(v);
    }

    // onBindViewHolder binds a ViewHolder to the data at the specified
    // position in mDataset
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        final Income income = mDataset.get(position);

        TextView tvName = holder.mRelativeLayout.findViewById(R.id.tv_item_name);
        TextView tvAmount = holder.mRelativeLayout.findViewById(R.id.tv_item_amount);
        TextView tvDate = holder.mRelativeLayout.findViewById(R.id.tv_item_date);
        ImageButton btnDelete = holder.mRelativeLayout.findViewById(R.id.ibDelete);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

        tvName.setText(income.getName());
        tvAmount.setText(numberFormat.format(income.getAmount()));
        tvDate.setText(income.getDay() + "-" + (income.getMonth() + 1) + "-" + income.getYear());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeleteConfirmationDialog(income);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    private void openDeleteConfirmationDialog(Income income) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.title_delete_income)
                .setMessage(R.string.msg_delete_income);

        builder.setPositiveButton(R.string.btn_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.incomeViewModel.delete(income);
                Toast.makeText(context, "Income deleted", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();

    }

}
