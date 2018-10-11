package ins.platform.aggpay.trade.service.impl;

import com.mybank.bkmerchant.base.HttpsMain;
import com.mybank.bkmerchant.util.XmlSignUtil;
import com.mybank.bkmerchant.util.XmlUtil;
import ins.platform.aggpay.trade.service.PrePayNoticeService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;


/**创建订单结果通知
 * Created by ADD on 2018/10/10.
 */
@Service
public class PrePayNoticeServiceImpl implements PrePayNoticeService {

    @Override
    public String payFinish(String param) throws Exception {
        //对param进行验签
        if (HttpsMain.isSign) {//生产环境需进行rsa验签
            if (!XmlSignUtil.verify(param)) {
                throw new Exception("验签失败");
            }
        }
        XmlUtil xmlUtil = new XmlUtil();
        String function = "ant.mybank.bkmerchanttrade.prePayNotice";
        //解析报文
        Map<String, Object> resMap = xmlUtil.parse(param, function);
        //返回报文参数
        for(Map.Entry<String,Object> entry : resMap.entrySet()){
            Object value = entry.getValue();
            System.out.println(value);
        }


        String resMsgId = (String)resMap.get("resMsgId");
        //返回RespInfo报文
        String response = "<document><response id=\"response\"><head>" +
                "<Version>1.0.0</Version><Appid>2017060700000025</Appid>" +
                "<Function>ant.mybank.bkmerchanttrade.prePayNotice</Function>" +
                "<ResTime>+"+ new Timestamp(System.currentTimeMillis()).toString()+"+</ResTime>" +
                "<ResTimeZone>UTC+8</ResTimeZone>" +
                "<ResMsgId>+"+resMsgId+"+</ResMsgId>" +
                "<Reserve></Reserve>" +
                "<SignType>RSA</SignType>" +
                "<InputCharset>UTF-8</InputCharset></head>" +
                "<body><RespInfo>\n" +
                "<ResultStatus>S</ResultStatus>\n" +
                "<ResultCode>0000</ResultCode>\n" +
                "<ResultMsg>success</ResultMsg>\n" +
                "</RespInfo></body></response></document>";
        //对response进行加签
        if (HttpsMain.isSign) {//生产环境需进行rsa签名
            response = XmlSignUtil.sign(response);
        }
        return response;
    }
}
