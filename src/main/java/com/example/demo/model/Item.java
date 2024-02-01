package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Item {

    private String code;
    private String name;
    private double price;
    private long vendorCode;
    private LocalDate dateOfProduction;
    private int amount;


    public Item(String code, String name, double price, long vendorCode, LocalDate dateOfProduction, int amount) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.vendorCode = vendorCode;
        this.dateOfProduction = dateOfProduction;
        this.amount = amount;
    }

    public Item(String code, String name, double price, long vendorCode, LocalDate dateOfProduction) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.vendorCode = vendorCode;
        this.dateOfProduction = dateOfProduction;
    }

    public Item(String code, String name, long vendorCode) {
        this.code = code;
        this.name = name;
        this.vendorCode = vendorCode;
    }

    public Item() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(long vendorCode) {
        this.vendorCode = vendorCode;
    }

    public LocalDate getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(LocalDate dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", vendorCode=" + vendorCode +
                ", dateOfProduction=" + dateOfProduction +
                ", amount=" + amount +
                '}';
    }
}
