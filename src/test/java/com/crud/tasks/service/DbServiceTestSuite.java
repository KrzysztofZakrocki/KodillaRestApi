package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTestSuite {

    @Autowired
    private DbService service;

    @Test
    public void saveTaskTest() {
        //Given
        Task testTask = new Task(1L, "name1", "content1");
        //When
        service.saveTask(testTask);
        List<Task> taskList = service.getAllTask();
        Long taskId = taskList.get(0).getId();
        //Then
        assertFalse(taskList.isEmpty());
        assertEquals(1, taskList.size());
        assertEquals(taskId, taskList.get(0).getId());
        assertEquals("name1", taskList.get(0).getTitle());

        //CleanUp
        service.deleteTask(taskId);
    }

    @Test
    public void deleteTaskTest() {
        //Given
        Task testTask1 = new Task(1L, "name1", "content1");
        Task testTask2 = new Task(2L, "name2", "content2");
        //When
        service.saveTask(testTask1);
        service.saveTask(testTask2);
        List<Task> TaskList = service.getAllTask();
        int firstTaskTestListSize = TaskList.size();
        Long taskId1 = TaskList.get(0).getId();
        Long taskId2 = TaskList.get(1).getId();
        service.deleteTask(taskId1);
        TaskList = service.getAllTask();

        //Then
        assertEquals(2, firstTaskTestListSize);
        assertFalse(TaskList.isEmpty());
        assertEquals(1, TaskList.size());
        assertEquals(taskId2, TaskList.get(0).getId());
        assertEquals("name2", TaskList.get(0).getTitle());

        //CleanUp
        service.deleteTask(taskId2);
    }
}
