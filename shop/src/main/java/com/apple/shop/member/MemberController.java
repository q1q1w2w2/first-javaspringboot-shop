package com.apple.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    String signup(){
        return "signup.html";
    }

    @PostMapping("/signup")
    String signup(String username, String password, String displayName) {
        String hash = passwordEncoder.encode(password);
        memberService.saveMember(username, hash, displayName);
        return "redirect:/list";
    }

    @GetMapping("/login")
    String login(){
        var result = memberRepository.findByUsername("test1");
        System.out.println(result.get().getDisplayName());
        return "login.html";
    }

//    @PostMapping("/login")
//    String login(String username, String password){
//        return "redirect:/list";
//    }
}
