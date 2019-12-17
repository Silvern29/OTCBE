package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import otc.be.entity.Tag;
import otc.be.repository.TagRepository;

import java.util.Optional;
import java.util.Set;

@Controller
public class TagController {
    @Autowired
    private TagRepository tagRepository;

    public Tag createTag(Tag tag){
        if(!tagRepository.findTagByText(tag.getText()).isPresent()){
            tagRepository.save(tag);
            return tag;
        }
        return null;
    }

    public Set<Tag> createTags(Set<Tag> tags){
        tags.forEach(tag -> tag.setText(tag.getText().toLowerCase()));
        tags.forEach(tag -> {
            if(createTag(tag) == null) {
                tags.remove(tag);
            }
        });
        return tags;
    }

    public Iterable<Tag> getAllTags(){
        return tagRepository.findAll();
    }
}
