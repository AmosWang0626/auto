package com.amos.auto.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;


/**
 * DESCRIPTION: JschConfig
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/5/23
 */
@Data
@Configuration
@ConfigurationProperties("jsch")
public class JschConfig {

    /**
     * IP/域名
     */
    private String host;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * SSH端口（默认值: 22）
     */
    private int port = 22;
    /**
     * 要执行的命令
     */
    private String command;


    /**
     * com.jcraft.jsch.Session
     */
    private Session session;

    public Session getSession() {
        return session;
    }

    @PostConstruct
    public void initial() {
        JSch jsch = new JSch();
        try {
            session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void close() {
        if (session != null) {
            session.disconnect();
        }
    }

}
