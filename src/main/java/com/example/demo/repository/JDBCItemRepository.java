package com.example.demo.repository;

import com.example.demo.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcClientAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.Array;
import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;
@Repository
public class JDBCItemRepository implements ItemRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Item item) {
        return jdbcTemplate.update("INSERT INTO items (code, name, price, vendorCode, dateOfProduction, amount) VALUES(?,?,?,?,?,?)",
                new Object[]{item.getCode(), item.getName(), item.getPrice(), item.getVendorCode(), item.getDateOfProduction(), item.getAmount()});
    }

    @Override
    public int update(Item item) {
        return jdbcTemplate.update("UPDATE items SET code=?, name=?, price=?, vendorCode=?, dateOfProduction=?, amount=?",
                new Object[]{item.getCode(), item.getName(), item.getPrice(), item.getVendorCode(), item.getDateOfProduction(), item.getAmount()});
    }

    @Override
    public Item findByCode(String code) {
        try{
//            Item item = jdbcTemplate.queryForObject("SELECT * FROM items WHERE code=?",
//                    BeanPropertyRowMapper.newInstance(Item.class), code);
//            return item;
            String q = "SELECT * from items WHERE name LIKE '%" + code +"%' LIMIT 1";
            return jdbcTemplate.queryForObject(q, BeanPropertyRowMapper.newInstance(Item.class));
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteByCode(String code) {
        return jdbcTemplate.update("DELETE FROM items WHERE code=?", code);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from items");
    }

    @Override
    public List<Item> findAll() {
        return jdbcTemplate.query("SELECT * from items", BeanPropertyRowMapper.newInstance(Item.class));
    }

    @Override
    public List<Item> findByNameContaining(String name) {
        String q = "SELECT * from items WHERE name LIKE '%" + name +"%'";
        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Item.class));
    }



}
