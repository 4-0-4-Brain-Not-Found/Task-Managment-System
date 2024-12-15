package _4.example.taskManagement.controller;

import _4.example.taskManagement.dto.LoginDTO;
import _4.example.taskManagement.dto.ReqRes;
import _4.example.taskManagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminAccountController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(@RequestBody LoginDTO loginRequest) {
        ReqRes response = adminService.loginAdmin(loginRequest.getUsername(), loginRequest.getPassword());

        if (response.getStatusCode() == 200) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(response.getStatusCode()).body(response.getError());
    }
}
