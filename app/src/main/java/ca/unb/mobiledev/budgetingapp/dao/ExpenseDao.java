package ca.unb.mobiledev.budgetingapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ca.unb.mobiledev.budgetingapp.entity.Expense;

@Dao
public interface ExpenseDao {

    @Query("SELECT * FROM expense_table")
    List<Expense> getAllExpenses();

    @Query("SELECT * FROM expense_table WHERE month=:month AND year=:year ORDER BY day DESC")
    List<Expense> getMonthlyExpenses(int month, int year);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Expense expense);

    @Delete
    void deleteExpense(Expense expense);

}
