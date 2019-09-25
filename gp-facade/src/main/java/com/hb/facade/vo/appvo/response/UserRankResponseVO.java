package com.hb.facade.vo.appvo.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * 用户排行榜
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRankResponseVO implements Serializable {

    private static final long serialVersionUID = -1266702903010172319L;

    private List<Rank> rankList;

    public List<Rank> getRankList() {
        return rankList;
    }

    public void setRankList(List<Rank> rankList) {
        this.rankList = rankList;
    }

    @Override
    public String toString() {
        return "UserRankResponseVO{" +
                "rankList=" + rankList +
                '}';
    }

}
