package com.mybank.bkmerchant.merchant;

import com.mybank.bkmerchant.base.AbstractReq;
import com.mybank.bkmerchant.base.HttpsMain;

import java.util.HashMap;
import java.util.Map;

/**
 * 5.2.4	微信子商户支付配置接口
 *
 * 请求报文
 OutTradeNo	外部交易号
 IsvOrgId	合作方机构号（网商银行分配）
 MerchantId 商户号。网商为商户分配的商户号，通过商户入驻结果查询接口获取。
 Appid  关联APPID(Subappid)
 Path   JS API支付授权目录 ，要求符合URI格式规范，每次添加一个支付目录，最多5个
 SubscribeAppid 特约商户或渠道号的公众号APPID

 应答报文
 RespInfo	返回码组件。Result Status 返回S不代表入驻成功，只代表申请单据已经查询到，结果需要看RegisterStatus字段。
 OutTradeNo	外部交易号
 */
public class AddMerchantConfig extends AbstractReq{
    private String outTradeNo;
    private String isvOrgId = HttpsMain.IsvOrgId;
    private String merchantId = HttpsMain.merchantId;
    private String appid = HttpsMain.appId;
//    private String path;
    private String subscribeAppid;
    private Map<String,String> body;

    public AddMerchantConfig(String outTradeNo,String subscribeAppid){
        super("ant.mybank.merchantprod.merchant.addMerchantConfig");
        this.outTradeNo = outTradeNo;
        this.subscribeAppid = subscribeAppid;

        this.body = new HashMap<String,String>();
        body.put("OutTradeNo",outTradeNo);
        body.put("IsvOrgId",this.isvOrgId);
        body.put("MerchantId",this.merchantId);
        body.put("Appid",this.appid);
        body.put("SubscribeAppid",subscribeAppid);
    }

    @Override
    public Map<String, String> getBody() {
        return this.body;
    }

    public static void main(String[] args) throws Exception{
        String outTradeNo = "98910a7bc0ae4cafa3e82bfa30a6f7d5";
        String subscribeAppid = "240824008";

        AddMerchantConfig addMerchantConfig = new AddMerchantConfig(outTradeNo,subscribeAppid);
        Map<String,Object> rst = addMerchantConfig.call();
        System.out.println((String) rst.get("OutTradeNo"));
    }
}
