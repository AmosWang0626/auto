package com.amos.auto.model.vo;

import com.amos.auto.common.api.BaseVO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * DESCRIPTION: PullVO
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/5/22
 */
@Getter
@Setter
@Accessors(chain = true)
public class PullVO extends BaseVO {

    private String result;

    private Boolean status;

}
