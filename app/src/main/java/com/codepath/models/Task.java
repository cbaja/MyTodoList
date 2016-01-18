package com.codepath.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by carly.baja on 1/9/2016.
 */

@Table(name = "Tasks")
public class Task extends Model {
    // If name is omitted, then the field name is used.
    @Column(name = "Description",index = true)
    public String description;

    @Column(name="Duedate")
    public Date duedate;

  //  @Column(name = "Statu", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    @Column(name = "Statu")
    public String statu;


    public Task(String description,Date duedate, String statu) {
        super();
        this.description = description;
        this.duedate = duedate;
        this.statu = statu;
    }


    public Task() {
        super();
    }

    public static List<Task> getAll() {
        // This is how you execute a query
        return new Select()
                .from(Task.class)
                .orderBy("Description ASC")
                .execute();
    }

}
