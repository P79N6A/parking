package com.zhuyitech.sms.ruanwei.config;

/**
 */
public enum CheckResEnum {

    Success(100, "请求应答成功"),
    YEBZ(9100, "余额不足 免费 请联系销售处理"),
    WQX(9110, "无模块操作权限 免费 请联系销售核查账户配置"),
    ZHBCZ(9200, "账户不存在 免费 请联系销售处理"),
    MMBZQ(9201, "密码不正确 免费 请联系销售处理"),
    IPWQX(9202, "无权限查询(IP 限制) 免费 请求 IP 未进行备案"),
    INVALIDTOKEN(9300, "无效 token 免费"),
    TOKENMUCH(9301, "请求 token 太频繁 免费 获取一次 token，有效期可进行多次业务核查"),
    REQUESTERROR(1000, "请求格式错误 免费 请按照协议进行参数检查"),
    JGYZ(1100, "结果一致 收费 身份证号码存在并且姓名一致身份证号码也一致时返回"),
    JGBYZ(1101, "结果不一致 收费 身份证号码一致姓名不一致"),
    KZWCH(1103, "库中无此号 先收后返 请到户籍所在地进行核实"),
    SFZYZCW(1190, "身份证验证错误 先收后返"),
    WXQQ(1902, "无效请求 MapKey 免费"),
    WXSFZ(1903, "无效身份证号码 免费"),
    XMBZQ(1904, "姓名不正确 免费 长度不正确或中文乱码"),
    WXXP(1905, "无效相片 免费"),
    YWLXBZQ(1906, "业务类型不正确 免费"),
    WXFSD(1907, "无效发生地 免费"),
    WXQZ(1910, "无效取值 免费"),
    WXSJH(1913, "无效手机号码 免费"),
    YHZHBZQ(1920, "银行账号不正确 免费");

    private int value;
    private String description;

    private CheckResEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static CheckResEnum getByValue(int value) {
        CheckResEnum[] codes = values();
        for (CheckResEnum each : codes) {
            if (each.value == value) {
                return each;
            }
        }
        return null;
    }
}
