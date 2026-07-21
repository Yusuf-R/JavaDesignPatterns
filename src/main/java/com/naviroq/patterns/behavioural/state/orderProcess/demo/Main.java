package com.naviroq.patterns.behavioural.state.orderProcess.demo;

import com.naviroq.patterns.behavioural.state.orderProcess.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Order> orders = new HashMap<>();

        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║      ORDER STATE MANAGEMENT SYSTEM      ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║  new <id>    - Create new order       ║");
        System.out.println("║  pay <id>    - Pay for order          ║");
        System.out.println("║  ship <id>   - Ship order             ║");
        System.out.println("║  deliver <id>- Mark as delivered      ║");
        System.out.println("║  cancel <id> - Cancel order           ║");
        System.out.println("║  status <id> - Show current state     ║");
        System.out.println("║  list        - Show all orders        ║");
        System.out.println("║  quit        - Exit                   ║");
        System.out.println("╚═══════════════════════════════════════╝\n");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split("\\s+", 2);

            String command = parts[0].toLowerCase();
            String orderId = parts.length > 1 ? parts[1] : "";

            switch (command) {
                case "new" -> {
                    if (orderId.isEmpty()) {
                        System.out.println("  ❌ Provide an order ID");
                        continue;
                    }
                    orders.put(orderId, new Order(orderId));
                    System.out.println("  ✅ Created: " + orders.get(orderId));
                }
                case "pay"      -> execute(orders, orderId, Order::pay);
                case "ship"     -> execute(orders, orderId, Order::ship);
                case "deliver"  -> execute(orders, orderId, Order::deliver);
                case "cancel"   -> execute(orders, orderId, Order::cancel);
                case "status"   -> {
                    Order o = orders.get(orderId);
                    System.out.println(o != null ? "  " + o : "  ❌ Order not found");
                }
                case "list" -> {
                    if (orders.isEmpty()) System.out.println("  No orders");
                    else orders.values().forEach(o -> System.out.println("  " + o));
                }
                case "quit" -> {
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("  ❌ Unknown command");
            }
        }
    }

    private static void execute(Map<String, Order> orders, String id, java.util.function.Consumer<Order> action) {
        Order order = orders.get(id);
        if (order == null) {
            System.out.println("  ❌ Order not found: " + id);
            return;
        }
        System.out.println("  " + order);
        action.accept(order);
        System.out.println();
    }
}
