package com.example.fitnessfirst.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise_essentials")
public class ExerciseEssentials {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ex_essential_id")
    private Long essentialId;

    @ColumnInfo(name = "essential_title")
    private String essentialTitle;

    @ColumnInfo(name = "essential_desc")
    private String essentialDesc;

    public ExerciseEssentials(String essentialTitle, String essentialDesc) {
        this.essentialTitle = essentialTitle;
        this.essentialDesc = essentialDesc;
    }

    public Long getEssentialId() {
        return essentialId;
    }

    public void setEssentialId(Long essentialId) {
        this.essentialId = essentialId;
    }

    public String getEssentialTitle() {
        return essentialTitle;
    }

    public void setEssentialTitle(String essentialTitle) {
        this.essentialTitle = essentialTitle;
    }

    public String getEssentialDesc() {
        return essentialDesc;
    }

    public void setEssentialDesc(String essentialDesc) {
        this.essentialDesc = essentialDesc;
    }

    @Override
    public String toString() {
        return "ExerciseEssentials{" +
                "essentialId=" + essentialId +
                ", essentialTitle='" + essentialTitle + '\'' +
                ", essentialDesc='" + essentialDesc + '\'' +
                '}';
    }
}
