package ins.platform.aggpay.trade.controller;

import ins.platform.aggpay.common.web.BaseController;
import ins.platform.aggpay.trade.service.PrePayNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 创建订单结果通知
 * Created by ADD on 2018/10/10.
 */
@Controller
@RequestMapping("/prePay")
public class PrePayNoticeController extends BaseController {
    @Autowired
    private PrePayNoticeService prePayNoticeService;
    /**
     * 异步通知
     * @param  param  参数
     * @return success/false
     */
    @PostMapping
    public String view(String param) throws Exception{
        return prePayNoticeService.payFinish(param);
    }


}



