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
        private final int porta;

        ServerConnessioneTCP(){
            porta=2100;
        }
        //metodo che fa si che il server passi in modalità "Ascolto" sulla porta 2100
        void inAscolto(){
            ServerSocket sSocket = null;
            Socket connection;
            
            try{
                // il server si mette in ascolto sulla porta voluta
                sSocket = new ServerSocket(porta);
                System.out.println("In attesa di connessioni!");
                //si è stabilita la connessione
                connection = sSocket.accept();
                System.out.println("Connessione stabilita!");
                System.out.println("Socket server: " + connection.getLocalSocketAddress());
                System.out.println("Socket client: " + connection.getRemoteSocketAddress());
                this.rispondi(sSocket,connection);//richiamo il metodo per effettuare la risposta al client
                
            }
               catch(IOException e){
                   System.err.println("Errore di I/O!");
            }
        }
        
        //risposta server da inoltrare al client
        void rispondi(ServerSocket sSocket,Socket connection){
            String messaggioInput="" , messaggioOutput = "";
            try{
                BufferedReader inputServer= new BufferedReader(new InputStreamReader(connection.getInputStream()));//prende in input il messaggio inviato dal client(non avviene più la lettura da tastiera)
                PrintStream outputServer= new PrintStream(connection.getOutputStream());
                while(!"ciao ciao".equals(messaggioOutput)){//ciclo finchè il messaggio che restituisce il server è quello di chiusura
                    messaggioInput=inputServer.readLine();
                    switch(messaggioInput){//controllo del messaggio di input con la quale si definisce la risposta da definire
                            case "chiudi":
                                messaggioOutput="ciao ciao";
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
                this.chiudiConnessione(sSocket);//richiamo la chiusura della connessione una volta finito lo scambio di messaggi
            }catch(IOException e){
                   System.err.println("Errore di I/O!");
            }
        }
        
        //chiusura della connessione in seguito all'invio del messaggio "chiudi"
        void chiudiConnessione(ServerSocket sSocket){
            try {
                if (sSocket!=null) sSocket.close();
            } catch (IOException ex) {
                System.err.println("Errore nella chiusura della connessione!");
            }
            System.out.println("Connessione chiusa!");
        
        }
}
