package otc.be.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import otc.be.controller.AdminController;
import otc.be.controller.AuthorizationController;
import otc.be.dto.LoginDTO;
import otc.be.entity.Admin;
import otc.be.exception.EmailExistsException;

@CrossOrigin
@RestController
@RequestMapping(path = "api")
public class AdminAPI {

    @Autowired
    private AdminController adminController;

    @RequestMapping(method = RequestMethod.POST, path = "/admins/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Admin login(@RequestBody LoginDTO loginDTO) {
        return adminController.login(loginDTO);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/admins", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Admin create(@RequestBody Admin admin) throws EmailExistsException {
        adminController.create(admin);
        return admin;
    }
}
