/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;

/**
 *
 * @author Denga
 */

import javax.xml.ws.Endpoint;

public class CalculadoraServerPublisher {
    
    public static void main(){
        Endpoint.publish("http://127.0.0.1:9876/calc", new CalculadoraServerImpl());
    }
}
