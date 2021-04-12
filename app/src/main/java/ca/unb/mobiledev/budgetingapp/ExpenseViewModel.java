package ca.unb.mobiledev.budgetingapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import ca.unb.mobiledev.budgetingapp.entity.Expense;
import ca.unb.mobiledev.budgetingapp.repository.ExpenseRepository;

public class ExpenseViewModel extends AndroidViewModel {

    private ExpenseRepository expenseRepository;
    private List<Expense> expenses;

    public ExpenseViewModel(@NonNull Application application) {
        super(application);
        expenseRepository = new ExpenseRepository(application);
    }



    public List<Expense> getAllExpenses() {
        return expenseRepository.getAllExpenses();
    }



    public List<Expense> getMonthlyExpenses(int month, int year) {
        return expenseRepository.getMonthlyExpenses(month, year);
    }



    public List<Expense> getWeeklyExpenses(int year, int month, int startDay, int endDay) {
        return expenseRepository.getWeeklyExpenses(year, month, startDay, endDay);
    }


    public List<Expense> getDailyExpenses(int year, int month, int day) {
        return expenseRepository.getDailyExpenses(year, month, day);
    }


    public Expense getExpenseById(int id) {
        return expenseRepository.getExpenseById(id);
    }



    public void insert(String name, double amount, int day, int month, int year) {
        expenseRepository.insertRecord(name, amount, day, month, year);
    }



    public void delete(Expense expense) {
        expenseRepository.deleteRecord(expense);
    }

}
