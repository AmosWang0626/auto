package com.amos.auto.core.impl;

import com.amos.auto.common.api.GeneralResponse;
import com.amos.auto.common.util.JschUtils;
import com.amos.auto.config.JschConfig;
import com.amos.auto.core.PullService;
import com.amos.auto.model.form.PullForm;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * DESCRIPTION: PullServiceImpl
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/5/22
 */
@Service("pullService")
public class PullServiceImpl implements PullService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PullServiceImpl.class);

    @Resource
    private JschConfig jschConfig;


    @Override
    public Mono<GeneralResponse<String>> update(PullForm pullForm) {
        Session session = jschConfig.getSession();
        String command = jschConfig.getCommand();

        LOGGER.debug(">>>>>>>>> 请求参数 [{}]", pullForm);

        long begin = System.currentTimeMillis();
        LOGGER.debug(">>>>>>>>> 执行命令 [{}] 开始...", command);

        Optional<List<String>> execResult = JschUtils.exec(session, command);

        LOGGER.debug(">>>>>>>>> 执行命令 [{}] 完成, 执行耗时 [{}] 毫秒", command, (System.currentTimeMillis() - begin));

        return execResult
                .map(list -> GeneralResponse.ofSuccess(
                        list.stream().collect(Collectors.joining("\n", "", "\n"))))
                .orElseGet(GeneralResponse::ofFail);
    }

}
