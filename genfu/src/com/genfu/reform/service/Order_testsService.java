package com.genfu.reform.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genfu.reform.model.Order_test;

public class Order_testsService {

    private static Map<String,Order_test> orders = new HashMap<String,Order_test>();
    private static int nextId = 6;
    static {
        orders.put("3", new Order_test("3", "Bob", 33));
        orders.put("4", new Order_test("4", "Sarah", 44));
        orders.put("5", new Order_test("5", "Jim", 66));
    }

    public Order_test get(String id) {
    	System.out.println(id + "=======get=======");
        return orders.get(id);
    }

    public List<Order_test> getAll() {
        return new ArrayList<Order_test>(orders.values());
    }

    public void save(Order_test order) {
        if (order.getId() == null) {
            order.setId(String.valueOf(nextId++));
        }

        orders.put(order.getId(), order);
    }

    public void remove(String id) {
        orders.remove(id);
    }

}
