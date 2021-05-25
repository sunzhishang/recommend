package cn.szsyph.dcd.exception;


import cn.szsyph.dcd.enums.ErrorEnum;
import lombok.Getter;

/**
 * @Description 异步任务业务异常
 * @Author chenlinghong
 * @Version V1.0
 */
@Getter
public class AsyncBusinessException extends RuntimeException {

    private static final long serialVersionUID = -8053155122859291515L;
    /**
     * 错误码
     */
    private int code;

    public AsyncBusinessException(ErrorEnum errorEnum) {
        super(errorEnum.getMsg());
        this.code = errorEnum.getCode();
    }

    public AsyncBusinessException(String message, Throwable throwable) {
        super(message, throwable);
        this.code = ErrorEnum.UNKNOWN_ERROR.getCode();
    }

}
