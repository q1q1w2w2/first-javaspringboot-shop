package com.apple.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor

public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/list")
    String list(Model model){
        model.addAttribute("items",itemService.findItem());
        return "list.html";
    }

    @GetMapping("/write")
    String write(Authentication auth) {
        if(auth!=null){
            return "write.html";
        }
        return "redirect:/list";
    }

    @PostMapping("/add")
    String addPost(String title, Integer price, Authentication auth) {
        if(auth!=null && auth.isAuthenticated()){
            String username = auth.getName();
            itemService.saveItem(title, price, username);
        }
        itemService.saveItem(title, price, null);
        return "redirect:/list";


//        title = title.trim();
//        if (title.isEmpty() || title == "") {
//            System.out.println("title 입력하셈");
//            return "redirect:/write";
//        } else if (price == "" || !price.chars().allMatch(Character::isDigit)) {
//            System.out.println("price 다시입력하셈");
//            return "redirect:/write";
//        } else {
//            int intPrice = Integer.parseInt(price);
//            var item = new Item(title, intPrice);
//            itemRepository.save(item);
//            return "redirect:/list";
//        }
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model){
        Optional<Item> result = itemRepository.findById(id);
        if(result.isPresent()){
            Item item = result.get();
            model.addAttribute("item", item);
            return "detail.html";
        } else {
            return "redirect:/list";
        }
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Long id, Model model){
        Optional<Item> result = itemRepository.findById(id);
        if(result.isPresent()){
            Item item = result.get();
            model.addAttribute("item", item);
            return "edit.html";
        }else {
            return "redirect:/list";
        }
    }

    @PostMapping("/editPost")
    String editPost(Long id, String title, Integer price){
        if(price<0){
            System.out.println("가격은 양수로");
            return "redirect:/edit/" + id;
        }else{
            itemService.updateItem(id, title, price);
            return "redirect:/list";
        }
    }

    @PostMapping("/test1")
    String test1(@RequestParam String name, Integer age){
        System.out.println("ajax요청 들어옴");
        System.out.println(name + age);
        return "redirect:/list";
    }

    @PostMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestBody Map<String, Long> body){
       // System.out.println(body.get("id"));
        Long id = body.get("id");
        itemRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

    @DeleteMapping("/deletePost1")
    public ResponseEntity<String> deletePost1(@RequestParam Long id){
        itemRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

    @GetMapping("/test2")
    String hashing(){
        var result = new BCryptPasswordEncoder().encode("문자~~~");
        System.out.println(result);
        return "redirect:/list";
    }
}

