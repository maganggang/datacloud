package com.fei2e.anypay.util;

import com.fei2e.anypay.config.WeiXinConfig;
import com.github.wxpay.sdk.WXPayUtil;

import java.util.Iterator;
import java.util.Map;
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
 public static SortedMap<Object, Object> MapToMap(Map<String,String> map) {
     //过滤空 设置 TreeMap
     SortedMap<Object, Object> packageParams = new TreeMap<>();
     Iterator it = map.keySet().iterator();
     while (it.hasNext()) {
         String parameter = (String) it.next();
         String parameterValue = map.get(parameter);
         String v = "";
         if (null != parameterValue) {
             v = parameterValue.trim();
         }
         packageParams.put(parameter, v);
     }
     return packageParams;
 }
}
