package com.hb.web.tool;

import com.hb.web.constant.AppConstant;
import com.hb.web.util.LogUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

/**
 * ========== json文件解析工具 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.app.tool.JsonFileParseTools.java, v1.0
 * @date 2019年06月15日 13时45分
 */
public class JsonFileParseTools {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileParseTools.class);

    public static void main(String[] args) {
        String jsonString = readJsonFile2String("static/app/test.json");
        LOGGER.info(jsonString);
    }

    /**
     * ########## 读取Json文件到字符串 ##########
     *
     * @param path 文件路径
     * @return 字符串
     */
    public static String readJsonFile2String(String path) {
        ClassPathResource classPathResource = new ClassPathResource(path);
        File jsonFile = null;
        String jsonString = StringUtils.EMPTY;
        try {
            jsonFile = classPathResource.getFile();
            jsonString = FileUtils.readFileToString(jsonFile, AppConstant.UTF_8);
        } catch (Exception e) {
            LOGGER.error(LogUtils.appLog("read json file to string exception : {}"), LogUtils.getStackTrace(e));
        }
        return jsonString;
    }

}
