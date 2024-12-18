package _4.example.taskManagement.repos;

import _4.example.taskManagement.entities.Comment;
import _4.example.taskManagement.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository  extends JpaRepository<Comment,Long> {
    List<Comment> findByTaskId(Long taskId);
    List<Comment> findByAssignedUserId(Long userId);
}
