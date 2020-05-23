package com.amos.auto.main;

import com.amos.auto.model.vo.PullVO;

/**
 * DESCRIPTION: 测试
 * 1. 测试 PullVO 继承父类 BaseVO，父类 toString 是否生效
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/5/23
 */
public class TestLombok {

    public static void main(String[] args) {
        System.out.println(new PullVO().setResult("成功").setStatus(true));
    }

}
