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
public class ClientConnessioneTCP {
        private final int porta;
        private final String indirizzoServer;
        
       ClientConnessioneTCP(){
           porta=2100;
           indirizzoServer="localhost";
       }
       
       
       //metodo che avvia la connessione con il server
       void avviaConnessione(){
           
            Socket connection = null;
            String serverAddress = "localhost";
            boolean a=true;
       }
       //scrittura messaggio da inoltrare al server
        void scriviMessaggio(){
        
        }
        //chiusura della connessione in seguito all'invio del messaggio "chiudi"
        void chiudiConnessione(){
        
        }
}
