package com.example.servingwebcontent;

import com.example.servingwebcontent.domain.Message;
import com.example.servingwebcontent.repos.MessageRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;

@Controller
public class GreetingContr {
    @Autowired
    private MessageRep messageRep;

    @GetMapping("/greeting")
    public String greeting (
            @RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name",name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model){
        Iterable<Message> messages = messageRep.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping
    public  String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);
        messageRep.save(message);

        Iterable<Message> messages = messageRep.findAll();
        model.put("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRep.findByTag(filter);
        } else {
            messages = messageRep.findAll();
        }

        model.put("messages", messages);

        return "main";
    }


}