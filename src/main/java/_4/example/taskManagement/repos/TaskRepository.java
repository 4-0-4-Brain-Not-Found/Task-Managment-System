package _4.example.taskManagement.repos;

import _4.example.taskManagement.entities.Task;
import _4.example.taskManagement.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository  extends JpaRepository<Task,Long> {

    List<Task> findByTitle(String title);
    Optional<Task> findById(Long id);
    List<Task> findByAssignedUserId(Long userId);
}
