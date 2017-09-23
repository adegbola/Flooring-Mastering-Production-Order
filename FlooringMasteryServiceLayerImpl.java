/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.service;

import FlooringMastery.dao.FlooringMasteryOrderDao;
import FlooringMastery.dao.FlooringMasteryTaxesDao;
import FlooringMastery.dao.OrderDaoPersistenceException;
import FlooringMastery.dto.Order;
import java.time.LocalDate;
import java.util.List;
import FlooringMastery.dao.FlooringMasteryProductsDao;
import FlooringMastery.dto.Products;
import FlooringMastery.dto.Taxes;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Kelsey
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    FlooringMasteryOrderDao dao;
    FlooringMasteryProductsDao da;
    FlooringMasteryTaxesDao access;
    private String training;
    private static final BigDecimal taxdiv = new BigDecimal(100);

    public FlooringMasteryServiceLayerImpl(FlooringMasteryOrderDao dao, FlooringMasteryProductsDao da, FlooringMasteryTaxesDao access) {
        this.dao = dao;
        this.da = da;
        this.access = access;
    }

    @Override
    public Order CreateOrder(Order order) {
        return dao.addorder(order);
    }

    @Override
    public void EditOrder(String date, Order order) throws OrderDaoPersistenceException {
        Taxes tax = access.gettax(order.getStateName());
        order.setTaxRate(tax.getTax());
        BigDecimal taxR = tax.getTax();
        taxR = taxR.divide(taxdiv).setScale(4, RoundingMode.HALF_UP);
        Products product = da.getProductinfo(order.getProductType());
        order.setCostPerSquareFoot(product.getCostPerSquareFoot());
        order.setLaborCostPerSquareFoot(product.getLaborCostPerSquareFoot());
        order.setMaterialCost(order.getCostPerSquareFoot().multiply(order.getArea()).setScale(3, RoundingMode.HALF_UP));
        order.setLaborCost(order.getLaborCostPerSquareFoot().multiply(order.getArea()).setScale(3, RoundingMode.HALF_UP));
        order.setTotalTax(taxR.multiply(order.getLaborCost().add(order.getMaterialCost().setScale(3, RoundingMode.HALF_UP))));
        order.setTotal1(order.getTotalTax().add(order.getMaterialCost().add(order.getLaborCost().setScale(3, RoundingMode.HALF_UP))));

        dao.commitEdit(order);

    }

    @Override
    public void addOrder(Order order) throws OrderDaoPersistenceException {
        if (order != null) {
            Taxes tax = access.gettax(order.getStateName());
            order.setTaxRate(tax.getTax());
            BigDecimal taxR = tax.getTax();
            taxR = taxR.divide(taxdiv).setScale(4, RoundingMode.HALF_UP);
            Products product = da.getProductinfo(order.getProductType());
            order.setCostPerSquareFoot(product.getCostPerSquareFoot());
            order.setLaborCostPerSquareFoot(product.getLaborCostPerSquareFoot());
            order.setMaterialCost(order.getCostPerSquareFoot().multiply(order.getArea()).setScale(3, RoundingMode.HALF_UP));
            order.setLaborCost(order.getLaborCostPerSquareFoot().multiply(order.getArea()).setScale(3, RoundingMode.HALF_UP));
            order.setTotalTax(taxR.multiply(order.getLaborCost().add(order.getMaterialCost().setScale(3, RoundingMode.HALF_UP))));
            BigDecimal ans=(order.getTotalTax().add(order.getMaterialCost().add(order.getLaborCost().setScale(3, RoundingMode.HALF_UP))));
            order.setTotal1(order.getTotalTax().add(order.getMaterialCost().add(order.getLaborCost())).setScale(3, RoundingMode.HALF_UP));

            dao.addorder(order);
        }

    }

    @Override
    public void RemoveOrder(Order order) throws OrderDaoPersistenceException, FlooringMasteryDataValidationException {
        dao.RemoveOrder(order);

    }

    @Override
    public List<Order> getAllOrders(String dt, int orderNumber) throws OrderDaoPersistenceException,NoOrderException {
        return dao.getAllOrders(dt);
    }

    @Override
    public Order getorder(String dt, int orderNumber) throws NoOrderException {

        Order answer = dao.getOrder(dt, orderNumber);
        if (answer == null) {
            throw new NoOrderException("Order on " + dt + ", number " + orderNumber + " not found");
        }
        return answer;
    }

    @Override
    public int createOrderNumber() throws NoOrderException{
        int orderNumber = dao.orderNum();
        return orderNumber;
    }

    @Override
    public void commitEdit(Order order) {
        dao.commitEdit(order);
    }

    @Override
    public void commitOrder(Order order) {
        dao.commitOrder(order);
    }

    @Override
    public void save() throws OrderDaoPersistenceException {
        dao.saveorder();
        
    }

}
