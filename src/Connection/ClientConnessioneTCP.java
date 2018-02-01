/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            
             try{

                connection = new Socket(serverAddress, porta);
                System.out.println("Connessione aperta");
                this.scriviMessaggio(connection);//richiamo il metodo che permette di scrivere un messaggio al server
               
            }
            catch(ConnectException e){//gestione dele eccezioni
                System.err.println("Server non disponibile!");
            }
            catch(UnknownHostException e1){
                System.err.println("Errore DNS!");
            }
            catch(IOException e2){
                 System.err.println("Errore I/O");
             }
       }
       
       
       //scrittura messaggio da inoltrare al server
        void scriviMessaggio(Socket connection){
            String messaggio="";
            try {
                BufferedReader inputClient= new BufferedReader(new InputStreamReader(System.in));//Input da tastiera
                BufferedReader inputClientRispServer= new BufferedReader(new InputStreamReader(connection.getInputStream()));//Stream per gestione della risposta del server
                PrintStream outputClient= new PrintStream(connection.getOutputStream());
                while(!"chiudi".equals(messaggio)){
                    System.out.println("Scrivi un messaggio da inoltrare al server");
                    messaggio=inputClient.readLine();//primo input da tastiera del client
                    outputClient.println(messaggio);
                    outputClient.flush();
                    String rispostaServer=inputClientRispServer.readLine();//lettura della risposta inviata dal server
                    System.out.println(rispostaServer);//stampo la risposta del server
                }
                this.chiudiConnessione(connection);//chiusura connessione una volta finito lo scambio di messagi tra client e server
            } catch (IOException ex) {
                Logger.getLogger(ClientConnessioneTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
        //chiusura della connessione in seguito all'invio del messaggio "chiudi"
        void chiudiConnessione(Socket connection){
                try {
                    if (connection!=null)
                        {
                            connection.close();
                            System.out.println("Connessione chiusa!");
                        }
                    }
                catch(IOException e){
                    System.err.println("Errore nella chiusura della connessione!");
                }
        }
}
