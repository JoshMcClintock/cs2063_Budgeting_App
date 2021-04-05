package ca.unb.mobiledev.budgetingapp.repository;

import android.app.Application;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import ca.unb.mobiledev.budgetingapp.dao.ExpenseDao;
import ca.unb.mobiledev.budgetingapp.db.AppDatabase;
import ca.unb.mobiledev.budgetingapp.entity.Expense;

public class ExpenseRepository {
    private ExpenseDao expenseDao;

    public ExpenseRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        expenseDao = db.expenseDao();
    }


    public List<Expense> getAllExpenses() {
        List<Expense> liveData = null;

        Future<List<Expense>> future = AppDatabase.databaseWriterExecutor.submit(new Callable<List<Expense>>() {
            @Override
            public List<Expense> call() {
                return expenseDao.getAllExpenses();
            }
        });

        try {
            liveData = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liveData;
    }

    public List<Expense> getMonthlyExpenses(int month, int year) {
        List<Expense> liveData = null;

        Future<List<Expense>> future = AppDatabase.databaseWriterExecutor.submit(new Callable<List<Expense>>() {
            @Override
            public List<Expense> call() {
                return expenseDao.getMonthlyExpenses(month, year);
            }
        });

        try {
            liveData = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liveData;
    }

    public void insertRecord(String name, double amount, int day, int month, int year) {
        Expense expense = new Expense();
        expense.setName(name);
        expense.setAmount(amount);
        expense.setDay(day);
        expense.setMonth(month);
        expense.setYear(year);

        insertRecord(expense);

    }

    private void insertRecord(final Expense expense) {
        AppDatabase.databaseWriterExecutor.execute(() ->
                expenseDao.insert(expense));
    }

    public void deleteRecord(final Expense expense) {
        AppDatabase.databaseWriterExecutor.execute(() ->
                expenseDao.deleteExpense(expense));
    }
    
}
