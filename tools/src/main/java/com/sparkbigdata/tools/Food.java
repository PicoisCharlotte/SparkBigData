package com.sparkbigdata.tools;

import java.io.Serializable;
import java.util.Objects;

public class Food implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return id == food.id &&
                Objects.equals(name, food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
