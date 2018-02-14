package com.yueqinxuexi.wx.wxproject.test;

import org.junit.Assert;
import org.junit.Test;
import com.yueqinxuexi.wx.wxproject.com.yueqinxuexi.wx.common.util;

public class UtilTest extends BaseJunitTest {

    @Test
    public void Test() {
        util utilObject = new util();
        boolean res = utilObject.checkSignature("29d9686a962b3b63b69faeca58a3f49c22dbd3bb",
                "1518562860",
                "3715492920");
        // Assert.assertEquals("The checkSignature is right!",True,res);
        Assert.assertTrue("The checkSignature is right!", res);
    }
}
