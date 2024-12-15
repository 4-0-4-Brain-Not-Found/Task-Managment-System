package _4.example.taskManagement.repos;

import _4.example.taskManagement.entities.users.Admin; // Admin entity class
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username); // Admin username ile sorgulama
}
