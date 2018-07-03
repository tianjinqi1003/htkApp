package com.htkapp.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2017/4/6.
 *
 */
public class OrderNumGen {

    //循环自增上限,超出则从初始值开始
    private static long maxLoop = 1000000000;
    private static int initNum  = 100;
    //订单
    private volatile static AtomicInteger atomicInteger;
    //团购消费券
    private volatile static AtomicInteger atomicIntegerNum;
    private static int initNumVal = 10000;
    private static long maxLoopVal = 1000000000;


    /**
     * author: Vino
     * date: 2016/5/8
     * function: DCL获取单例atomicInteger
     * @return
     */
    private static AtomicInteger getAtomicInteger() {
        if (atomicInteger == null) {
            synchronized (OrderNumGen.class) {
                if (atomicInteger == null)
                    atomicInteger = new AtomicInteger(initNum);//初始值为100
            }
        }
        return atomicInteger;
    }

    //获取单例
    private static AtomicInteger getAtomicLong(){
        if(atomicIntegerNum == null){
            synchronized (OrderNumGen.class){
                if(atomicIntegerNum == null){
                    atomicIntegerNum = new AtomicInteger(initNumVal);
                }
            }
        }
        return atomicIntegerNum;
    }

    //生成订单号
    public static Long next() {
        //获取单例
        AtomicInteger atomicInteger = getAtomicInteger();
        //原子性自增并返回自增前的值
        int nextValue = atomicInteger.getAndIncrement();
        //若循环次数已超过上限
        if (nextValue > maxLoop) {
            //CAS更新值成功
            if (atomicInteger.compareAndSet(nextValue + 1, initNum)) {
                atomicInteger.getAndIncrement();
                return getNextNormally(initNum);
            }
            else {//CAS更新值失败
                //重新获取
                nextValue = atomicInteger.getAndIncrement();
                if (nextValue <= maxLoop)
                    return getNextNormally(nextValue);
                else{//非正常情况下
                    return getNextUnNormally();
                }
            }
        }
        return getNextNormally(nextValue);
    }

    //生成团购消费券号
    public static Long getVoucherNumber(){
        //获取单例
        AtomicInteger atomicInteger = getAtomicLong();
        //原子性自增并返回自增前的值
        int nextValue = atomicInteger.getAndIncrement();
        //若循环次数已超过上限
        if (nextValue > maxLoop) {
            //CAS更新值成功
            if (atomicIntegerNum.compareAndSet(nextValue + 1, initNum)) {
                atomicIntegerNum.getAndIncrement();
                return getNextNormally(initNum);
            }
            else {//CAS更新值失败
                //重新获取
                nextValue = atomicInteger.getAndIncrement();
                if (nextValue <= maxLoop)
                    return getNextNormally(nextValue);
                else{//非正常情况下
                    return getNextUnNormally();
                }
            }
        }
        String result = getNextUnNormallyByNum(nextValue).toString();
        if(result.length() < 8){
            int length = result.length();
            for (int j=0; j< 8 - length; j++){
                result += "0";
            }
        }
        return Long.parseLong(result);
    }


    //生成单号加前缀
    public static String getOutRefundNo(String key){
        //获取单例
        AtomicInteger atomicInteger = getAtomicInteger();
        //原子性自增并返回自增前的值
        int nextValue = atomicInteger.getAndIncrement();
        //若循环次数已超过上限
        if (nextValue > maxLoop) {
            //CAS更新值成功
            if (atomicInteger.compareAndSet(nextValue + 1, initNum)) {
                atomicInteger.getAndIncrement();
                return key + getNextNormally(initNum);
            }
            else {//CAS更新值失败
                //重新获取
                nextValue = atomicInteger.getAndIncrement();
                if (nextValue <= maxLoop)
                    return key + getNextNormally(nextValue);
                else{//非正常情况下
                    return key + getNextUnNormally();
                }
            }
        }
        return key + getNextNormally(nextValue);
    }

    public static Long getNextNormally(int nextValue) {
        int randomNum=(int)(Math.random()*90)+10;//2位随机码
        return Long.valueOf(getDateNum()+nextValue+randomNum);
    }

    public static Long getNextUnNormally() {
        int randomNum = (int)(Math.random()*90000)+10000;//5位随机码
        return Long.valueOf(getDateNum()+randomNum);
    }

    public static Long getNextUnNormallyByNum(int nextVal) {
        int randomNum = (int)(Math.random()*90000000)+10000;//8位随机码
        return (long) (nextVal + randomNum);
    }

    /**
     * author: Vino
     * date: 2016/5/8
     * function: 获取订单号中的时间部分
     * @return
     */
    public static String getDateNum(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String sTime = simpleDateFormat.format(date);
        Integer nSecond = Integer.valueOf(sTime.substring(6,8)) * 60 * 60 +Integer.valueOf(sTime.substring(8,10)) * 60
                + Integer.valueOf(sTime.substring(10,12));
        String sSecond = String.valueOf(nSecond);
        if(sSecond.length() < 5){
            for(int i = 5,j = sSecond.length(); i > j; i--){
                sSecond = "0" + sSecond;
            }
        }
        return sTime.substring(0,6)+sSecond;
    }

}
