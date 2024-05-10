package com.apple.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(String title, Integer price, String username){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        item.setUsername(username);
        itemRepository.save(item);
    }

    public List<Item> findItem(){
        try{
            return itemRepository.findAll();
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "에러발생");
        }
    }

    public void updateItem(Long id, String title, Integer price){
        Item item = new Item();
        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

}
