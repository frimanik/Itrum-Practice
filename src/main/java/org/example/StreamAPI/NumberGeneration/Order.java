package org.example.StreamAPI.NumberGeneration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Order {
    private String product;
    private double cost;

    public Order(String product, double cost) {
        this.product = product;
        this.cost = cost;
    }

    public String getProduct() {
        return product;
    }

    public double getCost() {
        return cost;
    }
}

class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );
        // Лист уникальных продуктов
        List<String> distinctProducts = orders.stream()
                .map(Order::getProduct)
                .distinct().toList();

        System.out.println("List of different products and costs");


        // Группировка по продуктам
        Map<String, List<Order>> groupedByProduct = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct));

        System.out.println("Orders grouped by product");
        System.out.println(groupedByProduct);

        // Общая стоимость каждого продукта
        Map<String, Double> totalCostByProduct = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCost)));

        System.out.println("Total cost of orders for each product");
        System.out.println(totalCostByProduct);

        // Сортировка в убывающем порядке
        List<String> sortedProductsByCost = totalCostByProduct.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("Products sorted by cost (from high to low)");
        System.out.println(sortedProductsByCost);

        // Выбор 3х самых дорогих
        List<String> top3ExpensiveProducts = sortedProductsByCost.stream()
                .collect(Collectors.toList());

        System.out.println("Top 3 most expensive products");
        System.out.println(top3ExpensiveProducts);

        //Вывод 3х самых дорогих и их стоимость
        Map<String, Double> top3ExpensiveProductsCost = totalCostByProduct.entrySet().stream()
                .filter(entry -> top3ExpensiveProducts.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println("Total cost of top 3 most expensive products");
        System.out.println(top3ExpensiveProductsCost);

    }
}