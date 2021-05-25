package cn.szsyph.dcd.enums;

import lombok.Getter;

/**
 * @Description 用户行为枚举类
 * @Author sunzhishang
 * @Version V1.0
 */
@Getter
public enum UserBehaviorEnum {

    //行为: 点击, 收藏,评价
    CLICK(1, "点击", 2),
    PIN(5, "收藏", 5),
    COMMENT_SCORE_1(11, "评价-1分", -2),
    COMMENT_SCORE_2(12, "评价-2分", -1),
    COMMENT_SCORE_3(13, "评价-3分", 1),
    COMMENT_SCORE_4(14, "评价-4分", 2),
    COMMENT_SCORE_5(15, "评价-5分", 3);

    /**
     * 行为码 对应数据库存储数值
     */
    private final int code;

    /**
     * message
     */
    private final String msg;

    /**
     * 因子，指用户行为对用户偏好的影响因素，值越大说明影响越大
     */
    private final Integer factor;

    UserBehaviorEnum(int code, String msg, int factor) {
        this.code = code;
        this.msg = msg;
        this.factor = factor;
    }


    public static UserBehaviorEnum getBehaviorEnumByGrade(int grade) {
        UserBehaviorEnum result = null;
        for (UserBehaviorEnum behaviorEnum : UserBehaviorEnum.values()) {
            if (behaviorEnum.getCode() - 10 == grade) {
                result = behaviorEnum;
                break;
            }
        }
        return result;
    }
}
