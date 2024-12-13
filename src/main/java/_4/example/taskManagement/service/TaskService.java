package _4.example.taskManagement.service;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import _4.example.taskManagement.dto.TaskDTO;
import _4.example.taskManagement.entities.Task;
import _4.example.taskManagement.entities.users.User;
import _4.example.taskManagement.exceptions.TaskNotFoundException;
import _4.example.taskManagement.repos.TaskRepository;
import _4.example.taskManagement.repos.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    // constructor-based dependency injection @AUTOWIRED
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }
    // CRUD islemleri
    //Tüm görev listesini alma
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    //ID ile görev detayını alma
    public TaskDTO getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return convertToDTO(task.get());
        } else {
            throw new TaskNotFoundException("Task not found with ID: " + id);
        }
    }
    //Yeni görev oluşturma
    public TaskDTO createTask(TaskDTO taskDTO) {
         Task task = convertToEntity(taskDTO);
         Task savedTask = taskRepository.save(task);
         return convertToDTO(savedTask);
    }
    //Güncelleme
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        throw new UnsupportedOperationException();
    }
    //Mevcut bir görevi silme
    public void deleteTask(Long id) {
        throw new UnsupportedOperationException();
    }
    // DTO -> Entity
    private Task convertToEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setTaskStatus(taskDTO.getTaskStatus());
        User user = userRepository.findById(taskDTO.getAssignedUserId()).orElse(null);
        task.setAssignedUser(user);
        return task;
    }
    // Entity -> DTO
    private TaskDTO convertToDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getAssignedUser().getId(),
                task.getDueDate(),
                task.getTaskStatus(),
                new HashSet<>()
        );
    }}

