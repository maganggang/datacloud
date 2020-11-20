package com.fei2e.anypay.util;

import com.fei2e.anypay.config.WeiXinConfig;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @ClassName ConfigUtil
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/20 11:34
 * @Version 1.0
 **/
public class ConfigUtil {
    /**
     * 微信创建
     * @param config
     * @return
     */
    public static SortedMap<Object, Object> commonParams(WeiXinConfig config) {
        // 账号信息
        String appid = config.getAppId(); // appid
        String mch_id = config.getMchId(); // 商业号
        // 生成随机字符串
        String currTime = PayCommonUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = PayCommonUtil.buildRandom(4) + "";
        String nonce_str = strTime + strRandom;
        SortedMap<Object, Object> sortedMap=new  TreeMap<Object, Object>();
        sortedMap.put("appid", appid);// 公众账号ID
        sortedMap.put("mch_id", mch_id);// 商户号
        sortedMap.put("nonce_str", nonce_str);// 随机字符串
        return sortedMap;
    }

}
