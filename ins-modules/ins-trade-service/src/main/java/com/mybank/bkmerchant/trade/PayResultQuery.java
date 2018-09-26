package com.mybank.bkmerchant.trade;

import com.mybank.bkmerchant.base.HttpsMain;
import com.mybank.bkmerchant.util.XmlSignUtil;
import com.mybank.bkmerchant.util.XmlUtil;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 5.2.20	他行卡结算查询接口< ant.mybank.bkmerchantsettle.payResultQuery>
 *请求报文
 IsvOrgId	合作方机构号（网商银行分配）
 MerchantId 商户号。网商为商户分配的商户号，通过商户入驻结果查询接口获取。
 PayDate    打款日期(2017-08-30精确到日期即可)
 QueryMode  查询类型

 *应答报文
 IsvOrgId	合作方机构号（网商银行分配）
 MerchantId 商户号。网商为商户分配的商户号，通过商户入驻结果查询接口获取。
 ResultList 通知列表（base64编码），与打款通知接口的notifyList元素相同
 */
public class PayResultQuery {
    public static void main (String[] args) throws Exception {
        String function = "ant.mybank.bkmerchantsettle.payResultQuery";

        XmlUtil xmlUtil = new XmlUtil();
        Map<String, String> form = new HashMap<String, String>();
        form.put("Function", function);
        form.put("ReqTime", new Timestamp(System.currentTimeMillis()).toString());
        //reqMsgId每次报文必须都不一样
        form.put("IsvOrgId", HttpsMain.IsvOrgId);
        form.put("MerchantId", HttpsMain.merchantId);
        form.put("PayDate", "2018-9-21");
        form.put("QueryMode", "T1");

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
        System.out.println(resMap.toString());
    }
}
