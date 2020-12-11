package com.uapp.tasksservice.task.sort;

import com.uapp.tasksservice.task.Task;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class SortingStrategy {
    public Comparator<Task> getComparator(SortingType sortingType) {
        switch (sortingType) {
            case SORT_BY_DATE_ASC:
                return Comparator.comparing(Task::getDateOfCreation);
            case SORT_BY_NAME_ASC:
                return Comparator.comparing(Task::getName);
            case SORT_BY_DATE_DESC:
                return Comparator.comparing(Task::getDateOfCreation).reversed();
            case SORT_BY_NAME_DESC:
                return Comparator.comparing(Task::getName).reversed();
            default:
                return Comparator.comparing(Task::getId);
        }
    }

}
