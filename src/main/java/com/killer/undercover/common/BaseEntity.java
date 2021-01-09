package com.killer.undercover.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * 通用Entity
 * @author: huanghuiqiang
 * @create: 21.1.9 10:08
 */
@Data
@Accessors(chain = true)
public abstract class BaseEntity {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(notes = "自增长ID")
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(notes = "创建时间")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(notes = "更新时间")
    private LocalDateTime updateTime;

}
