package com.example.fitnessfirst.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    public Exercise(String exType, String exTitle, String exDesc) {
        this.exType = exType;
        this.exTitle = exTitle;
        this.exDesc = exDesc;
    }

    public Long getExId() {
        return exId;
    }

    public void setExId(Long exId) {
        this.exId = exId;
    }

    public String getExTitle() {
        return exTitle;
    }

    public void setExTitle(String exTitle) {
        this.exTitle = exTitle;
    }

    public String getExDesc() {
        return exDesc;
    }

    public void setExDesc(String exDesc) {
        this.exDesc = exDesc;
    }

    public String getExType() {
        return exType;
    }

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
