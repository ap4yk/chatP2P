package com.aron.peertopeer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class ServerMessaging extends Thread {
    private ServerSocket ss;
    private Set<ServerThread> stt = new HashSet<ServerThread>();

    public ServerMessaging(String port) throws IOException {
        ss = new ServerSocket(Integer.valueOf(port));
    }

    @Override
    public void run() {
        try{
            while (true){
                ServerThread serverThread = new ServerThread(ss.accept(), this ); // server thread for each eer
                stt.add(serverThread); // adding each peer to set
                serverThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void sendMessage(String message){
        try{stt.forEach(t-> t.getPrintWriter().println(message));} catch (Exception e) { //picking up messages on each server thread in a set
            e.printStackTrace();
        }
    }
    public Set<ServerThread> getStt(){   //get method for the Server Set.
        return stt;
    }
}
