package com.hb.facade.entity;

public class StockListDO extends BaseDO {
    private Integer id;

    private String code;

    private String name;

    private String full_code;

    private Byte type;

    private Integer state;

    public StockListDO() {
    }

    public StockListDO(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_code() {
        return full_code;
    }

    public void setFull_code(String full_code) {
        this.full_code = full_code;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "StockListDO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", full_code='" + full_code + '\'' +
                ", type=" + type +
                ", state=" + state +
                '}' + "," + super.toString();
    }
}