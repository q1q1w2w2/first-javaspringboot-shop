package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor

public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/list")
    String list(Model model){
        List<Item> result = itemRepository.findAll();
        System.out.println(result);
        model.addAttribute("items",result);

        return "list.html";
    }

    @GetMapping("/write")
    String wirte() {
        return "write.html";
    }

    @PostMapping("/add")
    String addPost(String title, String price) {

        title = title.trim();
        if (title.isEmpty() || title == "") {
            System.out.println("title 입력하셈");
            return "redirect:/write";
        } else if (price == "" || !price.chars().allMatch(Character::isDigit)) {
            System.out.println("price 다시입력하셈");
            return "redirect:/write";
        } else {
            int intPrice = Integer.parseInt(price);
            var item = new Item(title, intPrice);
            itemRepository.save(item);
            return "redirect:/list";
        }
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model){
        Optional<Item> result = itemRepository.findById(id);
        if(result.isPresent()){
            Item item = result.get();
            model.addAttribute("item", item);
        } else {
            return "redirect:/list";
        }


        return "detail.html";
    }

}

