package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {

        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "Title1", "Content1");
        taskList.add(task);

        when(taskRepository.findAll()).thenReturn(taskList);
        //When
        List<Task> tasks = dbService.getAllTasks();

        //Then
        assertEquals(1, tasks.size());
        assertEquals(1L, tasks.get(0).getId().longValue());
        assertEquals("Title1", tasks.get(0).getTitle());
        assertEquals("Content1", tasks.get(0).getContent());
    }

    @Test
    public void testGetTask() {

        //Given
        Task task = new Task(1L, "Title1", "Content1");

        when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(task));
        //When
        Optional<Task> tasks = dbService.getTaskId(1L);

        //Then
        assertNotNull(tasks);
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(1L, "Title1", "Content1");

        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task tasks = dbService.saveTask(task);

        //Then
        assertEquals(1L, tasks.getId().longValue());
    }

    @Test
    public void deleteTask() {
        //Given

        //When
        dbService.deleteTask(2L);

        //Then
        verify(taskRepository, times(1)).deleteById(2L);
    }
}