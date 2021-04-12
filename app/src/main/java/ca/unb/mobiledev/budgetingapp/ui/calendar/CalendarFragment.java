package ca.unb.mobiledev.budgetingapp.ui.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class CalendarFragment extends Fragment {

    private CalendarView calendarView;
    private Calendar calendar;

    private IncomeViewModel incomeViewModel;
    private ExpenseViewModel expenseViewModel;

    private ArrayList<Income> incomes;
    private ArrayList<Expense> expenses;

    private NumberFormat numberFormat;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendar = Calendar.getInstance(TimeZone.getDefault());

        incomeViewModel = MainActivity.incomeViewModel;
        expenseViewModel = MainActivity.expenseViewModel;

        numberFormat = NumberFormat.getCurrencyInstance();

        loadUpcomingItems();

        calendarView = getView().findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Intent intent = new Intent(getContext(), CalendarActivity.class);
                intent.putExtra("day", dayOfMonth);
                intent.putExtra("month", month);
                intent.putExtra("year", year);
                startActivity(intent);

            }
        });
    }



    private void loadUpcomingItems() {

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int startDay = calendar.get(Calendar.DAY_OF_MONTH);
        int endDay = calendar.getActualMaximum(Calendar.DATE);
        int numDays = endDay - startDay;
        String tvNames = "";
        String tvAmounts = "";

        int dayOfWeek = calendar.getFirstDayOfWeek();
        String day = "Today";

        if ((endDay - startDay) > 5) {
            endDay = startDay + 5;
            numDays = endDay - startDay;
        }

        expenses = new ArrayList<>(expenseViewModel.getWeeklyExpenses(year, month, startDay, endDay));
        incomes = new ArrayList<>(incomeViewModel.getWeeklyIncome(year, month, startDay, endDay));

        TextView[] days = {
                (TextView) getView().findViewById(R.id.tvDailyDate1),
                (TextView) getView().findViewById(R.id.tvDailyDate2),
                (TextView) getView().findViewById(R.id.tvDailyDate3),
                (TextView) getView().findViewById(R.id.tvDailyDate4),
                (TextView) getView().findViewById(R.id.tvDailyDate5)
        };

        TextView[] names = {
                (TextView) getView().findViewById(R.id.tvDailyName1),
                (TextView) getView().findViewById(R.id.tvDailyName2),
                (TextView) getView().findViewById(R.id.tvDailyName3),
                (TextView) getView().findViewById(R.id.tvDailyName4),
                (TextView) getView().findViewById(R.id.tvDailyName5)
        };

        TextView[] amounts = {
                (TextView) getView().findViewById(R.id.tvDailyAmount1),
                (TextView) getView().findViewById(R.id.tvDailyAmount2),
                (TextView) getView().findViewById(R.id.tvDailyAmount3),
                (TextView) getView().findViewById(R.id.tvDailyAmount4),
                (TextView) getView().findViewById(R.id.tvDailyAmount5)
        };


        loadDayLabels(days, startDay, numDays);


        for (Income i : incomes) {
            if (startDay == i.getDay()) {
                tvNames += i.getName();
                tvNames += "\n";

                tvAmounts += numberFormat.format(i.getAmount());
                tvAmounts += "\n";
            }
        }

        for (Expense e : expenses) {
            if (startDay == e.getDay()) {
                tvNames += e.getName();
                tvNames += "\n";

                tvAmounts += numberFormat.format(e.getAmount());
                tvAmounts += "\n";
            }
        }

        if (tvNames.isEmpty()) {
            tvNames = "No items";
        }

        names[0].setText(tvNames);
        amounts[0].setText(tvAmounts);
        startDay ++;
        tvNames = "";
        tvAmounts = "";

        // Rest of the days
        for (int j=1; j < numDays; j++) {

            for (Income i : incomes) {
                if (startDay == i.getDay()) {
                    tvNames += i.getName();
                    tvNames += "\n";

                    tvAmounts += numberFormat.format(i.getAmount());
                    tvAmounts += "\n";
                }
            }

            for (Expense e : expenses) {
                if (startDay == e.getDay()) {
                    tvNames += e.getName();
                    tvNames += "\n";

                    tvAmounts += numberFormat.format(e.getAmount());
                    tvAmounts += "\n";
                }
            }

            if (tvNames.isEmpty()) {
                tvNames = "No items";
            }

            names[j].setText(tvNames);
            amounts[j].setText(tvAmounts);
            startDay++;
            tvNames = "";
            tvAmounts = "";
        }

    }


    private void loadDayLabels(TextView[] days, int startDay, int numDays) {
        days[0].setText("Today");
        String day = "";
        int currentDay = (calendar.get(Calendar.DAY_OF_WEEK) + 1) % 7;

        for (int i=1; i<numDays; i++) {
            day = getWeekDay(currentDay);
            days[i].setText(day);
            currentDay = (currentDay + 1) % 7;




        }

    }


    private String getWeekDay(int day) {
        String weekDay = "";

        switch (day) {
            case 1:
                weekDay = "Sunday";
                break;
            case 2:
                weekDay = "Monday";
                break;
            case 3:
                weekDay = "Tuesday";
                break;
            case 4:
                weekDay = "Wednesday";
                break;
            case 5:
                weekDay = "Thursday";
                break;
            case 6:
                weekDay = "Friday";
                break;
            case 7:
                weekDay = "Saturday";
                break;
        }

        return weekDay;
    }

}
