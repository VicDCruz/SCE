/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RGAMBOAH
 */
public class HolaMundo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        String strNomClase = Thread.currentThread().getStackTrace()[1].getClassName();
        System.out.println("Saludos desde la clase " +strNomClase );
    }
    
}
