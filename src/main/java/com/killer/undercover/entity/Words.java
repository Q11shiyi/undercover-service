package com.killer.undercover.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.killer.undercover.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 词语表
 *
 * @author: huanghuiqiang
 * @create: 21.1.9 10:15
 */
@ApiModel(value = "com-killer-undercover-entity-Words")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "words")
public class Words extends BaseEntity implements Serializable {
    /**
     * 词语一号
     */
    @TableField(value = "word_one")
    @ApiModelProperty(value = "词语一号")
    private String wordOne;

    /**
     * 词语二号
     */
    @TableField(value = "word_two")
    @ApiModelProperty(value = "词语二号")
    private String wordTwo;

    private static final long serialVersionUID = 1L;

}