package com.example.lenovo.lab2intento2.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.lenovo.lab2intento2.Dao.DaoAnswers;
import com.example.lenovo.lab2intento2.Dao.DaoForms;
import com.example.lenovo.lab2intento2.Dao.DaoQuestions;
import com.example.lenovo.lab2intento2.Modelos.Forms;
import com.example.lenovo.lab2intento2.Modelos.Answers;
import com.example.lenovo.lab2intento2.Modelos.Questions;

/**
 * Created by lenovo on 24-04-2018.
 */


@Database(entities = {Forms.class, Answers.class, Questions.class}, version = 1, exportSchema = false)
public abstract class   FormDatabase extends RoomDatabase {
    public abstract DaoForms daoForms() ;
    public abstract DaoAnswers daoAnswers() ;
    public abstract DaoQuestions daoQuestions() ;
}