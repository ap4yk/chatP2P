package com.aron.peertopeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PeerThread extends Thread {

    private String[] address;
    private BufferedReader bfr;

    public PeerThread(Socket socket) throws IOException {
        bfr = new BufferedReader(new InputStreamReader(socket.getInputStream())); // get input stream from user
    }

    @Override
    public void run(){ //pickup messages here and print out
        boolean flag = true;
        while (flag){
            try {
                Object o = new BufferedReader(bfr).readLine();     //picking up messges/** MIGHT BE PROBLEMS HERE **/
                if(o.equals(address))                                          /**  From here **/
                    System.out.println("["+o.toString()+"]: "+o.toString());
                                                                                /** To here**/
            } catch (Exception e) {
                flag = false;
                interrupt();
            }
        } //while loop brackets
    } // run brackets


    }

