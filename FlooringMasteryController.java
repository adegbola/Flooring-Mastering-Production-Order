/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.controller;


import FlooringMastery.dao.OrderDaoPersistenceException;
import FlooringMastery.dto.Order;
import FlooringMastery.service.FlooringMasteryDataValidationException;
import FlooringMastery.service.FlooringMasteryServiceLayer;
import FlooringMastery.service.NoOrderException;
import FlooringMastery.ui.FlooringMasteryView;
import FlooringMastery.ui.UserIO;
import FlooringMastery.ui.UserIOConsoleImpl;

import java.math.BigDecimal;

import java.util.List;
import java.util.Scanner;

/**
 * @author Kelsey
 */
public class FlooringMasteryController {

    //FlooringMasteryOrderDao dao;
    FlooringMasteryServiceLayer service;
    FlooringMasteryView view;
    int input = 0;

    //Business service;
    Scanner scanner = new Scanner(System.in);

    public FlooringMasteryController(FlooringMasteryServiceLayer s, FlooringMasteryView v) {
        this.service = s;
        this.view = v;

    }

    private UserIO io = new UserIOConsoleImpl();

    public void run() throws OrderDaoPersistenceException, FlooringMasteryDataValidationException, NoOrderException {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing == true) {
            io.print("Main Menu");
            io.print("1. Get Orders");
            io.print("2. Add Orders");
            io.print("3. Get an Order");
            io.print("4. Edit an Order");
            io.print("5. Remove an Order");
            io.print("6. Save Current Work");
            io.print("7. Exit");

            menuSelection = io.readInt("Please select from the"
                    + " above choices.", 1, 6);

            switch (menuSelection) {
                case 1:
                    getOrders();

                    break;
                case 2:
                    addOrders();
                    break;
                case 3:
                    getOrder();
                    break;
                case 4:
                    editOrder();
                    break;
                case 5:
                    removeOrder();
                    break;
                case 6:
                    saveOrder();
                    break;
                case 7:
                    exitProgram();
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

        }
        io.print("GOOD BYE");
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void getOrder() throws NoOrderException {
        String orderDateAsString = view.getOrderDateChoice();
        String orderNumberAsString = view.getOrderNumberChoice();
        //  LocalDate orderDate = LocalDate.parse(orderDateAsString);
        int orderNumber = Integer.parseInt(orderNumberAsString);
        Order order = service.getorder(orderDateAsString, orderNumber);

        view.displayOrder(order);
    }

    private void getOrders() throws OrderDaoPersistenceException, NoOrderException {

        String v = view.getOrderDateChoice();
        List<Order> orderList = service.getAllOrders(v, input);//getAllOrders(v);

        view.displayOrderList(orderList);

    }

    private void addOrders() throws OrderDaoPersistenceException, NoOrderException {
        view.displayCreateOrderBanner();
        int orderNumber = service.createOrderNumber();
        Order neworder = view.getNewOrderInfo(orderNumber);
        System.out.print(" ** Order summary **");
        System.out.print(neworder.toString());
        System.out.println("");
        System.out.print("Do you wish to save the order? (y/n)");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("y")) {
            service.addOrder(neworder);
            view.displayCreateSuccessBanner();
            service.commitOrder(neworder);
        } else if (input.equalsIgnoreCase("n")) {
            io.print("Data discarded.");
        }
    }

    private void removeOrder() throws FlooringMasteryDataValidationException, NoOrderException, OrderDaoPersistenceException {
        String date = io.readString("Please enter Order Date");
        String number = io.readString("Please enter Order number");
        Order existingOrder = service.getorder(date, Integer.parseInt(number));
        if (existingOrder == null) {
            io.print("Sorry!. Order does not exist");
        } else {

            System.out.print(" ** Order summary **");
            System.out.print(existingOrder.toString());
            System.out.println("");
            System.out.print("Do you wish to remove the  order? (y/n)");
            String input = scanner.nextLine();
            if (input.equals("y")) {
                service.RemoveOrder(existingOrder);
                view.displayRemoveSuccessBanner();
            } else if (input.equals("n")) {
                io.print("Data discarded.");
            }

        }
    }

    private void editOrder() throws NoOrderException {
        String date = io.readString("Please enter Order Date");
        String number = io.readString("Please enter Order number");
        Order existingOrder = service.getorder(date, Integer.parseInt(number));
        if (existingOrder == null) {
            io.print("Sorry!. Order does not exist");
        } else {
            io.print("Continue editing the order");
            String custName = io.readString("Please edit the customer name");
            if (custName.length() > 0) {
                existingOrder.setCustomerName(custName);
            }
            String productType = io.readString("Please edit the product type.");
            if (productType.length() > 0) {
                existingOrder.setProductType(custName);
            }
            String stateAbbr = io.readString("Please edit the State abbrevation");
            if (stateAbbr.length() > 0) {
                existingOrder.setStateAbbreviation(stateAbbr);
            }
            String laborCost = io.readString("Please edit the Labor Cost");
            if (laborCost.length() > 0) {
                existingOrder.setLaborCost(new BigDecimal(laborCost));

            }
            String taxR = io.readString("Please edit the State taxt rate");
            if (taxR.length() > 0) {
                existingOrder.setTaxRate(new BigDecimal(taxR));
            }
            String materialCost = io.readString("Please edit the materialCost");
            if (materialCost.length() > 0) {
                existingOrder.setMaterialCost(new BigDecimal(materialCost));
            }

            System.out.print(" ** Order summary **");
            System.out.print(existingOrder.toString());
            System.out.println("");
            System.out.print("Do you wish to save the edit of this order? (y/n)");
            String input = scanner.nextLine();
            if (input.equals("y")) {
                service.commitEdit(existingOrder);
                view.displayCreateSuccessBanner();
                //dao.commitOrder(order);
            } else if (input.equals("n")) {
                io.print("Data discarded.");
            }

        }

    }

    private void saveOrder() throws OrderDaoPersistenceException {
        service.save();
    }

    private void exitProgram() {
        view.exitProgram();
    }

}
