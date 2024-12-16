package _4.example.taskManagement.service;

import _4.example.taskManagement.dto.TaskDTO;
import _4.example.taskManagement.entities.Task;
import _4.example.taskManagement.entities.users.User;  // Corrected import

import _4.example.taskManagement.repos.TaskRepository;
import _4.example.taskManagement.repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // Tüm görev listesini alma
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> new TaskDTO(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getAssignedUser() != null ? task.getAssignedUser().getId() : null,
                        task.getDueDate(),
                        task.getTaskStatus()
                ))
                .collect(Collectors.toList());
    }

    // ID ile görev detayını alma
    public TaskDTO getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.map(t -> new TaskDTO(
                        t.getId(),
                        t.getTitle(),
                        t.getDescription(),
                        t.getAssignedUser() != null ? t.getAssignedUser().getId() : null,
                        t.getDueDate(),
                        t.getTaskStatus()
                ))
                .orElse(null); // Return null if task not found
    }

    // Yeni görev oluşturma
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setTaskStatus(taskDTO.getTaskStatus());

        // Token'dan kullanıcıyı al
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User assignedUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Assigned user not found"));

        // Görev atanmış kullanıcıyı ayarla
        task.setAssignedUser(assignedUser);

        Task savedTask = taskRepository.save(task);
        return new TaskDTO(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getAssignedUser() != null ? savedTask.getAssignedUser().getId() : null,
                savedTask.getDueDate(),
                savedTask.getTaskStatus()
        );
    }

    // Güncelleme
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
            task.setDueDate(taskDTO.getDueDate());
            task.setTaskStatus(taskDTO.getTaskStatus());

            if (taskDTO.getAssignedUserId() != null) {
                task.setAssignedUser(userRepository.findById(taskDTO.getAssignedUserId()).orElse(null));
            }

            Task updatedTask = taskRepository.save(task);
            return new TaskDTO(
                    updatedTask.getId(),
                    updatedTask.getTitle(),
                    updatedTask.getDescription(),
                    updatedTask.getAssignedUser() != null ? updatedTask.getAssignedUser().getId() : null,
                    updatedTask.getDueDate(),
                    updatedTask.getTaskStatus()
            );
        }
        return null; // Return null if task is not found
    }

    // Mevcut bir görevi silme
    public boolean deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.delete(task.get());
            return true; // Successfully deleted
        }
        return false; // Task not found
    }
}
