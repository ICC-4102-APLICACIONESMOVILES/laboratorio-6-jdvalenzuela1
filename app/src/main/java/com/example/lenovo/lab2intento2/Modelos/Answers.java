package com.example.lenovo.lab2intento2.Modelos;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.location.Location;
import android.support.annotation.NonNull;

/**
 * Created by lenovo on 24-04-2018.
 */

@Entity
public class Answers {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int answerId;

    private String content;
    @ForeignKey(entity = Questions.class, parentColumns = "questionId", childColumns = "question")
    private int questionId;
    private String answerSet;
    private String Date;
    private Float latitude;
    private Float longitude;

    public Answers() {
    }
    public int getAnswerId() {return answerId;}
    public void setAnswerId(int answerId) {this.answerId = answerId;}

    public Float getLatitude() { return latitude; }
    public void setLatitude(Float latitude) { this.latitude = latitude; }

    public Float getLongitude() { return longitude; }
    public void setLongitude(Float longitude) { this.longitude = longitude; }

    public String getDate() { return Date; }
    public void setDate(String Date) { this.Date = Date; }

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}

    public int getQuestionId() {return questionId;}
    public void setQuestionId(int questionId) {this.questionId = questionId;}

    public String getAnswerSet() {return answerSet;}
    public void setAnswerSet(String answerSet) {this.answerSet = answerSet;}
}
