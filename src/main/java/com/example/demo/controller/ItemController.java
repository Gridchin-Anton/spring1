package com.example.demo.controller;
import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.JDBCItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ItemController {
    @Autowired
    ItemRepository itemRepository;

//    @GetMapping("/items")
@RequestMapping(value = "/items",
                method=RequestMethod.GET)
    public ResponseEntity<ArrayList<Item>> getAllItems(@RequestParam(required = false) String name) {   //works
        try {
            ArrayList<Item> items = new ArrayList<>();
            if (name == null) {
                items.addAll(itemRepository.findAll());
            } else {
                items.addAll(itemRepository.findByNameContaining(name));
            }
            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//@RequestMapping(value = "/items",
//        params = {"code"},
//        method=RequestMethod.GET)
        @GetMapping("/items/{code}")
    public ResponseEntity<Item> getItemByCode(@PathVariable("code") String code) {   // 500
        Item item = itemRepository.findByCode(code);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/items")
@RequestMapping(value = "/items/create",
                method=RequestMethod.POST)
    public ResponseEntity<String> CreateItem(@RequestBody Item item) {  //works
        try {
            itemRepository.save(new Item(item.getCode(), item.getName(), item.getPrice(), item.getVendorCode(), item.getDateOfProduction(), item.getAmount()));
            return new ResponseEntity<>("Item was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PutMapping("/items/{code}")
@RequestMapping(value = "/items/update",
        params = {"code"},
        method=RequestMethod.PUT)
    public ResponseEntity<String> updateItem(@PathVariable("code") String code, @RequestBody Item item) {   //500
        Item _item = itemRepository.findByCode(code);
        if (_item != null) {
            _item.setCode(code);
            _item.setName(item.getName());
            _item.setPrice(item.getPrice());
            _item.setVendorCode(item.getVendorCode());
            _item.setDateOfProduction(item.getDateOfProduction());
            _item.setAmount(item.getAmount());

            itemRepository.update(_item);
            return new ResponseEntity<>("Item was updated successfully. ", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find item with code=" + code, HttpStatus.NOT_FOUND);
        }
    }

//    @DeleteMapping("/items/{code}")
@RequestMapping(value = "/items/delete",
        params = {"code"},
        method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteItem(@PathVariable String code) {
        try {
            int result = itemRepository.deleteByCode(code);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find item with code=" + code, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Item deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete item", HttpStatus.INTERNAL_SERVER_ERROR);


        }
    }

//    @DeleteMapping("/items")
@DeleteMapping (value = "/items")
    public ResponseEntity<String> deleteAllItems() {    // works
        try {
            int numsRows = itemRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numsRows + " items successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot not delete items", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/items/name")
//@RequestMapping(value = "/items",
//                params = {"name"},
//                method=RequestMethod.GET)
//    public ResponseEntity<List<Item>> findByNameContaining(@PathVariable String name){
//        try{
//            List<Item> _items = itemRepository.findByNameContaining(name);
//            if (_items.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }   else {
//                return new ResponseEntity<>(_items, HttpStatus.OK);
//            }
//        }    catch (Exception e) {
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }





}
