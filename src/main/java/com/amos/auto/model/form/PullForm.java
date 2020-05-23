package com.amos.auto.model.form;

import com.amos.auto.common.api.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * DESCRIPTION: PullForm
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/5/22
 */
@Getter
@Setter
@Accessors(chain = true)
public class PullForm extends BaseForm {

    private String ref;
    private String after;
    private String before;
    private Boolean created;
    private Boolean deleted;

}
