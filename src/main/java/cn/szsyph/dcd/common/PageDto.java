package cn.szsyph.dcd.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 分页对象
 * @Author sunzhishang
 **/
@Data
@AllArgsConstructor
public class PageDto<T> implements Serializable {

    private static final long serialVersionUID = -8123061148193228577L;

    /**
     * 数据列表
     */
    private List<T> data;

    /**
     * 页码
     */
    private long pageNo;

    /**
     * 每页条数
     */
    private long pageSize;

    /**
     * 总条数
     */
    private long totalCount;

    /**
     * 构造函数
     *
     * @param data
     */
    public PageDto(List<T> data) {
        this.data = data;
        this.pageNo = 1;
        this.pageSize = data.size();
    }

}
