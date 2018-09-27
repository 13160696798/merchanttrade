/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.mybank.bkmerchant.trade;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.mybank.bkmerchant.base.HttpsMain;
import com.mybank.bkmerchant.util.XmlSignUtil;
import com.mybank.bkmerchant.util.XmlUtil;

/**
 * 4.2.8    H5支付（主扫）创建订单接口
 * @author simon.xxm
 * @version $Id: BalanceQuery.java, v 0.1 2016年3月3日 下午6:33:49 simon.xxm Exp $
 */
public class PrePay {

    /**
     * @author simon.xxm
     * @version $Id: BalanceQuery.java, v 0.1 2016年3月3日 下午6:33:49 simon.xxm Exp $
     * @param args      
     */
    public static void main(String[] args) throws Exception{
    	//账户余额查询
        String function = "ant.mybank.bkmerchanttrade.prePay";

        XmlUtil xmlUtil = new XmlUtil();
        Map<String, String> form = new HashMap<String, String>();
        form.put("Function", function);
        form.put("ReqTime", new Timestamp(System.currentTimeMillis()).toString());
        //reqMsgId每次报文必须都不一样
        form.put("ReqMsgId", UUID.randomUUID().toString());
        form.put("OutTradeNo",UUID.randomUUID().toString().replace("-",""));
        form.put("Body","主扫测试-咖啡");
        form.put("GoodsTag","test");
        form.put("GoodsDetail","test");
        form.put("TotalAmount","2");
        form.put("Currency","CNY");
        form.put("MerchantId",HttpsMain.merchantId);
        form.put("IsvOrgId", HttpsMain.IsvOrgId);
        form.put("ChannelType","WX");
        form.put("OpenId","test");
        form.put("OperatorId","test");
        form.put("StoreId","test");
        form.put("DeviceId","test");
        form.put("DeviceCreateIp","127.0.0.1");
        form.put("ExpireExpress","60");
        form.put("SettleType","T1");
        form.put("Attach","test");

        //form.put("NotifyUrl","http://notifyurl");
        form.put("SubAppId","wx62a55dbdd041bb1d");
        form.put("SpecifySubMerchId","N");
        form.put("ChannelId","240824008");
        form.put("SubMerchId","243108596");
        form.put("PayLimit","pcredit");
        form.put("DiscountableAmount","1");
        form.put("UndiscountableAmount","1");
        form.put("AlipayStoreId","支付宝的店铺编号");
        form.put("SysServiceProviderId","2018090700000286");
        form.put("CheckLaterNm","");
        //封装报文
        String param = xmlUtil.format(form, function);
        if (HttpsMain.isSign) {//生产环境需进行rsa签名
            param = XmlSignUtil.sign(param);
        }
        System.out.println(param);
        //发送请求
        String response = HttpsMain.httpsReq(HttpsMain.reqUrl, param);
        System.out.println(response);
        if (HttpsMain.isSign) {//生产环境需进行rsa验签
            if (!XmlSignUtil.verify(response)) {
                throw new Exception("验签失败");
            }
        }
        //解析报文
        Map<String, Object> resMap = xmlUtil.parse(response, function);
        //结果消息
        String b=(String)resMap.get("OutTradeNo");
        System.out.println(b);

    }

}
