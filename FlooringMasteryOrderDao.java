/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.dao;

import FlooringMastery.dto.Order;
import FlooringMastery.service.FlooringMasteryDataValidationException;
import FlooringMastery.service.NoOrderException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Kelsey
 */
public interface FlooringMasteryOrderDao {

    Order addorder(Order neworder);

    List<Order> getAllOrders(String dt) throws NoOrderException;

    Order getOrder(String dt, int orderNumber) throws NoOrderException;

    void RemoveOrder(Order order) throws FlooringMasteryDataValidationException;

    public boolean containskey(Object key);

    void commitOrder(Order order);

    public void commitEdit(Order order);

    public int orderNum() throws NoOrderException;

    void saveorder() throws OrderDaoPersistenceException;
}
