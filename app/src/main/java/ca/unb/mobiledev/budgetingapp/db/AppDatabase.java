package ca.unb.mobiledev.budgetingapp.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ca.unb.mobiledev.budgetingapp.dao.ExpenseDao;
import ca.unb.mobiledev.budgetingapp.dao.IncomeDao;
import ca.unb.mobiledev.budgetingapp.entity.Expense;
import ca.unb.mobiledev.budgetingapp.entity.Income;

@Database(entities = {Income.class, Expense.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public abstract IncomeDao incomeDao();
    public abstract ExpenseDao expenseDao();

    // Define an ExecutorService with a fixed thread pool which is used to run database operations asynchronously on a background thread
    public static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // Singleton access to the database
    public static AppDatabase getDatabase(final Context context) {
        if (null == INSTANCE) {
            synchronized (AppDatabase.class) {
                if (null == INSTANCE) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }

}
