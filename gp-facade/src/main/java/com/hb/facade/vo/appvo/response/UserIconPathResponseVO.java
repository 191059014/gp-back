package com.hb.facade.vo.appvo.response;

import java.io.Serializable;
import java.util.Set;

public class UserIconPathResponseVO implements Serializable {

    private static final long serialVersionUID = 1249609902137505839L;

    private Set<String> pathSet;

    public UserIconPathResponseVO(Set<String> pathSet) {
        this.pathSet = pathSet;
    }

    public Set<String> getPathSet() {
        return pathSet;
    }

    public void setPathSet(Set<String> pathSet) {
        this.pathSet = pathSet;
    }

    @Override
    public String toString() {
        return "UserIconPathResponseVO{" +
                "pathSet=" + pathSet +
                '}';
    }
}
