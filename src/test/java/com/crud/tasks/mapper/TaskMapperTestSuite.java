package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    TaskMapper taskMapper;

    @Test
    public void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        //When
        Task testTask = taskMapper.mapToTask(taskDto);
        //Then
        assertNotNull(testTask);
        assertEquals(testTask.getId(), taskDto.getId());
        assertEquals(testTask.getContent(), taskDto.getContent());
        assertEquals(testTask.getTitle(), taskDto.getTitle());
    }

    @Test
    public void mapToTaskDtoTest() {
        //Given
        Task task = new Task(100L, "title100", "content100");
        //When
        TaskDto testTaskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertNotNull(testTaskDto);
        assertEquals(testTaskDto.getId(), task.getId());
        assertEquals(testTaskDto.getContent(), task.getContent());
        assertEquals(testTaskDto.getTitle(), task.getTitle());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "test1", "content1"));
        taskList.add(new Task(2L, "test2", "content2"));
        taskList.add(new Task(3L, "test3", "content3"));
        taskList.add(new Task(4L, "test4", "content4"));
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertNotNull(taskDtoList);
        assertEquals(4, taskDtoList.size());
        assertEquals(new Long(2), taskDtoList.get(1).getId());
        assertEquals("test4", taskDtoList.get(3).getTitle());
        assertEquals("content1", taskDtoList.get(0).getContent());
    }
}
