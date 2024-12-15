package _4.example.taskManagement.security;




import _4.example.taskManagement.entities.users.Admin;
import _4.example.taskManagement.entities.users.User;
import _4.example.taskManagement.repos.AdminRepository;
import _4.example.taskManagement.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Önce admin tablosunda username arıyoruz
        Admin admin = adminRepository.findByUsername(username).orElse(null);

        // Eğer admin bulunamadıysa, normal user tablosunda arıyoruz
        if (admin != null) {
            // Admin bulunduyse, admin'in rolünü atıyoruz
            return new org.springframework.security.core.userdetails.User(
                    admin.getUsername(),
                    admin.getPassword(),
                    AuthorityUtils.createAuthorityList("ROLE_ADMIN")
            );
        }

        // Admin bulunamadı, User tablosunda arıyoruz
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // User bulunmuşsa, User rolünü atıyoruz
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_USER")
        );
    }
}



