package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class TaskMapperTestSuite {
    @Test
    public void testMapToTask() {

        //Given
        TaskDto taskDto = new TaskDto(1L, "Title1", "Content1");
        TaskMapper taskMapper = new TaskMapper();

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertEquals(1L, (long)task.getId());
        Assert.assertEquals("Title1", task.getTitle());
        Assert.assertEquals("Content1", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {

        //Given
        Task task = new Task(1L, "Title1", "Content1");
        TaskMapper taskMapper = new TaskMapper();
        TaskDto taskDto = new TaskDto(1L, "Title1", "Content1");

        //When
        TaskDto taskDtoMapper = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals(taskDto.getId(), taskDtoMapper.getId());
        Assert.assertEquals(taskDto.getTitle(), taskDtoMapper.getTitle());
        Assert.assertEquals(taskDto.getContent(), taskDtoMapper.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {

        //Given
        List<Task> tasks = new ArrayList<>();
        LongStream.rangeClosed(1, 5)
                .forEach(n -> tasks.add(new Task(n, "Title" + n, "Content" + n)));
        TaskMapper taskMapper = new TaskMapper();

        //When
        List<TaskDto> taskDtosMapped = taskMapper.mapToTaskDtoList(tasks);

        //Then
        Assert.assertEquals(5, taskDtosMapped.size());
    }
}