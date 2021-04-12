package ca.unb.mobiledev.budgetingapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import ca.unb.mobiledev.budgetingapp.dao.IncomeDao;
import ca.unb.mobiledev.budgetingapp.db.AppDatabase;
import ca.unb.mobiledev.budgetingapp.entity.Expense;
import ca.unb.mobiledev.budgetingapp.entity.Income;

public class IncomeRepository {
    private IncomeDao incomeDao;

    public IncomeRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        incomeDao = db.incomeDao();
    }


    public List<Income> getAllIncome() {
        List<Income> liveData = null;

        Future<List<Income>> future = AppDatabase.databaseWriterExecutor.submit(new Callable<List<Income>>() {
            @Override
            public List<Income> call() {
                return incomeDao.getAllIncome();
            }
        });

        try {
            liveData = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liveData;
    }



    public List<Income> getMonthlyIncome(int month, int year) {
        List<Income> liveData = null;

        Future<List<Income>> future = AppDatabase.databaseWriterExecutor.submit(new Callable<List<Income>>() {
            @Override
            public List<Income> call() {
                return incomeDao.getMonthlyIncome(month, year);
            }
        });


        try {
            liveData = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return liveData;
    }



    public List<Income> getDailyIncome(int year, int month, int day) {
        List<Income> liveData = null;

        Future<List<Income>> future = AppDatabase.databaseWriterExecutor.submit(new Callable<List<Income>>() {
            @Override
            public List<Income> call() {
                return incomeDao.getDailyIncome(year, month, day);
            }
        });

        try {
            liveData = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liveData;
    }



    public Income getIncomeById(int id) {
        Income income = null;

        Future<Income> future = AppDatabase.databaseWriterExecutor.submit(new Callable<Income>() {
            @Override
            public Income call() throws Exception {
                return incomeDao.getIncomeById(id);
            }
        });


        try {
            income = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return income;
    }



    public void insertRecord(String name, double amount, int day, int month, int year) {
        Income income = new Income();
        income.setName(name);
        income.setAmount(amount);
        income.setDay(day);
        income.setMonth(month);
        income.setYear(year);

        insertRecord(income);

    }

    private void insertRecord(final Income income) {
        AppDatabase.databaseWriterExecutor.execute(() ->
                incomeDao.insert(income));
    }

    public void deleteRecord(final Income income) {
        AppDatabase.databaseWriterExecutor.execute(() ->
                incomeDao.deleteIncome(income));
    }

}
