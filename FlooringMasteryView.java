/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.ui;

import FlooringMastery.dto.Order;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kelsey
 */
public class FlooringMasteryView {

    public FlooringMasteryView(UserIO myio) {
        this.io = myio;
    }
    Date today = new Date();
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

    UserIO io = new UserIOConsoleImpl();

    public void displayRemoveOrderBanner() {
        io.print("=== Remove Order ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Order successfully removed. Please hit enter to continue.");
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Save Current Work");
        io.print("6. Exit");
        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public Order getNewOrderInfo(int orderNumber) {
        String customer = io.readString("Please enter Customer Name");
        String state = io.readString("Please enter your state abbrevation");
        String Area = io.readString("Please enter the area");
        String ProductType = io.readString("Please enter the product type");

        Order currentOrder = new Order();

        currentOrder.setCustomerName(customer);
       
        currentOrder.setOrderDate(LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy")));
        currentOrder.setOrderNumber(orderNumber);
        currentOrder.setLaborCost(new BigDecimal("1"));
        currentOrder.setLaborCostPerSquareFoot(new BigDecimal("1"));
        currentOrder.setArea(new BigDecimal("1"));
        currentOrder.setMaterialCost(new BigDecimal("1"));
        currentOrder.setProductType(ProductType);
        currentOrder.setStateAbbreviation(state);
        currentOrder.setStateName(state);
        currentOrder.setTaxRate(new BigDecimal("1"));
        currentOrder.setTotal1(new BigDecimal("1"));

        currentOrder.setStateAbbreviation(state);

        

        return currentOrder;

    }

    public void displayCreateOrderBanner() {
        io.print("=== Create Order ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Order successfully created.  Please hit enter to continue");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public String getOrderDateChoice() {
        return io.readString("Please enter the Order Date.");
    }

    public String getOrderNumberChoice() {
        return io.readString("Please enter the Order Number for the day.");
    }

    public void exitProgram() {
        io.print("Thank you");
    }

    public void displayOrder(Order order) {
        String orderdate = io.readString("Please enter Order Date");
        if (order != null) {
            io.print(order.getCustomerName());
            //io.print(order.getOrderNumber());
            io.print(order.getProductType());
            io.print(order.getStateName());
            io.print(order.getArea().toString());
            io.print(order.getStateAbbreviation());
            io.print(order.getCostPerSquareFoot().toString());
            io.print(order.getLaborCost().toString());
            io.print("");
        } else {
            io.print("No such order.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayOrderList(List<Order> orderList) {
        for (Order currentStudent : orderList) {
            io.print(currentStudent.getCustomerName() + ": "
                    + currentStudent.getProductType() + " "
                    + currentStudent.getOrderNumber() + " "
                    + currentStudent.getStateName() + " "
                    //+ currentStudent.getStateAbbreviation() + " "
                    + currentStudent.getArea() + " "
                    + currentStudent.getCostPerSquareFoot() + " "
                    + currentStudent.getLaborCost() + " "
                    + currentStudent.getLaborCostPerSquareFoot() + " "
                    + currentStudent.getMaterialCost() + " "
                    + currentStudent.gettotalTax() + " "
                    + currentStudent.getTaxRate() + " "
                    + currentStudent.getTotal1());

        }

        io.readString("Please hit enter to continue.");
    }

}
