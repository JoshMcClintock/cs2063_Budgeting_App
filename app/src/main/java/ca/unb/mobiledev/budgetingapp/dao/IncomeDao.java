package ca.unb.mobiledev.budgetingapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ca.unb.mobiledev.budgetingapp.entity.Income;

@Dao
public interface IncomeDao {

    @Query("SELECT * FROM income_table")
    List<Income> getAllIncome();

    @Query("SELECT * FROM income_table WHERE month=:month AND year=:year ORDER BY day DESC")
    List<Income> getMonthlyIncome(int month, int year);

    @Query("SELECT * FROM income_table WHERE year=:year AND month=:month AND day=:day")
    List<Income> getDailyIncome(int year, int month, int day);

    @Query("SELECT * FROM income_table WHERE id=:id")
    Income getIncomeById(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Income income);

    @Delete
    void deleteIncome(Income income);

}
