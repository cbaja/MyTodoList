

/**
 * Created by carly.baja on 1/17/2016.
 */


package com.codepath.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;



@Table(name = "Tasks")
public class Todo extends Model {
    // If name is omitted, then the field name is used.
    @Column(name = "Description",index = true)
    public String description;

    @Column(name="Duedate")
    public String duedate;

    //  @Column(name = "Statu", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    @Column(name = "Statu")
    public String statu;


    public Todo(String description,String duedate, String statu) {
        super();
        this.description = description;
        this.duedate = duedate;
        this.statu = statu;    }

    public Todo(String description, String statu) {
        super();
        this.description = description;
        // this.duedate = duedate;
        this.statu = statu;
    }


    public Todo() {
        super();
    }

    public static List<Todo> getAll() {
        // This is how you execute a query
        return new Select()
                .from(Todo.class)
                .orderBy("Description ASC")
                .execute();
    }

}

