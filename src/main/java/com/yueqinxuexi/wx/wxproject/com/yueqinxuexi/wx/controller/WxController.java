package com.yueqinxuexi.wx.wxproject.com.yueqinxuexi.wx.controller;

import com.yueqinxuexi.wx.wxproject.com.yueqinxuexi.wx.common.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WxController {
    private static Logger LOG = LoggerFactory.getLogger(WxController.class);


    @RequestMapping(value = "/wx", method = RequestMethod.GET)
    public String checkSignature(@RequestParam(name = "signature", required = false) String signature,
                                 @RequestParam(name = "timestamp", required = false) String timestamp,
                                 @RequestParam(name = "nonce", required = false) String nonce,
                                 @RequestParam(name = "echostr", required = false) String echostr
    ) {
        String result = null;
        if(util.checkSignature(signature,timestamp,nonce)){
            LOG.info("验证成功");
            result = echostr;
        }
        else {
            LOG.info("验证失败");
        }
        return result;
    }
}
