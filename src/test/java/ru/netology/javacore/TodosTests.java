package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TodosTests {
    protected Todos todos = new Todos();

    @BeforeEach
    void setUp() {
        todos.addTask("Работа");
        todos.addTask("Шопинг");
        todos.addTask("Прогулка");
        todos.addTask("Отдых");
        todos.addTask("Учеба");
    }

    @Test
    @DisplayName("Тестирование добавления задач")
    void testAddTask() {
        todos.addTask("Вечеринка");
        String result = "Вечеринка Отдых Прогулка Работа Учеба Шопинг";
        Assertions.assertEquals(result, todos.getAllTasks());
    }

    @Test
    @DisplayName("Тестирование удаления задач")
    void testRemoveTask() {
        todos.removeTask("Работа");
        String result = "Отдых Прогулка Учеба Шопинг";
        Assertions.assertEquals(result, todos.getAllTasks());
    }

    @Test
    @DisplayName("Тестирование отсутствия повторов")
    void testReplay() {
        todos.addTask("Отдых");
        String result = "Отдых Прогулка Работа Учеба Шопинг";
        Assertions.assertEquals(result, todos.getAllTasks());
    }

    @Test
    @DisplayName("Тестирование невозможности добавления более 7 задач")
    void testMaxSize() {
        todos.addTask("Вечеринка");
        todos.addTask("Концерт");
        todos.addTask("Лекция");
        String result = "Вечеринка Концерт Отдых Прогулка Работа Учеба Шопинг";
        Assertions.assertEquals(result, todos.getAllTasks());
    }
}
