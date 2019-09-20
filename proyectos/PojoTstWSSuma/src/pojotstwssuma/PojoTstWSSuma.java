/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojotstwssuma;

/**
 *
 * @author sdist
 */
public class PojoTstWSSuma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println("Suma: " + suma(5, 9));
        }
    }

    private static int suma(int a, int b) {
        suma.WSSuma_Service service = new suma.WSSuma_Service();
        suma.WSSuma port = service.getWSSumaPort();
        return port.suma(a, b);
    }
    
}
