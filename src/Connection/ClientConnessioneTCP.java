/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;

/**
 *
 * @author Armando Palermo
 */
public class ClientConnessioneTCP {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        //oggetto da usare per realizzare la connessione TCP
        Socket connection = null;
        //nome o IP del server
        String serverAddress = "localhost";
        //porta del server in ascolto
        int port = 2000;
        boolean a=true;

        while(a){//controllo booleana per il ciclo finchè non il client invia exit

            //apertura della connessione al server sulla porta specificata
            try{

                connection = new Socket(serverAddress, port);
                System.out.println("Connessione aperta");
                BufferedReader inputClient= new BufferedReader(new InputStreamReader(System.in));//Input da tastiera
                BufferedReader inputClientRispServer= new BufferedReader(new InputStreamReader(connection.getInputStream()));//Stream per gestione della risposta del server
                PrintStream outputClient= new PrintStream(connection.getOutputStream());
                System.out.println("Scrivi il messaggio da inviare al server");
                String messaggio=inputClient.readLine();
                outputClient.println(messaggio);
                outputClient.flush();
                String rispostaServer=inputClientRispServer.readLine();
                System.out.println(rispostaServer);//stampo la risposta del server
                //controllo per fine connessione ed uscita dal while
                if(rispostaServer.equals("ciao ciao")){
                    a=false;
                }
            }
            catch(ConnectException e){
                System.err.println("Server non disponibile!");
            }
            catch(UnknownHostException e1){
                System.err.println("Errore DNS!");
            }

            catch(IOException e2){//
                System.err.println(e2);
                e2.printStackTrace();
            }

            //chiusura della connnessione
            finally{
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
    } 
}
