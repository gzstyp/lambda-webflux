package com.fwtai.lambda;

import java.math.BigDecimal;

public final class Book{

    private String title;

    private BigDecimal price;

    private String category;

    public Book(){}

    public Book(final String title,final BigDecimal price,final String category){
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    @Override
    public String toString(){
        return "Book{" + "title='" + title + '\'' + ", price=" + price + ", category='" + category + '\'' + '}';
    }
}