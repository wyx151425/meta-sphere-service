package org.metasphere.adminservice.service;

import org.junit.jupiter.api.Test;
import org.metasphere.adminservice.model.pojo.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-03-09 9:53
 * @Modified By:
 */
@SpringBootTest
public class ServerServiceTests {

    @Autowired
    private ServerService serverService;

    @Test
    void testCheckServerStatus() {
        int status = serverService.checkServerStatus("180.201.163.246");
        System.out.println(status);
    }

    @Test
    void testCheckServerStatus2() {
        int status = serverService.checkServerStatus("127.0.0.1", 8889);
        System.out.println(status);
    }

    @Test
    void testSaveServer() {
        Server server = new Server();
        server.setHost("180.201.163.246");
        server.setPort(5200);
        server.setName("NLP引擎服务器");
        server.setType(2);
        serverService.saveServer(server);
    }


    @Test
    void testClassLoader() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        ClassLoader parent = cl.getParent();
        System.out.println(parent);
    }

    @Test
    void testServerSocket() {
        try (ServerSocket ss = new ServerSocket(8889)) {
            final boolean[] flag = {true};
            int[] count = {0};

            new Thread(() -> {
                while (flag[0]) {
                    try {
                        Thread.sleep(2000);
                        if (count[0] > 2) {
                            flag[0] = false;
                        }
                        System.out.println("######" + flag[0]);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();


            while (flag[0]) {
                System.out.println("++++++" + flag[0]);
                Socket accept = null;
                try {
                    accept = ss.accept();
                    count[0]++;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(accept.getPort());
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
