package _4.example.taskManagement.service;

import _4.example.taskManagement.dto.ReqRes;
import _4.example.taskManagement.entities.users.User;
import _4.example.taskManagement.enums.Role;
import _4.example.taskManagement.repos.UserRepository;
import _4.example.taskManagement.security.JWTUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTUtils jwtUtils;

    private ReqRes validRegisterRequest;

    @BeforeEach
    void setUp() {
        validRegisterRequest = new ReqRes();
        validRegisterRequest.setUsername("irem");
        validRegisterRequest.setEmail("irem@example.com");
        validRegisterRequest.setPassword("password");
        validRegisterRequest.setPhoneNumber("1234567890");
        validRegisterRequest.setRole(Role.ROLE_USER);
    }

    @Test
    void testRegisterUserSuccess() {
        // Mock the repository calls to simulate the absence of the username and email
        Mockito.when(userRepository.findByUsername("irem")).thenReturn(java.util.Optional.empty());
        Mockito.when(userRepository.findByEmail("irem@example.com")).thenReturn(java.util.Optional.empty());

        // Mock the save operation
        User newUser = new User();
        newUser.setUsername("irem");
        newUser.setEmail("irem@example.com");
        newUser.setPassword("password");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(newUser);

        // Test the registerUser method
        ReqRes response = accountService.registerUser(validRegisterRequest);

        // Assert the expected outcomes
        assertEquals(200, response.getStatusCode());
        assertEquals("User Registered Successfully", response.getMessage());
    }

    @Test
    void testRegisterUserFailureDueToDuplicateUsername() {
        // Mock the repository to simulate a duplicate username scenario
        Mockito.when(userRepository.findByUsername("irem")).thenReturn(java.util.Optional.of(new User()));

        // Test the registerUser method when the username is taken
        ReqRes response = accountService.registerUser(validRegisterRequest);

        // Assert the expected outcomes
        assertEquals(400, response.getStatusCode());
        assertEquals("Username is already taken", response.getError());
    }

    @Test
    void testRegisterUserFailureDueToDuplicateEmail() {
        // Mock the repository to simulate a duplicate email scenario
        Mockito.when(userRepository.findByUsername("irem")).thenReturn(java.util.Optional.empty());
        Mockito.when(userRepository.findByEmail("irem@example.com")).thenReturn(java.util.Optional.of(new User()));

        // Test the registerUser method when the email is taken
        ReqRes response = accountService.registerUser(validRegisterRequest);

        // Assert the expected outcomes
        assertEquals(400, response.getStatusCode());
        assertEquals("Email is already taken", response.getError());
    }
}
