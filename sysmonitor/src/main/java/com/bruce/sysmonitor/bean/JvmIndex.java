package com.bruce.sysmonitor.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("jvm_index")
public class JvmIndex {
    @TableId
    private Long id;
    private Data reportTime;
    private String hostId;
    private String value;
}
