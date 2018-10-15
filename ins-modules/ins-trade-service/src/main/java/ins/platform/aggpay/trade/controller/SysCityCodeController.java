package ins.platform.aggpay.trade.controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import ins.platform.aggpay.common.constant.CommonConstant;
import ins.platform.aggpay.common.util.Query;
import ins.platform.aggpay.common.util.R;
import ins.platform.aggpay.trade.model.entity.SysCityCode;
import ins.platform.aggpay.trade.service.SysCityCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ins.platform.aggpay.common.web.BaseController;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 省市区-城市代码映射表 前端控制器
 * </p>
 *
 * @author zhangyu
 * @since 2018-10-08
 */
@RestController
@RequestMapping("/sysCityCode")
public class SysCityCodeController extends BaseController {
    @Autowired private SysCityCodeService sysCityCodeService;

    /**
    * 通过ID查询
    *
    * @param id ID
    * @return SysCityCode
    */
    @GetMapping("/{id}")
    public R<SysCityCode> get(@PathVariable Integer id) {
        return new R<>(sysCityCodeService.selectById(id));
    }


    /**
    * 分页查询信息
    *
    * @param params 分页对象
    * @return 分页对象
    */
    @RequestMapping("/page")
    public Page page(@RequestParam Map<String, Object> params) {
        params.put(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        return sysCityCodeService.selectPage(new Query<>(params), new EntityWrapper<>());
    }

    /**
     * 添加
     * @param  sysCityCode  实体
     * @return success/false
     */
    @PostMapping
    public R<Boolean> add(@RequestBody SysCityCode sysCityCode) {
        return new R<>(sysCityCodeService.insert(sysCityCode));
    }

    /**
     * 删除
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        SysCityCode sysCityCode = new SysCityCode();
        sysCityCode.setId(id);
        sysCityCode.setUpdateTime(new Date());
        return new R<>(sysCityCodeService.updateById(sysCityCode));
    }

    /**
     * 编辑
     * @param  sysCityCode  实体
     * @return success/false
     */
    @PutMapping
    public R<Boolean> edit(@RequestBody SysCityCode sysCityCode) {
        sysCityCode.setUpdateTime(new Date());
        return new R<>(sysCityCodeService.updateById(sysCityCode));
    }

}
