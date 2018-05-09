package com.example.lenovo.lab2intento2.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.lenovo.lab2intento2.Modelos.Forms;

import java.util.List;

/**
 * Created by lenovo on 24-04-2018.
 */
@Dao
public interface DaoForms {
    @Insert
    void insertOnlySingleForm(Forms forms);

    @Insert
    void insertMultipleForms(List<Forms> formList);

    @Query("SELECT * FROM Forms WHERE formId = :formId")
    Forms fetchOneFormsbyFormId(int formId);

    @Query("SELECT * FROM Forms")
    List<Forms> fetchAllForms();

    @Update
    void updateForm(Forms forms);

    @Delete
    void deleteForm(Forms forms);

    @Query("DELETE FROM Forms;")
    void deleteAllForms();
}

