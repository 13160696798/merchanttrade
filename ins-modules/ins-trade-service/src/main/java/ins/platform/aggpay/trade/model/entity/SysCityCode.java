package ins.platform.aggpay.trade.model.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 省市区-城市代码映射表
 * </p>
 *
 * @author zhangyu
 * @since 2018-10-08
 */
@Data
@TableName("sys_city_code")
public class SysCityCode extends Model<SysCityCode> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 中文简称
     */
    private String name;
    /**
     * 地区码
     */
    private String code;
    /**
     * 区域名称
     */
    @TableField("area_name")
    private String areaName;
    /**
     * 上级区域编码
     */
    @TableField("superior_code")
    private String superiorCode;
    /**
     * 是否有效 - 1：有效，0：无效
     */
    @TableField("valid_ind")
    private String validInd;
    /**
     * 创建人代码
     */
    @TableField("creator_code")
    private String creatorCode;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新人代码
     */
    @TableField("updater_code")
    private String updaterCode;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;




    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysCityCode{" +
        ", id=" + id +
        ", name=" + name +
        ", code=" + code +
        ", areaName=" + areaName +
        ", superiorCode=" + superiorCode +
        ", validInd=" + validInd +
        ", creatorCode=" + creatorCode +
        ", createTime=" + createTime +
        ", updaterCode=" + updaterCode +
        ", updateTime=" + updateTime +
        "}";
    }
}
