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
public class Taxes {
    private String stateName;
    private BigDecimal tax;
    private BigDecimal TotalTax;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotalTax() {
        return TotalTax;
    }

    public void setTotalTax(BigDecimal TotalTax) {
        this.TotalTax = TotalTax;
    }

    
    
}
