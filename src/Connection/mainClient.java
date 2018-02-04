/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.net.Socket;

/**
 *
 * @author Armando Palermo
 */
public class mainClient {
    
    public static void main(String[] args) {
        ClientConnessioneTCP c=new ClientConnessioneTCP();
        c.avviaConnessione("localhost",2000);
        c.scriviMessaggio();
	c.chiudiConnessione();
    }
    
}
