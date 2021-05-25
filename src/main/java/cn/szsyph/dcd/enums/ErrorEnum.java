package cn.szsyph.dcd.enums;

import lombok.Getter;

/**
 * @Description 错误码枚举类
 * @Author sunzhishang
 **/
@Getter
public enum ErrorEnum {

    SUCCESS(0, "请求成功"),


    PARAM_IS_NULL(2000, "参数为空"),
    PARAM_ILLEGAL(2001, "参数非法"),
    TELEPHONE_ILLEGAL(2002, "电话号码非法"),
    SERVER_ERROR(2222, "服务器未知错误"),
    PARAM_ERROR(2003, "参数错误"),
    FILE_HANDLE_ERROR(2004, "文件处理异常"),
    FILE_IS_NULL(2005, "文件不存在"),
    FILE_STREAM_CREATE_ERROR(2006, "文件流创建错误"),
    UNKNOWN_ERROR(2007, "未知错误"),
    DELETE_ERROR(2008, "删除失败"),

    SESSION_DATA_IS_NULL(4000, "Session数据为空"),


    CHAT_INSERT_ERROR(5000, "消息写入数据库失败"),


    COMMENT_INSERT_ERROR(6000, "添加评论失败"),
    FAILED_TO_INSERT_SHOPPING_CART(6001, "添加购物车失败"),
    NO_GOODS(6002, "商品不存在");

    private final Integer code;

    /**
     * 错误信息
     */
    private final String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
