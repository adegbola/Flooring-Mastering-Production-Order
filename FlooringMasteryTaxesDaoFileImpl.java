/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.dao;
import FlooringMastery.dto.Taxes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kelsey
 */
public class FlooringMasteryTaxesDaoFileImpl implements FlooringMasteryTaxesDao {
    Taxes tax=new Taxes();
    public final Map<String, Taxes> statetax = new HashMap<>();
    public static final String STATE_TAXES = "stateTax.txt";
    private static final String DELIMITER = ",";
    Integer stateNum = 0;

    public FlooringMasteryTaxesDaoFileImpl() {
        try {
            loadTaxes();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadTaxes() throws OrderDaoPersistenceException {

        Scanner scanner;
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(STATE_TAXES)));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new OrderDaoPersistenceException(
                    "-_- Could not load roster data into memory.", e);

        }
        String currentLine;

        String[] currentTokens;
        scanner.nextLine();
        while (scanner.hasNextLine()) {
           currentLine = scanner.nextLine();
           currentTokens = currentLine.split(DELIMITER);
            Taxes currentTax = new Taxes();
            currentTax.setStateName(currentTokens[0]);
            currentTax.setTax(new BigDecimal(currentTokens[1]));

            //statetax.put(currentTokens[0], currentTax
            statetax.put(currentTax.getStateName(), currentTax);
        }
    }

    @Override
    public List<Taxes> getStates() {
        return new ArrayList<Taxes>(statetax.values());
    }

    @Override
    public Taxes gettax(String state) {
        try {
            loadTaxes();
        } catch (OrderDaoPersistenceException ex) {
            
        }
        return statetax.get(state);
    }

}
