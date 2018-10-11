/*
 * Copyright (c) 2018-2020, Ripin Yan. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ins.platform.aggpay.trade.service.impl;

import ins.platform.aggpay.trade.common.util.MapUtil;
import ins.platform.aggpay.trade.entity.GgMerchant;
import ins.platform.aggpay.trade.mapper.GgMerchantMapper;
import ins.platform.aggpay.trade.service.GgMerchantService;
import ins.platform.aggpay.trade.vo.MerchantResVo;
import ins.platform.aggpay.trade.vo.MerchantVo;
import ins.platform.aggpay.trade.vo.RegistResVo;
import ins.platform.aggpay.trade.vo.RegisterQueryVo;
import ins.platform.aggpay.trade.vo.UploadPhotoVo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybank.bkmerchant.constant.BizTypeEnum;
import com.mybank.bkmerchant.merchant.Freeze;
import com.mybank.bkmerchant.merchant.MerchantQuery;
import com.mybank.bkmerchant.merchant.Register;
import com.mybank.bkmerchant.merchant.RegisterQuery;
import com.mybank.bkmerchant.merchant.Unfreeze;
import com.mybank.bkmerchant.merchant.UpdateMerchant;
import com.mybank.bkmerchant.merchant.UploadPhoto;
import com.mybank.bkmerchant.trade.SendSmsCode;

/**
 * <p>
 * 商户信息主表 服务实现类
 * </p>
 *
 * @author ripin
 * @since 2018-09-18
 */
@Service
public class GgMerchantServiceImpl extends ServiceImpl<GgMerchantMapper, GgMerchant> implements GgMerchantService {

	@Autowired
	private GgMerchantMapper ggMerchantMapper;
	
	@Override
	public RegistResVo regist(Register register) {

		try {
			Map<String, Object> call = register.call();
			Object resultStatus = call.get("resultStatus");
			if(resultStatus.equals("S")){

				//update merchant 入驻结果

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		merchantMapper.

		return null;
	}

	@Override
	public RegisterQueryVo registerQuery(String isvOrgId, String orderNo) {
		RegisterQuery rq = new RegisterQuery(orderNo);
		RegisterQueryVo vo = null;
		try {
			Map<String, Object> call = rq.call();
			vo = MapUtil.map2Obj(call,RegisterQueryVo.class);
			System.out.println(vo.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vo;
	}

	@Override
	public MerchantResVo sendsmscode(SendSmsCode sendSmsCode) {
		MerchantResVo rs = null;
		try {
			Map<String, Object> result =  SendSmsCode.sendSmsCodeReturnMap(sendSmsCode.getMerchantId()
					, sendSmsCode.getOutTradeNo()
					, BizTypeEnum.getBizTypeByCode(sendSmsCode.getBizType())
					, sendSmsCode.getMobile());
			rs = MapUtil.map2Obj(result, MerchantResVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public UploadPhotoVo uploadphoto(UploadPhoto uploadPhoto) {
		UploadPhotoVo rs = null;
		try {
			Map<String, Object> result = uploadPhoto.call();
			rs = MapUtil.map2Obj(result, UploadPhotoVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	@Override
	public MerchantResVo updateMerchant(UpdateMerchant updateMerchant) {
//		UpdateMerchant.updateMerchant(merchantId, outTradeNo);
		
		return null;
	}

	@Override
	public MerchantVo merchantQuery(String isvOrgId, String merchantId) {
		MerchantQuery merchantQuery = new MerchantQuery();
		
		MerchantVo rs = null;
		try {
			Map<String, Object> result = merchantQuery.call();
			rs = MapUtil.map2Obj(result, MerchantVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public MerchantResVo merchantFreeze(String isvOrgId, String merchantId,
			String freezeReason, String outTradeNo) {
		Freeze freeze = new Freeze(freezeReason,outTradeNo);
		Map<String, Object> result = null;
		try {
			result = freeze.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		MerchantResVo rs = MapUtil.map2Obj(result, MerchantResVo.class);
		return rs;
	}

	@Override
	public MerchantResVo merchantUnfreeze(String isvOrgId, String merchantId,
			String unfreezeReason, String outTradeNo) {
		Unfreeze freeze = new Unfreeze(unfreezeReason,outTradeNo);
		Map<String, Object> result = null;
		try {
			result = freeze.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		MerchantResVo rs = MapUtil.map2Obj(result, MerchantResVo.class);
		return rs;
	}

}