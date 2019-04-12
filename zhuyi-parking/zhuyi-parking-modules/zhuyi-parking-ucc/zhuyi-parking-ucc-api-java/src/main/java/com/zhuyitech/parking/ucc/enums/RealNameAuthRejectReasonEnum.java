package com.zhuyitech.parking.ucc.enums;

/**
 * 用户实名认证驳回原因
 *
 * @author AkeemSuper
 * @date 2018/4/20 0020
 */
public enum RealNameAuthRejectReasonEnum {

    NAME_NOT_COINCIDE("1", "姓名与图片不一致 "),
    CARD_NUMBER_NOT_COINCIDE("2", "身份证与图片不一致  "),
    CARD_IMAGE_ERROR("3", "身份证照片上传错误"),
    IMAGE_INDISTINCT("4", "图片不清晰，请重新上传"),
    FACE_NOT_MATCH_IMAGE("5", "身份证证件照与人脸照片不符"),
    OTHER("6", "其他");

    /**
     * 值
     */
    private String value;

    /**
     * 注释
     */
    private String comment;

    public String getValue() {
        return value;
    }

    public String getComment() {
        return this.comment;
    }

    RealNameAuthRejectReasonEnum(String value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static RealNameAuthRejectReasonEnum parse(String value) {
        if (value != null) {
            RealNameAuthRejectReasonEnum[] array = values();
            for (RealNameAuthRejectReasonEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
