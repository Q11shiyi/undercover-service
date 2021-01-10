package com.killer.undercover.enums;

/**
 * 游戏状态枚举
 * @author: huanghuiqiang
 * @create: 21.1.9 22:59
 */
public enum GameStatusEnum {

    /**
     * 游戏状态枚举
     */
    START("START", "开始游戏"),
    KILL("KILL", "杀死玩家")
    ;

    /**
     * 编码
     */
    private final String code;

    /**
     * 名称
     */
    private final String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    GameStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(String code) {
        for (GameStatusEnum e : GameStatusEnum.values()) {
            if (e.getCode().equals(code)) {
                return e.getName();
            }
        }
        return "未知类型";
    }

    public static GameStatusEnum getEnumByCode(String code) {
        for (GameStatusEnum e : GameStatusEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}