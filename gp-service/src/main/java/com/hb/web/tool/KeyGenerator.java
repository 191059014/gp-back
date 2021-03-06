package com.hb.web.tool;

import com.hb.unic.cache.service.impl.RedisCacheServiceImpl;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.util.DateUtils;
import com.hb.unic.util.util.StringUtils;
import com.hb.facade.enumutil.TableEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

/**
 * ========== ID生成器 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.helper.KeyGenerator.java, v1.0
 * @date 2019年06月11日 15时25分
 */
@Component
public class KeyGenerator {
    /**
     * slf4j log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(KeyGenerator.class);

    private static final String FORMAT_MS = "yyMMddHHmmssSSS";
    private static final int SEQUENCE_LENGTH = 5;
    private static final Long SEQUENCE_MAX = 10000L;

    @Autowired
    private RedisCacheServiceImpl redisCacheServiceImpl;

    /**
     * ########## 生成唯一ID ##########
     * 前缀（2） + 时间戳（15） + 随机数（4） + 序列（5）= 26位
     *
     * @return ID
     */
    public String generateKey(TableEnum tableEnum) {
        String dataStr = DateUtils.date2str(DateUtils.getCurrentDate(), FORMAT_MS);
        String random = String.valueOf(new Random().nextInt(1000));
        random = StringUtils.fillZero(random, 4);
        Long nextValue = redisCacheServiceImpl.getNextValue(tableEnum.getSequenceKey());
        if (nextValue == null) {
            nextValue = 0L;
            redisCacheServiceImpl.set(tableEnum.getSequenceKey(), nextValue);
        }
        if (nextValue.compareTo(SEQUENCE_MAX) > 0) {
            redisCacheServiceImpl.delete(tableEnum.getSequenceKey());
        }
        String sequenceId = String.valueOf(nextValue);
        sequenceId = StringUtils.fillZero(sequenceId, SEQUENCE_LENGTH);
        LOGGER.info("dateStr:{}", dataStr);
        LOGGER.info("random:{}", random);
        LOGGER.info("sequenceId:{}", sequenceId);
        return new StringBuilder(tableEnum.getKeyPrefix()).append(dataStr).append(random).append(sequenceId).toString();
    }

    /**
     * ########## UUID ##########
     *
     * @return UUID
     */
    public String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
