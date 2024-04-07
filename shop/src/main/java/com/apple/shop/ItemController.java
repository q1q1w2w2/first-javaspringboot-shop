package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

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
    String wirte() {
        return "write.html";
    }

    @PostMapping("/add")
    String addPost(String title, Integer price) {
        itemService.saveItem(title, price);
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


//        try{
//            Optional<Item> result = itemRepository.findById(id);
//            if(result.isPresent()){
//                Item item = result.get();
//                model.addAttribute("item", item);
//                return "detail.html";
//            } else {
//                return "redirect:/list";
//            }
//        } catch (Exception e){
//            System.out.println(e.getMessage());
////            return ResponseEntity.status(400).body("에러남");
//            return "redirect:/list";
//        }
    }
}

