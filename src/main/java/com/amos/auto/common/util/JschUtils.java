package com.amos.auto.common.util;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * DESCRIPTION: JschUtils
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/5/23
 */
public class JschUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JschUtils.class);

    /**
     * 执行 Linux 命令
     *
     * @param session com.jcraft.jsch.Session
     * @param command 要执行的命令
     * @return 执行命令结果
     */
    public static Optional<List<String>> exec(Session session, String command) {
        ChannelExec channelExec = null;
        BufferedReader reader = null;
        List<String> result = null;
        try {
            // 执行命令
            channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand(command);
            channelExec.setInputStream(null);
            channelExec.setErrStream(System.err);
            channelExec.connect();

            // 读取结果
            InputStream inputStream = channelExec.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String buf;
            result = new ArrayList<>();
            while ((buf = reader.readLine()) != null) {
                result.add(buf);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(">>>>>>>>> 执行命令 { " + command + " } 失败.", e);
        } finally {
            // 关闭连接
            try {
                Objects.requireNonNull(reader).close();
                Objects.requireNonNull(channelExec).disconnect();
            } catch (IOException ignored) {
            }
        }

        return Optional.ofNullable(result);
    }

}
