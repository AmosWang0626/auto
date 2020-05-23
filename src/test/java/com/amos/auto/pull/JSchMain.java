package com.amos.auto.pull;

import com.amos.auto.common.util.JschUtils;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jcraft.jsch.Session;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PROJECT: chaos
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2019/1/18
 */
public class JSchMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSchMain.class);

    private static final String HOST = "127.0.0.1";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String IP_REGEX = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$";


    public static void main(String[] args) {
        String ip = "192.168.1.34";
        LOGGER.info("add ip status : {}", addWhiteIp(ip));
    }


    /**
     * 执行脚本添加白名单 IP
     *
     * @param ip IP Address
     * @return true 添加成功
     */
    private static boolean addWhiteIp(String ip) {
        if (!checkIp(ip)) {
            LOGGER.error("IP [{}] 格式错误", ip);
            return false;
        }

        String cmdExistIp = "cat /etc/nginx/block_ip.txt";
        String cmdAddIp = "./white_ip.sh " + ip;
        AtomicBoolean ipExist = new AtomicBoolean(false);

        try {
            long begin = System.currentTimeMillis();
            Session session = JschSessionDCL.getInstance(HOST, USER, PASSWORD);
            LOGGER.info(">>>>>>>>> 连接 [{}] 成功. [耗时 {} 毫秒]", HOST, (System.currentTimeMillis() - begin));

            // 遍历执行结果, IP 不能重复添加
            JschUtils.exec(session, cmdExistIp)
                    .ifPresent(infos -> ipExist.set(infos.stream().anyMatch(s -> s.contains(ip))));
            if (ipExist.get()) {
                LOGGER.error("IP [{}] 已存在,请勿重复添加", ip);
                return false;
            }

            JschUtils.exec(session, cmdAddIp).ifPresent(strings ->
                    LOGGER.info(new GsonJsonProvider().toJson(strings)));

            return true;
        } catch (Exception e) {
            LOGGER.error("IP 添加失败", e);
        } finally {
            JschSessionDCL.closeSession();
        }

        return false;
    }

    /**
     * 校验 IP 格式
     *
     * @param ip IP地址
     * @return true IP格式正确；false：格式错误。
     */
    private static boolean checkIp(String ip) {
        if (StringUtils.isBlank(ip)) {
            return false;
        }
        Matcher matcher = Pattern.compile(IP_REGEX).matcher(ip);
        if (matcher.matches()) {
            String[] split = ip.split("\\.");
            for (String str : split) {
                if (Integer.parseInt(str) > 255) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
