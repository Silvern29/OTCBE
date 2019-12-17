package otc.be.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import otc.be.controller.TagController;
import otc.be.entity.Tag;

@CrossOrigin
@RestController
@RequestMapping(path = "api")
public class TagAPI {
    @Autowired
    private TagController tagController;

    @RequestMapping(method = RequestMethod.GET, path = "/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Tag> getAllTags(){
        return tagController.getAllTags();
    }
}
