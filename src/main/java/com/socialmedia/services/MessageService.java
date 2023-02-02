package com.socialmedia.services;

import com.socialmedia.domain.Message;
import com.socialmedia.domain.User;
import com.socialmedia.repos.MessageRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
@AllArgsConstructor
@Service
public class MessageService {
    private final MessageRepo messageRepo;

    public void add(
            User user,
            String text,
            String tag,
            Model model
    ){
        Message message = new Message();
        message.setAuthor(user);
        message.setText(text);
        message.setTag(tag);

        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
    }
    public void filter(
            String filter,
            Model model
    ){
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()){
            messages = messageRepo.findByTag(filter);
        }else {
            messages = messageRepo.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
    }
}
