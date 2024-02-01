package com.example.demo.repository;
import com.example.demo.model.Item;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
public interface ItemRepository {

    int save(Item item);
    int update(Item item);
    Item findByCode(String code);
    int deleteByCode(String code);
    int deleteAll();
    List<Item> findAll();
    List<Item> findByNameContaining(String name);

}
