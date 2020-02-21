package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
@RunWith(SpringRunner.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskController taskController;

    @Test
    public void shouldGetEmptyTasksList() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        when(taskController.getTasks()).thenReturn(taskDtoList);
        //Then&When
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getTasksList() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "Test1", "Test2"));
        when(taskController.getTasks()).thenReturn(taskDtoList);
        //Then&When
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test1")))
                .andExpect(jsonPath("$[0].content", is("Test2")));
    }

    @Test
    public void getTaskTest() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "Test1", "Test2"));
        taskDtoList.add(new TaskDto(2L, "Test1500", "Test2500"));
        when(taskController.getTask(2L)).thenReturn(taskDtoList.get(1));
        //Then&When
        mockMvc.perform(get("/v1/tasks/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.title", is("Test1500")))
                .andExpect(jsonPath("$.content", is("Test2500")));
    }

    @Test
    public void updateTaskTest() throws Exception {
        TaskDto testTaskDto = new TaskDto(5L, "test title", "test content");
        when(taskController.updateTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(testTaskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(testTaskDto);
        //Then@When
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.title", is("test title")))
                .andExpect(jsonPath("$.content", is("test content")));
    }

    @Test
    public void createTaskTest() throws Exception {
        //Given
        TaskDto testTaskDto = new TaskDto(5L, "test title", "test content");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(testTaskDto);
        //When&Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent));
        verify(taskController).createTask(any(TaskDto.class));
    }

    @Test
    public void deleteTaskTest() throws Exception {
        //Given
        //When&Then
        mockMvc.perform(delete("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON));
        verify(taskController).deleteTask(1L);
    }
}
