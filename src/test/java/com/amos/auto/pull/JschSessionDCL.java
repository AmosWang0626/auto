package com.amos.auto.pull;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * PROJECT: chaos
 * DESCRIPTION: JSch 连接 Linux Util
 *
 * @author Daoyuan
 * @date 2019/1/18
 */
public class JschSessionDCL {

    /**
     * DEFAULT PORT
     */
    private static final int DEFAULT_SSH_PORT = 22;
    /**
     * Singleton Session
     */
    private static volatile Session session;


    public static Session getInstance(String host, String username, String password) throws JSchException {
        return getInstance(host, username, password, DEFAULT_SSH_PORT);
    }

    /**
     * 获取 Session 对象
     *
     * @param host     域名
     * @param username 用户名
     * @param password 密码
     * @param port     ssh端口
     * @return Session 对象
     * @throws JSchException 获取 Session 对象异常
     */
    public static Session getInstance(String host, String username, String password, int port) throws JSchException {
        if (session == null) {
            synchronized (Session.class) {
                if (session == null) {
                    JSch jsch = new JSch();
                    session = jsch.getSession(username, host, port);
                    session.setPassword(password);
                    session.setConfig("StrictHostKeyChecking", "no");
                    session.connect();

                }
            }
        }
        return session;
    }

    public static void closeSession() {
        if (session != null) {
            session.disconnect();
        }
    }

}
