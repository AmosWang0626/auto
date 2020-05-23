package com.amos.auto.core;

import com.amos.auto.common.api.GeneralResponse;
import com.amos.auto.model.form.PullForm;
import reactor.core.publisher.Mono;

/**
 * DESCRIPTION: PullService
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/5/22
 */
public interface PullService {

    /**
     * 自动更新代码
     *
     * @param pullForm 请求表单
     * @return GeneralResponse
     */
    Mono<GeneralResponse<String>> update(PullForm pullForm);
}
