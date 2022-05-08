package com.example.fitnessfirst.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The type Exercise.
 */
@Entity(tableName = "exercises")
public class Exercise {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ex_id")
    private Long exId;

    @ColumnInfo(name = "ex_type")
    private String exType;

    @ColumnInfo(name = "ex_title")
    private String exTitle;

    @ColumnInfo(name = "ex_desc")
    private String exDesc;

    /**
     * Instantiates a new Exercise.
     *
     * @param exType  the ex type
     * @param exTitle the ex title
     * @param exDesc  the ex desc
     */
    public Exercise(String exType, String exTitle, String exDesc) {
        this.exType = exType;
        this.exTitle = exTitle;
        this.exDesc = exDesc;
    }

    /**
     * Gets ex id.
     *
     * @return the ex id
     */
    public Long getExId() {
        return exId;
    }

    /**
     * Sets ex id.
     *
     * @param exId the ex id
     */
    public void setExId(Long exId) {
        this.exId = exId;
    }

    /**
     * Gets ex title.
     *
     * @return the ex title
     */
    public String getExTitle() {
        return exTitle;
    }

    /**
     * Sets ex title.
     *
     * @param exTitle the ex title
     */
    public void setExTitle(String exTitle) {
        this.exTitle = exTitle;
    }

    /**
     * Gets ex desc.
     *
     * @return the ex desc
     */
    public String getExDesc() {
        return exDesc;
    }

    /**
     * Sets ex desc.
     *
     * @param exDesc the ex desc
     */
    public void setExDesc(String exDesc) {
        this.exDesc = exDesc;
    }

    /**
     * Gets ex type.
     *
     * @return the ex type
     */
    public String getExType() {
        return exType;
    }

    /**
     * Sets ex type.
     *
     * @param exType the ex type
     */
    public void setExType(String exType) {
        this.exType = exType;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "exId=" + exId +
                ", exTitle='" + exTitle + '\'' +
                ", exDesc='" + exDesc + '\'' +
                ", exType='" + exType + '\'' +
                '}';
    }
}
