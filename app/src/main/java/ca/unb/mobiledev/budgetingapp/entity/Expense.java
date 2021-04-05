package ca.unb.mobiledev.budgetingapp.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "expense_table")
public class Expense {

    /**
     * The unique identifier for this Expense
     */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    /**
     * The name of the expense, for example: "rent" or "groceries"
     */
    private String name;

    /**
     * The cost of this expense
     */
    private double amount;

    /**
     * The day that the expense is spent
     */
    private int day;

    /**
     * The month that the expense is spent
     */
    private int month;

    /**
     * The year that the expense is spent
     */
    private int year;


    /**
     * Get the unique identifier for this Expense
     * @return the unique identifier of this Expense
     */
    public int getId() {
        return id;
    }

    /**
     * Set the unique identifier for this Expense
     * @param id the unique identifier
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the name of this Expense
     * @return the name of this Expense
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of this Expense
     * @param name the name of this Expense
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the dollar amount for this Expense
     * @return the dollar amount for this Expense
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Set the dollar amount for this Expense
     * @param amount the dollar amount for this Expense
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Get the day money is spent for this Expense
     * @return the day money is spent for this Expense
     */
    public int getDay() {
        return day;
    }

    /**
     * Set the day money is spent for this Expense
     * @param day the date money is spent for this Expense
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Get the month money is spent for this Expense
     * @return the month money is spent for this Expense
     */
    public int getMonth() {
        return month;
    }

    /**
     * Set the month money is spent for this Expense
     * @param month the month money is spent for this Expense
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Get the year money is spent for this Expense
     * @return the year money is spent for this Expense
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the year money is spent for this Expense
     * @param year the year money is spent for this Expense
     */
    public void setYear(int year) {
        this.year = year;
    }

}
