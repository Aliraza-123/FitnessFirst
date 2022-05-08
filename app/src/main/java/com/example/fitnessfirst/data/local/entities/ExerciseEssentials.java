package com.example.fitnessfirst.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The type Exercise essentials.
 */
@Entity(tableName = "exercise_essentials")
public class ExerciseEssentials {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ex_essential_id")
    private Long essentialId;

    @ColumnInfo(name = "essential_title")
    private String essentialTitle;

    @ColumnInfo(name = "essential_desc")
    private String essentialDesc;

    /**
     * Instantiates a new Exercise essentials.
     *
     * @param essentialTitle the essential title
     * @param essentialDesc  the essential desc
     */
    public ExerciseEssentials(String essentialTitle, String essentialDesc) {
        this.essentialTitle = essentialTitle;
        this.essentialDesc = essentialDesc;
    }

    /**
     * Gets essential id.
     *
     * @return the essential id
     */
    public Long getEssentialId() {
        return essentialId;
    }

    /**
     * Sets essential id.
     *
     * @param essentialId the essential id
     */
    public void setEssentialId(Long essentialId) {
        this.essentialId = essentialId;
    }

    /**
     * Gets essential title.
     *
     * @return the essential title
     */
    public String getEssentialTitle() {
        return essentialTitle;
    }

    /**
     * Sets essential title.
     *
     * @param essentialTitle the essential title
     */
    public void setEssentialTitle(String essentialTitle) {
        this.essentialTitle = essentialTitle;
    }

    /**
     * Gets essential desc.
     *
     * @return the essential desc
     */
    public String getEssentialDesc() {
        return essentialDesc;
    }

    /**
     * Sets essential desc.
     *
     * @param essentialDesc the essential desc
     */
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
