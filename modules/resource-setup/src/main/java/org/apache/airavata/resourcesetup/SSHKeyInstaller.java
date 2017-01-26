package org.apache.airavata.resourcesetup;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by marcus on 1/25/17.
 */
public class SSHKeyInstaller {
    private final static Logger logger = LoggerFactory.getLogger(SSHKeyInstaller.class);

    public static void install(String username, String password, String hostname, String publicKey) {

        Session session = null;
        Channel channel = null;

        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, hostname);
            session.setPassword(password);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();

            channel = session.openChannel("shell");
            channel.connect();

            // Shell commands for installing public key in authorized_keys file
            try (OutputStream os = channel.getOutputStream()) {
                try (PrintStream ps = new PrintStream(os, true)) {
                    ps.println("mkdir -p -m 700 ~/.ssh/");
                    ps.println("echo '" + publicKey + "' >> ~/.ssh/authorized_keys");
                    ps.println("chmod 600 ~/.ssh/authorized_keys");
                    ps.println("exit $?");
                    ps.close();
                }
            }

            // Consume shell output and make sure it didn't exit with any errors
            try (InputStream in = channel.getInputStream()) {
                byte[] tmp = new byte[1024];
                String result = "";
                Integer exitStatus;

                while (true) {
                    while (in.available() > 0) {
                        int i = in.read(tmp, 0, 1024);
                        if (i < 0) break;
                        result += new String(tmp, 0, i);
                    }
                    if (channel.isClosed()) {
                        if (in.available() > 0) continue;
                        exitStatus = channel.getExitStatus();
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }

                logger.debug("Output from shell: " + result);
                logger.debug("Exit status: " + exitStatus);

                if (exitStatus == null || exitStatus != 0) {
                    throw new RuntimeException("SSH key install script exited with exit status: " + exitStatus);
                }
            }

        } catch (JSchException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {

            if (channel != null) {

                channel.disconnect();
            }
            if (session != null) {

                session.disconnect();
            }
        }
    }

    public static void main(String args[]) {

        String password = "XXXX";
        String publicKey = "XXXX";
        try {
            SSHKeyInstaller.install("machrist", password, "bigred2.uits.iu.edu", publicKey);
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }
}
