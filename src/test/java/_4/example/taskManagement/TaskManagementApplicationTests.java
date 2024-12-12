package _4.example.taskManagement;

import _4.example.taskManagement.controller.TaskController;
import _4.example.taskManagement.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class TaskManagementApplicationTests {

	@Autowired
	private TaskController taskController;

	@Autowired
	private TaskService taskService;
	@Test
	void contextLoads() {
		// Context'in yüklendiğini doğrulamak için
		assertThat(taskController).isNotNull();
		assertThat(taskService).isNotNull();
	}

}
