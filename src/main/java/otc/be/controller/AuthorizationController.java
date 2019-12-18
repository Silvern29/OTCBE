package otc.be.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import otc.be.dto.AuthorizedUserDTO;
import otc.be.dto.LoginDTO;
import otc.be.entity.User;
import otc.be.exception.ForbiddenException;
import otc.be.repository.UserRepository;

import java.security.Key;

@Controller
public class AuthorizationController {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthorizedUserDTO login(LoginDTO loginDTO) {
        if (repository.findByEmail(loginDTO.getEmail()) != null) {
            User currUser = repository.findByEmail(loginDTO.getEmail());
            //Passwordencoder tries to match typed password with hashed password
            if (passwordEncoder.matches(loginDTO.getPassword(), currUser.getPassword())) {
                System.out.println("LOGGED IN");
                String jws = Jwts.builder().setSubject(currUser.getFirstName()).signWith(key).compact();
                AuthorizedUserDTO authorizedUserDTO = new AuthorizedUserDTO(currUser);
                authorizedUserDTO.setJws(jws);
                return authorizedUserDTO;
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
