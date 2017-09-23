/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author Kelsey
 */
public class Order {

  

    private int orderNumber;
    private String orderDate;
    
    public String getOrderDate() {
    return orderDate;
    }
    public void setOrderDate(String orderDate) {
    this.orderDate = orderDate;
    }
    public BigDecimal getTotalTax() {
    return totalTax;
    }
    public void setTotalTax(BigDecimal totalTax) {
    this.totalTax = totalTax;
    }
    

   
    private String CustomerName;
    private String ProductType;
    private String StateAbbreviation;
    private String StateName;
    private BigDecimal TaxRate;
    private BigDecimal LaborCostPerSquareFoot;
    private BigDecimal CostPerSquareFoot;
    private BigDecimal Total1;
    private BigDecimal area;
    private BigDecimal laborCost;
    // private int orderNumber;
    //private String orderDate;
    private BigDecimal materialCost;
    private BigDecimal totalTax;
    private int orderNum=0;

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal gettotalTax() {
        return totalTax;
    }

    public void settotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String ProductType) {
        this.ProductType = ProductType;
    }

    public String getStateAbbreviation() {
        return StateAbbreviation;
    }

    public void setStateAbbreviation(String StateAbbreviation) {
        this.StateAbbreviation = StateAbbreviation;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String StateName) {
        this.StateName = StateName;
    }

    public BigDecimal getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(BigDecimal TaxRate) {
        this.TaxRate = TaxRate;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return LaborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal LaborCostPerSquareFoot) {
        this.LaborCostPerSquareFoot = LaborCostPerSquareFoot;
    }

    public BigDecimal getCostPerSquareFoot() {
        return CostPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal CostPerSquareFoot) {
        this.CostPerSquareFoot = CostPerSquareFoot;
    }

    public BigDecimal getTotal1() {
        return Total1;
    }

    public void setTotal1(BigDecimal Total) {
        this.Total1 = Total;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }
 @Override
    public String toString() {
        String orderSummary = "Order date : " + orderDate + " Order id  : "+ orderNumber  + " Order Type : "+ ProductType;  
        return orderSummary; //To change body of generated methods, choose Tools | Templates.
    }
}
    
    
    
    
    

