package com.uapp.tasksservice.columntaskrelation;

public class ColumnTaskRelationDto {
    private int columnId;
    private int taskId;

    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
