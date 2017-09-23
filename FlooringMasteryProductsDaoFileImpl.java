/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.dao;


import FlooringMastery.dto.Products;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 *
 * @author Kelsey
 */
public class FlooringMasteryProductsDaoFileImpl implements FlooringMasteryProductsDao {

    private Map<String, Products> productInfo = new HashMap<>();
    public static final String PRODUCTS = "productInfo.txt";

    public static final String DELIMITER = ",";
    Integer productNumber = 0;

    private void loadProduct() throws OrderDaoPersistenceException {

        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCTS)));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new OrderDaoPersistenceException(
                    "-_- Could not load roster data into memory.", e);
        }

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.trim().split(DELIMITER);
            if (currentTokens.length == 3) {

                Products currentItem = new Products();
                currentItem.setProductType(currentTokens[0]);
                currentItem.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));
                currentItem.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[2]));
                productInfo.put(currentItem.getProductType(), currentItem);
            }

        }
        // close scanner
        scanner.close();
    }

    @Override
    public Products getProductinfo(String type) {
        try {
            loadProduct();
        } catch (OrderDaoPersistenceException ex) {
            
        }
        
        return productInfo.get(type);
    }

    @Override
    public List<Products> getProductlist() {
        try {
            loadProduct();
        } catch (OrderDaoPersistenceException ex) {
            
        }
        return new ArrayList<Products>(productInfo.values());

    }

}
