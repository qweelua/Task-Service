package com.uapp.tasksservice.column;

import java.util.Objects;

public class Column {
    private int id;
    private String name;
    private int order;

    public Column(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

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
        Column column = (Column) o;
        return getId() == column.getId() &&
                Objects.equals(getName(), column.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Column{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
