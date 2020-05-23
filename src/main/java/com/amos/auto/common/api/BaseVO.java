package com.amos.auto.common.api;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * DESCRIPTION: BaseVO
 * 继承了本类，可以无视（XXX没有写toString方法）
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/5/23
 */
public class BaseVO {

    /**
     * 子类无需重写 toString
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
