package cn.szsyph.dcd.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description API返回模板类
 * @Author sunzhishang
 **/
@Data
public class ResultVo implements Serializable {

    private static final long serialVersionUID = 3535016020293872262L;
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 数据
     */
    private Object data;

}
