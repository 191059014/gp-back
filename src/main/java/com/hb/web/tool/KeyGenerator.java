package com.hb.web.tool;

import com.hb.web.constant.enumutil.TableEnum;
import com.hb.web.util.DateUtils;
import com.hb.web.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

/**
 * ========== ID生成器 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.tool.KeyGenerator.java, v1.0
 * @date 2019年06月11日 15时25分
 */
@Component
public class KeyGenerator {

    private static String FORMAT_MS = "yyMMddHHmmssSSS";

    public static void main(String[] args) {
        System.out.println(generateKey(TableEnum.T_AGENT));
    }

    /**
     * ########## 生成唯一ID ##########
     * 前缀 + 时间戳 + 随机数 = 20位
     *
     * @return ID
     */
    public static synchronized String generateKey(TableEnum tableEnum) {
        String dataStr = DateUtils.date2str(DateUtils.getCurrentDate(), FORMAT_MS);
        String random = String.valueOf(new Random().nextInt(100));
        String fillZero = StringUtils.fillZero(random, 4);
        System.out.println("dateStr:" + dataStr);
        System.out.println("fillZero:" + fillZero);
        return new StringBuilder(tableEnum.getKeyPrefix()).append(dataStr).append(fillZero).toString();
    }

    /**
     * ########## UUID ##########
     *
     * @return UUID
     */
    public String getUUID() {
        return UUID.randomUUID().toString();
    }

}
