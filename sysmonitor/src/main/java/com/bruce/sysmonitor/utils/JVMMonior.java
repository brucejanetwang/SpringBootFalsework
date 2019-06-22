package com.bruce.sysmonitor.utils;


import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class JVMMonior {


    static public  void GetGCInfo( ) throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        RuntimeMXBean jvmruntime = ManagementFactory.getRuntimeMXBean();
        ObjectName youngMBean = new ObjectName("java.lang:type=GarbageCollector,name=PS MarkSweep");
        ObjectName tenuredMBean = new ObjectName("java.lang:type=GarbageCollector,name=PS Scavenge");
        System.out.println("YGC:" + mbs.getAttribute(youngMBean, "CollectionCount"));
        System.out.println("FGC:" + mbs.getAttribute(tenuredMBean, "CollectionCount"));
        System.gc();
        Thread.sleep(5000);
        System.out.println("YGC:" + mbs.getAttribute(youngMBean, "CollectionCount"));
        System.out.println("FGC:" + mbs.getAttribute(tenuredMBean, "CollectionCount"));

        long runtimesec =  jvmruntime.getUptime()/1000;
        System.out.println("runtimesec :" +runtimesec);
    }
    static  public void GetMemInfo(){
        //maxMemory()这个方法返回的是java虚拟机（这个进程）能构从操纵系统那里挖到的最大的内存，以字节为单位，也就是-Xmx参数
        System.out.println("Max:"+Runtime.getRuntime().maxMemory());

        //freeMemory()是挖过来而又没有用上的内存，比如申请比较大的Xms参数
        System.out.println("Free:"+Runtime.getRuntime().freeMemory());

        //totalMemory()这个方法返回的是java虚拟机现在已经从操纵系统那里挖过来的内存大小，也就是java虚拟机这个进程当时所占用的 所有 内存。也就是-Xms参数加上扩展的，
        System.out.println("Total:"+Runtime.getRuntime().totalMemory());

    }

    public static void main(String[] args) {
// TODO Auto-generated method stub
        try {
            JVMMonior.GetGCInfo();
            JVMMonior.GetMemInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
