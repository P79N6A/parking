package com.zhuyitech.sms.ruanwei.config;


import com.alibaba.fastjson.JSON;

/**
 * 消息内容 Map
 * 形式的 JSON
 * 串，身份证号码
 * 作为 MapKey
 * <p>
 */
public class IdCheckMsg {

    private String ywlx; //业务类型 仅做长度校验
    private String sessionId; //序号 可以为空
    private String gmsfhm; //公民身份证号码
    private String xm; //姓名
    private String fsd; //发生地 仅做长度校验
    private String account; //账号 分配的账号
    private String xp; //相片 为空
    private String action = "qsfz"; //固定值：qsfz
    private String cid; //客户请求唯一序号 由请求方生成, 为空时服务端 自动生成

    public String toJsonString() {
       /* IdCheckMsg msg = new IdCheckMsg();
        msg.setYwlx("check identity");
        msg.setSessionId("");
        msg.setGmsfhm("130432198706110315");
        msg.setXm("陈磊");
        msg.setFsd("北京");
        msg.setAccount("sin7");
        msg.setXp("");
        msg.setAction("qsfz");
        msg.setCid("sin718600992571");*/
        return "{\"" + this.getGmsfhm() + "\":" + JSON.toJSONString(this) + "}";
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getGmsfhm() {
        return gmsfhm;
    }

    public void setGmsfhm(String gmsfhm) {
        this.gmsfhm = gmsfhm;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getFsd() {
        return fsd;
    }

    public void setFsd(String fsd) {
        this.fsd = fsd;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getXp() {
        return xp;
    }

    public void setXp(String xp) {
        this.xp = xp;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
