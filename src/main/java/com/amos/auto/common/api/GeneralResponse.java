package com.amos.auto.common.api;

import lombok.Data;
import lombok.experimental.Accessors;
import reactor.core.publisher.Mono;

/**
 * DESCRIPTION: GeneralResponse
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/5/22
 */
@Data
@Accessors(chain = true)
public class GeneralResponse<T> {

    private String code;

    private String message;

    private T body;

    public GeneralResponse(CodeRespEnum codeResp) {
        this(codeResp.code, codeResp.message);
    }

    public GeneralResponse(CodeRespEnum codeResp, T body) {
        this(codeResp.code, codeResp.message);
        this.body = body;
    }

    public GeneralResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <R> Mono<GeneralResponse<R>> ofSuccess(R body) {
        return ofMono(new GeneralResponse<>(CodeRespEnum.SUCCESS, body));
    }

    public static <R> Mono<GeneralResponse<R>> ofSuccess() {
        return ofMono(new GeneralResponse<>(CodeRespEnum.SUCCESS));
    }

    public static <R> Mono<GeneralResponse<R>> ofFail() {
        return ofMono(new GeneralResponse<>(CodeRespEnum.SERVER_ERROR));
    }

    public static <R> Mono<GeneralResponse<R>> ofErrorParam(String field) {
        return ofMono(new GeneralResponse<>(
                CodeRespEnum.ERROR_PARAM.code,
                String.format(CodeRespEnum.ERROR_PARAM.message, field)
        ));
    }

    public static <R> Mono<GeneralResponse<R>> ofIllegal() {
        return ofMono(new GeneralResponse<>(CodeRespEnum.ILLEGAL_REQUEST));
    }

    public static <R> Mono<GeneralResponse<R>> ofMono(GeneralResponse<R> response) {
        return Mono.justOrEmpty(response);
    }

    private enum CodeRespEnum {
        /***/
        SUCCESS("2000", "成功"),
        ERROR_PARAM("4000", "参数错误[%s]"),
        ILLEGAL_REQUEST("4001", "非法请求"),
        SERVER_ERROR("5000", "系统异常");

        CodeRespEnum(String code, String message) {
            this.code = code;
            this.message = message;
        }

        private final String code;
        private final String message;
    }

}
