package otc.be.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import otc.be.controller.AnfrageController;
import otc.be.entity.AnfrageDTO;

import java.util.LinkedList;


@CrossOrigin
@RestController
@RequestMapping(path = "api")
public class AnfrageAPI {

    @Autowired
    private AnfrageController anfrageController;

    @RequestMapping(method = RequestMethod.POST, path = "/anfrage", produces = MediaType.APPLICATION_JSON_VALUE)
    public LinkedList<AnfrageDTO> create(@RequestBody AnfrageDTO anfrageDTO) {
        return anfrageController.buildList(anfrageDTO);
    }

}
