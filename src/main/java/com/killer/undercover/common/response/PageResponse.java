package com.killer.undercover.common.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 分页响应
 *
 * @author xuhaoming
 * @since 2020/7/7
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "分页响应")
public class PageResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final ThreadLocal<DateFormat> sdf = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    @ApiModelProperty(value = "当前服务器时间")
    private String sysdate;

    @ApiModelProperty(value = "是否成功")
    private boolean success = true;

    @ApiModelProperty(value = "状态码")
    private int code = 200;

    @ApiModelProperty(value = "返回数据")
    private T results;

    @ApiModelProperty(value = "返回消息")
    private String message = "success";

    @ApiModelProperty(notes = "当前页")
    private int curPage = 0;

    @ApiModelProperty(notes = "页面总数")
    private int pageCount = 0;

    @ApiModelProperty(notes = "页面大小")
    private int pageSize = 0;

    @ApiModelProperty(notes = "总条目数")
    private long count = 0;


    public static <T> PageResponse<List<T>> format(IPage<T> page) {

        return new PageResponse<List<T>>()
                .setSysdate(sdf.get().format(new Date()))
                .setResults(page.getRecords())
                .setCurPage((int) page.getCurrent())
                .setPageCount((int) page.getPages())
                .setPageSize((int) page.getSize())
                .setCount((int) page.getTotal());
    }

}
