package com.killer.undercover.dto.mini.res;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: huanghuiqiang
 * @create: 21.1.9 10:57
 */
@Data
@Accessors(chain = true)
public class RoomRes {

    /**
     * 房间号
     */
    @ApiModelProperty(value = "房间号")
    private String roomKey;

}