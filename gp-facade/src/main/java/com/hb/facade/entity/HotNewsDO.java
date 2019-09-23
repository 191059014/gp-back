package com.hb.facade.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.annotation.SelfTableClass;
import com.hb.unic.base.annotation.SelfTableColumn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ========== 热点资讯 ==========
 *
 * @author Mr.huang
 * @version OrderDO.java, v1.0
 * @date 2019年09月20日 22时09分
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "热点资讯模型")
@SelfTableClass(value = "t_hotnews", comment = "热点资讯表")
public class HotNewsDO extends BaseDO {

    private static final long serialVersionUID = -8024655613166163485L;

    @ApiModelProperty(value = "热点资讯ID")
    @SelfTableColumn(value = "id", id = true, comment = "热点资讯ID")
    private Integer id;

    @ApiModelProperty(value = "标题")
    @SelfTableColumn(value = "title", comment = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    @SelfTableColumn(value = "content", comment = "内容")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HotNewsDO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}' + super.toString();
    }
}
