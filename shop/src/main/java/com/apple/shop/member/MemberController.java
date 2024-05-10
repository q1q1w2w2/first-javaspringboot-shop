package com.apple.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signup(Authentication auth){
        if(auth!=null && auth.isAuthenticated()){
            return "redirect:/list";
        }
        return "signup.html";
    }

    @PostMapping("/signup")
    public String signup(String username, String password, String displayName) {
        String hash = passwordEncoder.encode(password);
        memberService.saveMember(username, hash, displayName);
        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login(){
        var result = memberRepository.findByUsername("test1");
        System.out.println(result.get().getDisplayName());
        return "login.html";
    }

//    @PostMapping("/login")
//    String login(String username, String password){
//        return "redirect:/list";
//    }

    @GetMapping("my-page")
    public String myPage(Authentication auth, Model model){
//        CustomUser user =(CustomUser) auth.getPrincipal();
//        System.out.println(user);

        if(auth.isAuthenticated()){
            model.addAttribute("username", auth.getName());
            return "mypage.html";
        }
        return "login.html";
    }
}
