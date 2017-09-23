/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.service;

/**
 *
 * @author Kelsey
 */
public class FlooringMasteryDataValidationException extends Exception{
    public FlooringMasteryDataValidationException(String message) {
        super(message);
    }

    public FlooringMasteryDataValidationException(String message,
            Throwable cause) {
        super(message, cause);
    }
    @Override
    public String toString()
    {
        return "Invalid Data";
    }
    
    
}
