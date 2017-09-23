/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.service;

import FlooringMastery.dao.OrderDaoPersistenceException;
import FlooringMastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Kelsey
 */
public interface FlooringMasteryServiceLayer {

    
    Order CreateOrder(Order order);

    Order getorder(String dt, int orderNumber) throws NoOrderException;

    void addOrder(Order order) throws OrderDaoPersistenceException;

    int createOrderNumber()throws NoOrderException;

    public void save() throws OrderDaoPersistenceException;

    void EditOrder(String date, Order order) throws OrderDaoPersistenceException;

    void RemoveOrder(Order order) throws OrderDaoPersistenceException, FlooringMasteryDataValidationException;

    List<Order> getAllOrders(String dt, int orderNumber) throws OrderDaoPersistenceException,NoOrderException;

    void commitEdit(Order order);

    void commitOrder(Order order);
}
