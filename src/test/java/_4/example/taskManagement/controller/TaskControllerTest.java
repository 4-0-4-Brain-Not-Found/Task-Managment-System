
package _4.example.taskManagement.controller;

import _4.example.taskManagement.dto.TaskDTO;
import _4.example.taskManagement.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;


import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private TaskDTO taskDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskDTO = new TaskDTO(1L, "Test Task", "Test Description", 1L, new java.util.Date(), null);
    }

    @Test
    void testGetAllTasks() {
        when(taskService.getAllTasks()).thenReturn(List.of(taskDTO));

        var tasks = taskController.getAllTasks();

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
    }

    @Test
    void testGetTaskById() {
        when(taskService.getTaskById(1L)).thenReturn(taskDTO);

        var response = taskController.getTaskById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testCreateTask() {
        when(taskService.createTask(taskDTO)).thenReturn(taskDTO);

        var response = taskController.createTask(taskDTO);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testUpdateTask() {
        when(taskService.updateTask(1L, taskDTO)).thenReturn(taskDTO);

        var response = taskController.updateTask(1L, taskDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testDeleteTask() {
        Mockito.when(this.taskService.deleteTask(1L)).thenReturn(true); // veya uygun dönüş tipi neyse

        ResponseEntity<Void> response = this.taskController.deleteTask(1L);

        Assertions.assertEquals(204, response.getStatusCodeValue());
    }

}