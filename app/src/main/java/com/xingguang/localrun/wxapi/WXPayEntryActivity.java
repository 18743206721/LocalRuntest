package com.xingguang.localrun.wxapi;

import android.util.Log;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xingguang.core.base.BaseActivity;
import com.xingguang.core.utils.ToastUtils;

/**
 * 创建日期：2018/6/11
 * 描述:
 * 作者:LiuYu
 */
public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {


    private IWXAPI api;

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        api = WXAPIFactory.createWXAPI(this,null);
        api.registerApp("");
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d("zjp", "onPayFinish, errCode = " + resp.errCode);// 支付结果码 /** * 结果码参考：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=8_5 */
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int code = resp.errCode;
            switch (code) {
                case 0:
                    ToastUtils.showToast(this,"支付成功");
                    break;
                case -1:
                    // 支付失败 可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等
                    ToastUtils.showToast(this,"支付失败");
                    break;
                case -2:
                    ToastUtils.showToast(this,"支付取消");
                    break;
            }
            this.finish();
        }



    }
}