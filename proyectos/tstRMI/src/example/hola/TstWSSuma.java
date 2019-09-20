/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.hola;

/**
 *
 * @author sdist
 */
public class TstWSSuma {
    public static int suma() {
        example.hello.WSSuma_Service service = new example.hello.WSSuma_Service();
        example.hello.WSSuma port = service.getWSSumaPort();
        int a, b, c = 0;
        a = (int) (Math.random() * 50);
        b = (int) (Math.random() * 60);
        c = port.suma(a, b);
        System.out.println("Terminado.");
        return c;
    }
    
    public static void main(String args[]) {
        example.hello.WSSuma_Service service = new example.hello.WSSuma_Service();
        example.hello.WSSuma port = service.getWSSumaPort();
        int a, b, c;
        for (int i = 0; i < 1000; i++) {
            a = (int) (Math.random() * 50);
            b = (int) (Math.random() * 60);
            c = port.suma(a, b);
        }
        System.out.println("Terminado.");   
    }
    
}
