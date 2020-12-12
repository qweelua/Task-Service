package com.uapp.tasksservice.task.sort;

import com.uapp.tasksservice.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;

@Component
public class SortingFactory {
    private final SortingStrategy sortingStrategy;

    @Autowired
    public SortingFactory(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public Optional<Comparator<Task>> getComparator(String sortingType) {
        for (SortingType type : SortingType.values()) {
            if (type.name().equalsIgnoreCase(sortingType)) {
                return Optional.of(sortingStrategy.getComparator(type));
            }
        }
        return Optional.empty();
    }
}
