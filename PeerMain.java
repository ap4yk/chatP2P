package com.aron.peertopeer;

import java.io.*;
import java.net.Socket;

public class PeerMain {

    public static void main(String[] args) throws IOException
    {
        PeerMain PEER = new PeerMain();
        PEER.run();

    }

    public void run() throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in)); //prompt user for input
        System.out.println("> Enter ID & port: ");  // && Port and IP of another User
        String[] userSetup =  bfr.readLine().split(" "); // pick up user input

        ServerMessaging serverMessaging = new ServerMessaging(userSetup[1]); // instantiating ServerThread & passing port
        serverMessaging.start(); //triggering run method in serverThread
        new PeerMain().PeerListener(bfr, userSetup[0], serverMessaging); // Calling PeerListener
    }

    private void PeerListener(BufferedReader bfr, String userID, ServerMessaging serverMessaging) throws IOException {    //  picks up messages, updating peer information

        System.out.println("> enter a members hostaddress & port");
        System.out.println("peers to receive messages from");
        String input = bfr.readLine();
        String[] userInput = input.split(" ");
        //----------------------------------------------
        for (int i = 0; i < userInput.length ; i++) {
            String[] address = userInput[i].split(":"); // pickup host and port and do split
            Socket socket = null;
            try{
                socket = new Socket(address[0], Integer.valueOf(address[1])); // instantiating socket
                new PeerThread(socket).start(); // instance for peerthread, calling start method.
            } catch (Exception e) {
                if (socket != null) socket.close();
                else System.out.println("Invalid Input. Skipping");
            }
        }
    }
    public void communication(BufferedReader bfr, String userID, ServerMessaging serverMessaging) throws IOException { //sending messages
        System.out.println("> Communication has starter");
        boolean flag = true;
        while(flag){
            String message = bfr.readLine();

            StringWriter stringwriter = new StringWriter(); /** Errors might be here**/
            stringwriter.write(message);
            serverMessaging.sendMessage(stringwriter.toString()); // to send messages throught serverMessaging
        }
    }
}
