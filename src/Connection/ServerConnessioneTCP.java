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
        private Socket connection;
        private ServerSocket connessioneServer;
		
        ServerConnessioneTCP(){
           connessioneServer=null;
           connection=null;
        }
        //metodo che fa si che il server passi in modalità "Ascolto" sulla porta inserita come parametro del metodo
        public Socket inAscolto(int porta){
            ServerSocket sSocket = null;
            
            try{
                // il server si mette in ascolto sulla porta voluta
                connessioneServer = new ServerSocket(porta);
                System.out.println("In attesa di connessioni!");
                //si è stabilita la connessione
                this.connection = connessioneServer.accept();
                System.out.println("Connessione stabilita!");
                System.out.println("Socket server: " + connection.getLocalSocketAddress());
                System.out.println("Socket client: " + connection.getRemoteSocketAddress());
                
            }
               catch(IOException e){
                   System.err.println("Errore di I/O!");
            }
			return connection;
        }
        
        //risposta server da inoltrare al client
        public void rispondi(){
	    boolean a = true;
            String messaggioInput="" , messaggioOutput = "";
            try{
                BufferedReader inputServer= new BufferedReader(new InputStreamReader(this.connection.getInputStream()));//prende in input il messaggio inviato dal client(non avviene più la lettura da tastiera)
                PrintStream outputServer= new PrintStream(this.connection.getOutputStream());
                while(a){
                    messaggioInput=inputServer.readLine();
                    switch(messaggioInput){//controllo del messaggio di input con la quale si definisce la risposta da definire
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
            }catch(IOException e){
                   System.err.println("Errore di I/O!");
            }
		
        }
        
        //chiusura della connessione in seguito all'invio del messaggio "chiudi"
        void chiudiConnessione(){
            try {
                if (this.connessioneServer!=null) this.connessioneServer.close();
            } catch (IOException ex) {
                System.err.println("Errore nella chiusura della connessione!");
            }
            System.out.println("Connessione chiusa!");
        
        }
}
