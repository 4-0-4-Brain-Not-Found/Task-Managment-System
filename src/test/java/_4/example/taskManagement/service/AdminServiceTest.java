package _4.example.taskManagement.service;

import _4.example.taskManagement.dto.ReqRes;
import _4.example.taskManagement.entities.users.Admin;
import _4.example.taskManagement.repos.AdminRepository;
import _4.example.taskManagement.security.JWTUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private JWTUtils jwtUtils;

    private String username = "admin";
    private String password = "admin123";

    @BeforeEach
    void setUp() {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        Mockito.when(adminRepository.findByUsername(username)).thenReturn(java.util.Optional.of(admin));
    }

    @Test
    void testLoginAdminSuccess() {
        Mockito.when(jwtUtils.generateToken(Mockito.any())).thenReturn("token");
        Mockito.when(jwtUtils.generateRefreshToken(Mockito.any(), Mockito.any())).thenReturn("refreshToken");

        ReqRes response = adminService.loginAdmin(username, password);
        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getToken());
        assertNotNull(response.getRefreshToken());
    }

    @Test
    void testLoginAdminNotFound() {
        Mockito.when(adminRepository.findByUsername("wrongUser")).thenReturn(java.util.Optional.empty());

        ReqRes response = adminService.loginAdmin("wrongUser", password);
        assertEquals(404, response.getStatusCode());
        assertEquals("Admin not found", response.getError());
    }

    @Test
    void testLoginAdminInvalidPassword() {
        ReqRes response = adminService.loginAdmin(username, "wrongPassword");
        assertEquals(401, response.getStatusCode());
        assertEquals("Invalid username or password", response.getError());
    }
}
