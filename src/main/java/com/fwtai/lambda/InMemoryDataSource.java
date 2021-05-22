package com.fwtai.lambda;

import java.math.BigDecimal;

//模拟数据源
public final class InMemoryDataSource{

    public static final Book[] kooks = new Book[]{
        new Book("CS Book #1",BigDecimal.valueOf(19.99),"CS"),
        new Book("CS Book #2",BigDecimal.valueOf(9.99),"CS"),
        new Book("CS Book #3",BigDecimal.valueOf(39.99),"CS"),

        new Book("Children Book #1",BigDecimal.valueOf(20.99),"CHILDREN"),
        new Book("Children Book #2",BigDecimal.valueOf(25.99),"CHILDREN"),
        new Book("Children Book #3",BigDecimal.valueOf(24.99),"CHILDREN"),
        new Book("Children Book #4",BigDecimal.valueOf(10.99),"CHILDREN"),

        new Book("Novel #1",BigDecimal.valueOf(1.99),"NOVEL"),
        new Book("Novel #2",BigDecimal.valueOf(12.99),"NOVEL"),
        new Book("Novel #3",BigDecimal.valueOf(8.99),"NOVEL"),
        new Book("Novel #4",BigDecimal.valueOf(2.99),"NOVEL")
    };
}