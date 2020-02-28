package com.aron.peertopeer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    private ServerMessaging serverMessaging;
    private Socket socket;
    private PrintWriter printWriter;
    public ServerThread(Socket socket, ServerMessaging serverMessaging){ //initializing socket and serverMessaging
        this.serverMessaging = serverMessaging;
        this.socket = socket;
    }
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
            while(true) serverMessaging.sendMessage( bufferedReader.readLine());
        } catch (Exception e) { serverMessaging.getStt().remove(this); }
    }
    public PrintWriter getPrintWriter() {return printWriter;}
}
