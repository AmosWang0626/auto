package com.amos.auto.web.controller;

import com.amos.auto.common.api.GeneralResponse;
import com.amos.auto.core.PullService;
import com.amos.auto.model.form.PullForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * DESCRIPTION: 自动拉取代码
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/5/22
 */
@RestController
@RequestMapping("pull")
public class PullController {

    @Resource
    private PullService pullService;

    @PostMapping
    public Mono<GeneralResponse<String>> update(@RequestBody PullForm pullForm) {
        return pullService.update(pullForm);
    }

}
