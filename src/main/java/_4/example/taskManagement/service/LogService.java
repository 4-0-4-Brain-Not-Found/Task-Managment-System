package _4.example.taskManagement.service;

import _4.example.taskManagement.entities.Log;
import _4.example.taskManagement.repos.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    // Log kaydetmek için genel metot
    public void saveLog(String username, String logType, String message) {
        Log log = new Log();
        log.setUsername(username);
        log.setMessage(message);
        log.setTimestamp(new Date()); // Şu anki tarih/saat
        logRepository.save(log);
    }
}
