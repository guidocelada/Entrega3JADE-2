package Agents;

import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.Location;
import java.io.File;
import java.util.Scanner;

/**
 * This is an agent that can add the numbers in a file.
 * This file it's in a container, different than the origin container
 *
 * @author Celada, Soria
 */
//TODO: 
@SuppressWarnings("serial")
public class AdderAgent extends Agent {  
       
    private String container = "Container-1"; //the container to move to
       
    private int result;
    
    private Location origin;
    
    
    @Override
    public void setup() {
        System.out.println("\n---------------------------------------\n");
        System.out.println("DEBUG: Initiating " + this.getLocalName());
        
        this.result = 0;
        this.origin = this.here();
        this.doMove(new ContainerID(container, null));     
    }
    
    /**
     * Called after the object has been moved to another container
     **/
    @Override
    public void afterMove() {      
        
        if (this.here().getID().equals(this.origin.getID())) {
            System.out.printf("\033[1m\nThe sum of the numbers is: " + result + " \033[0m \n\n");
        } else {
            System.out.println("DEBUG: " + this.getLocalName() + " passing through " + this.here().getName());
                
            try { //Read the file and make the sum
                Scanner scanner = new Scanner(new File("numbers.txt"));
                while(scanner.hasNextInt()) {
                    result += scanner.nextInt();
                }
            } catch (Exception ex) {
                System.out.println("\u001B[31mERROR: "+ex.getMessage()+"\u001B[0m");
            }
            
            this.doMove(origin);
        }
    }
}
