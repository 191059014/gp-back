package com.hb.facade.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 修改密码 ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.request.ModifyPasswordRequestVO.java, v1.0
 * @date 2019年08月27日 08时13分
 */
public class ModifyPasswordRequestVO implements Serializable {

    private static final long serialVersionUID = -4888033904882923605L;

    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "ModifyPasswordRequestVO{" +
                "newPassword='" + newPassword + '\'' +
                '}';
    }
}
