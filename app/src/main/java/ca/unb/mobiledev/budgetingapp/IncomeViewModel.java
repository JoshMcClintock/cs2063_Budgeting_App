package ca.unb.mobiledev.budgetingapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import ca.unb.mobiledev.budgetingapp.entity.Expense;
import ca.unb.mobiledev.budgetingapp.entity.Income;
import ca.unb.mobiledev.budgetingapp.repository.IncomeRepository;

public class IncomeViewModel extends AndroidViewModel {

    private IncomeRepository incomeRepository;
    private List<Income> incomes;



    public IncomeViewModel(@NonNull Application application) {
        super(application);
        incomeRepository = new IncomeRepository(application);
    }



    public List<Income> getAllIncome() {
        return incomeRepository.getAllIncome();
    }



    public List<Income> getMonthlyIncome(int month, int year) {
        return incomeRepository.getMonthlyIncome(month, year);
    }


    public List<Income> getDailyIncome(int year, int month, int day) {
        return incomeRepository.getDailyIncome(year, month, day);
    }


    public Income getIncomeById(int id) {
        return incomeRepository.getIncomeById(id);
    }



    public void insert(String name, double amount, int day, int month, int year) {
        incomeRepository.insertRecord(name, amount, day, month, year);
    }



    public void delete(Income income) {
        incomeRepository.deleteRecord(income);
    }

}
