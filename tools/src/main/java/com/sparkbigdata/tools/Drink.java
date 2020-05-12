package com.sparkbigdata.tools;

import java.io.Serializable;
import java.util.Objects;

public class Drink implements Serializable  {
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
        Drink drink = (Drink) o;
        return id == drink.id &&
                Objects.equals(name, drink.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
