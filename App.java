import FlooringMastery.controller.FlooringMasteryController;
import FlooringMastery.dao.OrderDaoPersistenceException;
import FlooringMastery.service.FlooringMasteryDataValidationException;
import FlooringMastery.service.NoOrderException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kelsey
 */
public class App {

    public static void main(String[] args) throws OrderDaoPersistenceException, FlooringMasteryDataValidationException, NoOrderException {
        // FlooringMasteryController controller = new FlooringMasteryController();
        // controller.run();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller = ctx.getBean("controller", FlooringMasteryController.class);
        System.out.println("===Production Mode===");
        controller.run();
    }
}
