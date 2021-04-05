package ca.unb.mobiledev.budgetingapp.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "income_table")
public class Income {

    /**
     * The unique identifier for this Income
     */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    /**
     * The name of the income, for example: "salary" or "part-time"
     */
    private String name;

    /**
     * The amount of money this Income will yield
     */
    private double amount;

    /**
     * The day that the income is received
     */
    private int day;

    /**
     * The month that the income is received
     */
    private int month;

    /**
     * The year that the income is received
     */
    private int year;


    /**
     * Get the unique identifier for this Income
     * @return the unique identifier of this Income
     */
    public int getId() {
        return id;
    }

    /**
     * Set the unique identifier for this Income
     * @param id the unique identifier
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the name of this Income
     * @return the name of this Income
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of this Income
     * @param name the name of this Income
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the dollar amount for this Income
     * @return the dollar amount for this Income
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Set the dollar amount for this Income
     * @param amount the dollar amount for this Income
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Get the day money is received for this Income
     * @return the day money is received for this Income
     */
    public int getDay() {
        return day;
    }

    /**
     * Set the day money is received for this Income
     * @param day the date money is received for this income
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Get the month money is received for this Income
     * @return the month money is received for this Income
     */
    public int getMonth() {
        return month;
    }

    /**
     * Set the month money is received for this Income
     * @param month the month money is received for this Income
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Get the year money is received for this Income
     * @return the year money is received for this Income
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the year money is received for this Income
     * @param year the year money is received for this Income
     */
    public void setYear(int year) {
        this.year = year;
    }
}
