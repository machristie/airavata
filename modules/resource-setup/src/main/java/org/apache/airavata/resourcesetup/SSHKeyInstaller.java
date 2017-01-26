package org.apache.airavata.resourcesetup;

import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by marcus on 1/25/17.
 */
public class SSHKeyInstaller {

    public static void install(String username, String password, String hostname, String publicKey) throws JSchException, IOException {

        JSch jsch = new JSch();
        Session session = jsch.getSession(username, hostname);
        session.setPassword(password);
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();

        Channel channel = session.openChannel("shell");
        OutputStream os = channel.getOutputStream();
        PrintStream ps = new PrintStream(os, true);

        channel.connect();
        ps.println("mkdir -p -m 700 ~/.ssh/");
        ps.println("echo '" + publicKey + "' >> ~/.ssh/authorized_keys");
        ps.println("chmod 600 ~/.ssh/authorized_keys");
        ps.println("exit $?");
        ps.close();

        InputStream in = channel.getInputStream();


        byte[] tmp=new byte[1024];
        String result = "";
        Integer exitStatus;

        while(true){
            while(in.available()>0){
                int i=in.read(tmp, 0, 1024);
                if(i<0)break;
                result += new String(tmp, 0, i);
            }
            if(channel.isClosed()){
                if(in.available()>0) continue;
                exitStatus = channel.getExitStatus();
                break;
            }
            try{Thread.sleep(1000);}catch(Exception ee){}
        }
        channel.disconnect();
        session.disconnect();

        System.out.println("Output from script :" + result);

    }

    public static void main(String args[]) throws IOException, JSchException {

        String password = "XXXX";
        String publicKey = "XXXX";
        SSHKeyInstaller.install("machrist", password, "bigred2.uits.iu.edu", publicKey);
    }
}
