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
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Armando Palermo
 */
public class ServerConnessioneTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // porta del server maggiore di 1024 
        int port=2000;
        //oggetto ServerSocket necessario per accettare richieste dal client
        ServerSocket sSocket = null;
        //oggetto da usare per realizzare la connessione TCP
        Socket connection;
        String messaggioInput= "",messaggioOutput = "";
        boolean a=true;

        while(a){//controllo booleana per il ciclo finchè non il client invia exit
            try{
                // il server si mette in ascolto sulla porta voluta
                sSocket = new ServerSocket(port);
                System.out.println("In attesa di connessioni!");
                //si è stabilita la connessione
                connection = sSocket.accept();
                System.out.println("Connessione stabilita!");
                System.out.println("Socket server: " + connection.getLocalSocketAddress());
                System.out.println("Socket client: " + connection.getRemoteSocketAddress());
                BufferedReader inputServer= new BufferedReader(new InputStreamReader(connection.getInputStream()));//prende in input il messaggio inviato dal client(non avviene più la lettura da tastiera)
                PrintStream outputServer= new PrintStream(connection.getOutputStream());
                messaggioInput=inputServer.readLine();
                
                switch(messaggioInput){
                    case "chiudi":
                        messaggioOutput="ciao ciao";
                        a=false;
                        break;
                    case "ciao":
                        messaggioOutput ="salve";
                        break;
                    case "come stai":
                        messaggioOutput ="bene";
                        break;
                }
                outputServer.println(messaggioOutput);
                outputServer.flush();//svuoto lo stream e invio il messaggio
                System.out.println(messaggioOutput);
            }
               catch(IOException e){
                   System.err.println("Errore di I/O!");
            }
            
            //chiusura della connessione con il client
            try {
                if (sSocket!=null) sSocket.close();
            } catch (IOException ex) {
                System.err.println("Errore nella chiusura della connessione!");
            }
            System.out.println("Connessione chiusa!");
        }
     }
}
