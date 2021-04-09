package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServeurSocket {

    /**
     * Socket du serveur
     */
    private ServerSocket serverSocket;

    /**
     * Socket des clients connectés
     */
    private ArrayList<Socket> clientSockets;

    /**
     * Démarrer le socket serveur et écouter les demandes de connexion
     *
     * @throws IOException
     */
    public void start() throws IOException {
        this.serverSocket = new ServerSocket(25000);

        // On écoute les demandes de connexion de la part des clients
        while (true) {
            // Établir la connexion avec le client
            Socket socket = serverSocket.accept();
            this.clientSockets.add(socket);

            // Démarrer le thread qui gère le client
            new ClientHandler(socket).start();
        }
    }

    /**
     * Fermer les sockets
     *
     * @throws IOException
     */
    public void stop() throws IOException {
        for (Socket socket : this.clientSockets) {
            socket.close();
        }

        this.serverSocket.close();
    }

    /**
     * Classe qui gère la communication avec un client connecté
     */
    private static class ClientHandler extends Thread {

        /**
         * Socket du client
         */
        private Socket clientSocket;

        /**
         * Flux de réception des messages
         */
        private BufferedReader fluxEntrant;

        /**
         * Flux de transmission des messages
         */
        private PrintWriter fluxSortant;

        /**
         * @param socket Socket du client connecté
         */
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        /**
         * Logger un message dans la console serveur
         *
         * @param msg Message log
         */
        public void log(String msg) {
            System.out.println("[IP : " + this.clientSocket.getInetAddress() + "] " + msg + ".");
        }

        /**
         * Logger un message d'erreur dans la console serveur
         *
         * @param msg Message erreur
         */
        public void error(String msg) {
            System.err.println("[IP : " + this.clientSocket.getInetAddress() + "] " + msg + ".");
        }

        @Override
        public void run() {
            this.log("Connexion effectuée");

            try {
                this.fluxEntrant = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
                this.fluxSortant = new PrintWriter(this.clientSocket.getOutputStream(), true);

                String msgRecu;
                while ((msgRecu = this.fluxEntrant.readLine()) != null) {
                    System.out.println("<< " + msgRecu);

                    if (msgRecu.equals(".")) {
                        this.fluxSortant.println("bye");
                        break;
                    }

                    this.fluxSortant.println(msgRecu);
                }

                this.fluxEntrant.close();
                this.fluxSortant.close();
                this.clientSocket.close();
            } catch (IOException ex) {

                this.error("Erreur de communication.");
                Logger.getLogger(ServeurSocket.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
