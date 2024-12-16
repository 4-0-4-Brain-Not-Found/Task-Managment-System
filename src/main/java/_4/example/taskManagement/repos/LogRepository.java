package _4.example.taskManagement.repos;

import _4.example.taskManagement.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {

    // You can add custom queries here if needed, for example:
    List<Log> findAllByOrderByTimestampDesc();  // To get logs in descending order by timestamp

}
