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
        Socket connection;
        
        
       ClientConnessioneTCP(){
           connection=null;
       }
       
       
       //metodo che avvia la connessione con il server
       public Socket avviaConnessione(String indirizzoServer, int porta){
           
            
             try{

                this.connection = new Socket(indirizzoServer, porta);
                System.out.println("Connessione aperta");
               
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
			 
			return connection;
       }
       
       
       //scrittura messaggio da inoltrare al server
        public void scriviMessaggio(){
            boolean a=true;
            String messaggio="";
            try {
                BufferedReader inputClient= new BufferedReader(new InputStreamReader(System.in));//Input da tastiera
                BufferedReader inputClientRispServer= new BufferedReader(new InputStreamReader(this.connection.getInputStream()));//Stream per gestione della risposta del server
                PrintStream outputClient= new PrintStream(this.connection.getOutputStream());
                
                while(a){
                    System.out.println("Scrivi un messaggio da inoltrare al server");
                    messaggio=inputClient.readLine();//primo input da tastiera del client
                    outputClient.println(messaggio);
                    outputClient.flush();
                    String rispostaServer=inputClientRispServer.readLine();//lettura della risposta inviata dal server
                    System.out.println(rispostaServer);//stampo la risposta del server
                    if("chiudi".equals(messaggio)){
                        a=false;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientConnessioneTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //chiusura della connessione in seguito all'invio del messaggio "chiudi"
        void chiudiConnessione(){
                try {
                    if (this.connection!=null)
                        {
                            this.connection.close();
                            System.out.println("Connessione chiusa!");
                        }
                    }
                catch(IOException e){
                    System.err.println("Errore nella chiusura della connessione!");
                }
        }
}
