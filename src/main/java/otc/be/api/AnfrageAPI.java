package otc.be.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import otc.be.controller.AnfrageController;
import otc.be.entity.Anfrage;

import java.util.LinkedList;


@CrossOrigin
@RestController
@RequestMapping(path = "api")
public class AnfrageAPI {

    @Autowired
    private AnfrageController anfrageController;

    @RequestMapping(method = RequestMethod.POST, path = "/anfrage", produces = MediaType.APPLICATION_JSON_VALUE)
    public LinkedList<Anfrage> create(@RequestBody Anfrage anfrage) {
        LinkedList<Anfrage> antwortliste = anfrageController.step1(anfrage);
        return antwortliste;
    }

}
