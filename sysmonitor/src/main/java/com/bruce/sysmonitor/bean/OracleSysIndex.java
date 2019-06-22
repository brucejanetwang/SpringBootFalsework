package com.bruce.sysmonitor.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("oracle_sysindex")
public class OracleSysIndex implements Serializable{
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
   private Date reportTime; //第一单词小些，后面的单词首字母大写
  private String hostId;
  private Integer procRun;
  private Integer procBusy;

  private Integer memSwapd;
  private Integer memFree;
  private Integer memBuff;
  private Integer memCache;
  private Integer memIncat;
  private Integer memActive;

  private Integer cpuSys;
  private Integer cpuIdle;
  private Integer cpuWait;
}


