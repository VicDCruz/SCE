/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.hola;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vicda
 */
public class TestOperaciones {

    private static final int TOTALSERVICES = 4;

    private static int getOperation() {
        return 1 + ((int) Math.round(Math.random() * (TOTALSERVICES - 1)));
    }

    public static double doRequest() {
        example.hello.MycalculatorCAService1 service
                = new example.hello.MycalculatorCAService1();
        example.hello.MyCalculatorPortType port = service.getCasaPort1();
        example.hello.InputComplexType input;
        example.hello.OutputComplextype output = null;

        input = new example.hello.InputComplexType();
        input.setParam01((int) (Math.random() * 20));
        // input.setParam02((int) (Math.random() * 20));
        input.setParam02(0);
        switch (getOperation()) {
            case 1:
                output = additionOperation(input, port);
                break;
            case 2:
                output = subtractionOperation(input, port);
                break;
            case 3:
                output = multiplicationOperation(input, port);
                break;
            case 4: {
                try {
                    output = divisionOperation(input, port);
                } catch (example.hello.OperationFault ex) {
                    System.out.println(ex.getFaultInfo().getFaultDetail());
                    return 0;
                }
            }
            break;
            default:
                throw new AssertionError();
        }
        return output.getResponse01();
    }

    public static void main(String args[]) {
        for (int i = 0; i < 10; i++)
            System.out.println("Resultado " + TestOperaciones.doRequest());        
    }

    private static example.hello.OutputComplextype additionOperation(
            example.hello.InputComplexType part1,
            example.hello.MyCalculatorPortType port) {
        return port.additionOperation(part1);
    }

    private static example.hello.OutputComplextype divisionOperation(
            example.hello.InputComplexType part1,
            example.hello.MyCalculatorPortType port) throws example.hello.OperationFault {
        return port.divisionOperation(part1);
    }

    private static example.hello.OutputComplextype multiplicationOperation(
            example.hello.InputComplexType part1,
            example.hello.MyCalculatorPortType port) {
        return port.multiplicationOperation(part1);
    }

    private static example.hello.OutputComplextype subtractionOperation(
            example.hello.InputComplexType part1,
            example.hello.MyCalculatorPortType port) {
        return port.subtractionOperation(part1);
    }
}
