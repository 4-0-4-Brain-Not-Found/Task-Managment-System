package _4.example.taskManagement.service;

import _4.example.taskManagement.dto.CommentDTO;
import _4.example.taskManagement.entities.Comment;
import _4.example.taskManagement.entities.Task;
import _4.example.taskManagement.entities.users.User;
import _4.example.taskManagement.repos.CommentRepository;
import _4.example.taskManagement.repos.TaskRepository;
import _4.example.taskManagement.repos.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, TaskRepository taskRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // Tüm yorumları getirme
    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Göreve ait yorumları getirme
    public List<CommentDTO> getCommentsByTaskId(Long taskId) {
        return commentRepository.findByTaskId(taskId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Kullanıcıya ait yorumları getirme
    public List<CommentDTO> getCommentsByUserId(Long userId) {
        return commentRepository.findByAssignedUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Yorum oluşturma
    public CommentDTO createComment(CommentDTO commentDTO) {
        throw new UnsupportedOperationException("createComment method not implemented yet.");
    }

    // Yorum güncelleme
    public CommentDTO updateComment(Long id, CommentDTO commentDTO) {
        throw new UnsupportedOperationException();
    }

    // Yorum silme
    public void deleteComment(Long id) {
        throw new UnsupportedOperationException();
    }

    // Entity -> DTO
    private CommentDTO convertToDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getTask().getId(),
                comment.getAssignedUser().getId()
        );
    }

    // DTO -> Entity
    private Comment convertToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());

        Task task = taskRepository.findById(commentDTO.getTaskId()).orElse(null);
        comment.setTask(task);

        User user = userRepository.findById(commentDTO.getAssignedUserId()).orElse(null);
        comment.setAssignedUser(user);

        return comment;
    }

}
