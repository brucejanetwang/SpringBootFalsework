package com.bruce.sysmonitor;

import com.bruce.sysmonitor.bean.JvmIndex;
import com.bruce.sysmonitor.mapper.JvmMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JvmServiceTest {

     @Autowired
     private JvmMapper jvmMapper;

    @Test
    public void testSelect() {
        //mybatis plus版本和springboot版本兼容有问题，否则出现'sqlSessionFactory' or 'sqlSessionTemplate' are required 问题
        System.out.println(("----- selectAll method test ------"));
        List<JvmIndex> userList =  jvmMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}