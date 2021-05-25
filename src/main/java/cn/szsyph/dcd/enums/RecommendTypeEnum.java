package cn.szsyph.dcd.enums;

import lombok.Getter;

/**
 * @Description 推荐类型枚举
 * @Author sunzhishang
 * @Version V1.0
 */
@Getter
public enum RecommendTypeEnum {

    USER_BASED_RECOMMEND(1, "基于用户的协同过滤推荐"),
    ITEM_BASED_RECOMMEND(2, "基于物品的协同过滤推荐"),
    POPULAR_RECOMMEND(3, "热门推荐");

    /**
     * 类型编码
     */
    private final int code;

    /**
     * 说明
     */
    private final String msg;

    RecommendTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取推荐类型
     */
    public static RecommendTypeEnum getByCode(int recommendType) {
        RecommendTypeEnum result = null;
        if (recommendType == RecommendTypeEnum.USER_BASED_RECOMMEND.getCode()) {
            result = RecommendTypeEnum.USER_BASED_RECOMMEND;
        } else if (recommendType == RecommendTypeEnum.ITEM_BASED_RECOMMEND.getCode()) {
            result = RecommendTypeEnum.ITEM_BASED_RECOMMEND;
        } else if (recommendType == RecommendTypeEnum.POPULAR_RECOMMEND.getCode()) {
            result = RecommendTypeEnum.POPULAR_RECOMMEND;
        }
        return result;
    }

}
