package ca.unb.mobiledev.budgetingapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ca.unb.mobiledev.budgetingapp.entity.Expense;
import ca.unb.mobiledev.budgetingapp.entity.Income;

@Dao
public interface ExpenseDao {

    @Query("SELECT * FROM expense_table")
    List<Expense> getAllExpenses();

    @Query("SELECT * FROM expense_table WHERE month=:month AND year=:year ORDER BY day DESC")
    List<Expense> getMonthlyExpenses(int month, int year);

    @Query("SELECT * FROM expense_table WHERE year=:year AND month=:month AND day >= :startDay AND day <= :endDay ORDER by day ASC")
    List<Expense> getWeeklyExpenses(int year, int month, int startDay, int endDay);

    @Query("SELECT * FROM expense_table WHERE year=:year AND month=:month AND day=:day")
    List<Expense> getDailyExpenses(int year, int month, int day);

    @Query("SELECT * FROM expense_table WHERE id=:id")
    Expense getExpenseById(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Expense expense);

    @Delete
    void deleteExpense(Expense expense);

}
