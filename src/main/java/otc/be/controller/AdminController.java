package otc.be.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import otc.be.dto.LoginDTO;
import otc.be.entity.Admin;
import otc.be.exception.EmailExistsException;
import otc.be.exception.ForbiddenException;
import otc.be.repository.AdminRepository;

import java.security.Key;

@Controller
public class AdminController {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepository adminRepository;


    public Admin create(Admin admin) throws EmailExistsException {
        if (emailExists(admin.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress:" + admin.getEmail());
        }
        Admin newAdmin = new Admin();
        newAdmin.setEmail(admin.getEmail());
        newAdmin.setRestaurant(admin.getRestaurant());
        //Passwordencoder hashes the password using bcrypt
        newAdmin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(newAdmin);
    }

    private boolean emailExists(final String email) {
        return adminRepository.findByEmail(email) != null;
    }

    public Admin login(LoginDTO loginDTO) {
        if (adminRepository.findByEmail(loginDTO.getEmail()) != null) {
            Admin currAdmin = adminRepository.findByEmail(loginDTO.getEmail());
            //Passwordencoder tries to match typed password with hashed password
            if (passwordEncoder.matches(loginDTO.getPassword(), currAdmin.getPassword())) {
                System.out.println("LOGGED IN");
                String jws = Jwts.builder().setSubject(currAdmin.getRestaurant().getName()).signWith(key).compact();
                currAdmin.setJws(jws);
                System.out.println(jws);
                return currAdmin;
            } else {
                throw new ForbiddenException();
            }
        } else {
            throw new ForbiddenException();
        }
    }

    public boolean isAuthorized(String token) {
        try {
            String result = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
