package com.brucejanet.springaop.service;

import com.brucejanet.springaop.aop.LogActionPointCut;
import org.springframework.stereotype.Service;

@Service
public class MakeDataService {
    @LogActionPointCut(name="this is inc")
    public int inc(int num){
        return num++;
    }
    @LogActionPointCut(name="this is dec")
    public int dec(int num){
        return num--;
    }
}
