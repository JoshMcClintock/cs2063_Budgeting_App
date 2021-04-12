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
        public TableRow mTableRow;

        public ViewHolder(TableRow v) {
            super(v);
            mTableRow = v;
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

        TextView tvName = holder.mTableRow.findViewById(R.id.tv_item_name);
        TextView tvAmount = holder.mTableRow.findViewById(R.id.tv_item_amount);
        TextView tvDate = holder.mTableRow.findViewById(R.id.tv_item_date);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

        String name = income.getName();
        String date = income.getDay() + "-" + (income.getMonth() + 1) + "-" + income.getYear();
        double amount = income.getAmount();

        tvName.setText(name);
        tvAmount.setText(numberFormat.format(amount));
        tvDate.setText(date);

        holder.mTableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, IncomeDetailsActivity.class)
                        .putExtra("id", income.getId())
                        .putExtra("name", name)
                        .putExtra("amount", numberFormat.format(amount))
                        .putExtra("date", date);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
