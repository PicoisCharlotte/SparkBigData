package com.sparkbigdata.tools;

import java.io.Serializable;
import java.util.Objects;

public class TypeFood implements Serializable {
    private int id;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeFood typeFood = (TypeFood) o;
        return id == typeFood.id &&
                Objects.equals(type, typeFood.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
