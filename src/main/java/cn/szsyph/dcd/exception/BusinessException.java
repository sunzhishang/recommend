package cn.szsyph.dcd.exception;


import cn.szsyph.dcd.enums.ErrorEnum;
import lombok.Getter;

/**
 * @Description 业务异常
 * @Author chenlinghong
 **/
@Getter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 3625697516019055447L;
    /**
     * 异常码
     */
    private final Integer code;

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.code = ErrorEnum.UNKNOWN_ERROR.getCode();
    }

    public BusinessException(ErrorEnum errorEnum) {
        super(errorEnum.getMsg());
        this.code = errorEnum.getCode();
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
}
