package com.codepath.models;



import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by carly.baja on 1/9/2016.
 */
@Table(name="Status")
public class Statu extends Model {

    @Column(name = "Name",index = true)
    public String name;

    // This method is optional, does not affect the foreign key creation.
    public List<Task> tasks() {
        return getMany(Task.class, "Statu");
    }

}
