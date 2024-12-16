package _4.example.taskManagement.controller;

import _4.example.taskManagement.dto.LogDTO;
import _4.example.taskManagement.repos.LogRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class LogController {

    private final LogRepository logRepository;

    public LogController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")  // Yalnızca ADMIN rolüne sahip kullanıcılar
    @GetMapping("/logs")
    public ResponseEntity<List<LogDTO>> getLogs() {
        List<LogDTO> logDTOs = logRepository.findAll().stream()
                .map(log -> new LogDTO(log.getMessage(), log.getTimestamp(), log.getUsername()))
                .toList();

        if (logDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();  // Return 204 if no logs found
        }

        return ResponseEntity.ok(logDTOs);  // Return 200 with the list of logs
    }
}
