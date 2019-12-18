package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import otc.be.entity.Tag;
import otc.be.repository.TagRepository;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
public class TagController {
    @Autowired
    private TagRepository tagRepository;

    public Tag createTag(Tag tag){
        return tagRepository.save(tag);
    }

    public List<Tag> addTags(List<Tag> results, Tag tag){
        if(!tagRepository.findTagByText(tag.getText()).isPresent()) {
            createTag(tag);
        }
        results.add(tagRepository.findTagByText(tag.getText()).get());
        return results;
    }

    public List<Tag> createTagList(List<Tag> tags){
        Set<String> tagSet = new HashSet<>();
        tags.forEach(tag -> {
            tag.setText(tag.getText().toLowerCase());
            tagSet.add(tag.getText());
        });
        List<Tag> tagList = new LinkedList<>();
        tagSet.forEach(str -> {
            tagList.add(tagRepository.findTagByText(str).get());
        });
        List<Tag> results = new LinkedList<>();
        tagList.forEach(tag -> addTags(results, tag));
        return results;
    }

    public Iterable<Tag> getAllTags(){
        return tagRepository.findAll();
    }
}
