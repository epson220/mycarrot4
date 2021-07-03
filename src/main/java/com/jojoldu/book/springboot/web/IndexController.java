package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/isLoggined")
    @ResponseBody
    public String isLoggined(@LoginUser SessionUser user) {

        HashMap result = new HashMap();
        String email ="";

        System.out.println("==============================isLoggined호출=============");
        if(user != null) {
            System.out.println(user);
            System.out.println(user.getName());
            System.out.println(user.getEmail());
            email = user.getEmail();
            result.put("email", email);

            return email;
        }else {
            System.out.println("유저가 null입니다!!!!!!!!!!!!!!");
            return email;
        }
    }

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null) {
            System.out.println("test user");
            System.out.println(user);
            System.out.println(user.getName());
            model.addAttribute("googleName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
