/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.dao;

import FlooringMastery.dto.Order;
import FlooringMastery.service.FlooringMasteryDataValidationException;
import FlooringMastery.service.NoOrderException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 *
 * @author Kelsey
 */
public class FlooringMasteryDaoOrderFileImpl implements FlooringMasteryOrderDao {

    FlooringMasteryProductsDao productDAO;
    FlooringMasteryTaxesDao stateDAO;

    public FlooringMasteryDaoOrderFileImpl(String ProgramMode) {
        this.ProgramMode = ProgramMode;
    }
    String ProgramMode;
    private Map<String, List<Order>> orders = new HashMap<>();
    static LocalDateTime time = LocalDateTime.now();
    static DateTimeFormatter format = DateTimeFormatter.ofPattern("MMddyyyy");
    static String datetime = time.format(format);
    public static String Order_FILE = "Order_" + datetime + ".txt";
    public static final String DELIMITER = ",";
    Integer orderNumber = 1;

    private String getCurrentDate() {
        time = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMddyyyy");
        return time.format(format);
    }

    private void loadOrder(String dt) throws NoOrderException {
        String date = "Order_" + dt + ".txt";
        Scanner scanner;
        orders = new HashMap<>();

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(date)));
        } catch (FileNotFoundException e) {
            throw new NoOrderException("Error");
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Order currentOrder = new Order();
            currentOrder.setOrderDate(dt);
            currentOrder.setOrderNumber(Integer.parseInt(currentTokens[1]));
            currentOrder.setCustomerName(currentTokens[2]);
            currentOrder.setStateName(currentTokens[3]);
            currentOrder.setTaxRate(new BigDecimal(currentTokens[4]));
            currentOrder.setProductType(currentTokens[5]);
            currentOrder.setArea(new BigDecimal(currentTokens[6]));
            currentOrder.setCostPerSquareFoot(new BigDecimal(currentTokens[7]));
            currentOrder.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[8]));
            currentOrder.setMaterialCost(new BigDecimal(currentTokens[9]));
            currentOrder.setLaborCost(new BigDecimal(currentTokens[10]));
            currentOrder.settotalTax(new BigDecimal(currentTokens[11]));
            currentOrder.setTotal1(new BigDecimal(currentTokens[12]));

            if (!orders.containsKey(dt)) {
                orders.put(dt, new ArrayList<>());
            }

            orders.get(dt).add(currentOrder);
        }
        // close scanner
        scanner.close();
    }

    private void writeAllOrders() throws Exception {
        for (Map.Entry<String, List<Order>> entry : orders.entrySet()) {
            writeOrder(entry.getValue());
        }
    }

    private void writeOrder(List<Order> orderList) throws Exception {
        if (ProgramMode.equals("train")) {

        } else {
            PrintWriter out = new PrintWriter(new FileWriter("Order_" + getCurrentDate() + ".txt"));

            for (Order currentorder : orderList) {
                out.println(currentorder.getOrderDate() + DELIMITER
                        + currentorder.getOrderNumber() + DELIMITER
                        + currentorder.getCustomerName() + DELIMITER
                        + currentorder.getStateName() + DELIMITER
                        + currentorder.getTaxRate() + DELIMITER
                        + currentorder.getProductType() + DELIMITER
                        + currentorder.getArea() + DELIMITER
                        + currentorder.getCostPerSquareFoot() + DELIMITER
                        + currentorder.getLaborCostPerSquareFoot() + DELIMITER
                        + currentorder.getMaterialCost() + DELIMITER
                        + currentorder.getLaborCost() + DELIMITER
                        + currentorder.getTotalTax() + DELIMITER
                        + currentorder.getTotal1());
                // force PrintWriter to write line to the file out.flush(); } //

            }
            out.close();
            out.flush();
        }

    }

    @Override
    public List<Order> getAllOrders(String dt) throws NoOrderException {
        loadOrder(dt);
        return orders.get(dt);
    }

    @Override
    public Order getOrder(String dt, int orderNumber) throws NoOrderException {
        loadOrder(dt);
        List<Order> dayOrders = orders.get(dt);
        if (dayOrders != null) {
            return dayOrders.get(orderNumber);
        }

        return null;
    }

    @Override
    public Order addorder(Order order) {
        try {
            getAllOrders(order.getOrderDate());
        } catch (NoOrderException e) {
            e.printStackTrace();
        }

        if (!orders.containsKey(order.getOrderDate())) {
            orders.put(order.getOrderDate(), new ArrayList<>());
        }

        orders.get(order.getOrderDate()).add(order);

        try {
            writeAllOrders();
        } catch (Exception ex) {

        }

        return order;
    }

    @Override
    public int orderNum() throws NoOrderException {
        LocalDate time = LocalDate.now();
        String sc = time.toString();
        int orderNumber;
        try {
            loadOrder(sc);
        } catch (Exception e) {
            orderNumber = 0;
            return orderNumber+1;

        }
        //return orderNumber+1;
    
    
        orderNumber = getAllOrders(sc).size();
        return orderNumber + 1;

    }

    @Override
    public boolean containskey(Object key) {
        if (orders.containsKey(key)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void commitOrder(Order order) {

        orderNumber++;
        String str = "Order_" + order.getOrderNumber() + ".txt";
        List<Order> list = new ArrayList<Order>();
        orders.put(order.getCustomerName(), list);

    }

    @Override
    public void commitEdit(Order order) {

        if (orders.containsKey(order.getOrderDate())) {
            List<Order> floor = orders.get(order.getOrderDate());
            int store = 0;
            for (int i = 0; i < floor.size(); i++) {
                Order sc = floor.get(i);
                if (sc.getOrderNumber() == order.getOrderNumber()) {
                    store = i;
                }
            }
            floor.remove(store);
            floor.add(store, order);

        }

    }

    @Override
    public void RemoveOrder(Order order) throws FlooringMasteryDataValidationException {
        if (orders.containsKey(order.getOrderDate())) {
            List<Order> floor = orders.get(order.getOrderDate());
            int store = 0;
            for (int i = 0; i < floor.size(); i++) {
                Order sc = floor.get(i);
                if (sc.getOrderNumber() == order.getOrderNumber()) {
                    store = i;
                }
            }
            floor.remove(order);
            //floor.remove(store);

        }

    }

    @Override
    public void saveorder() throws OrderDaoPersistenceException {
        try {
            writeAllOrders();
        } catch (Exception ex) {

        }
    }
}
