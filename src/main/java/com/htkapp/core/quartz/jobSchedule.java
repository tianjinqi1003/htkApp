package com.htkapp.core.quartz;

import com.htkapp.modules.merchant.pay.dao.OrderRecordMapper;
import com.xiaoleilu.hutool.date.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.net.Socket;
import java.util.Date;

import static com.xiaoleilu.hutool.date.DateUtil.NORM_DATETIME_PATTERN;
import static com.xiaoleilu.hutool.date.DateUtil.format;

public class jobSchedule extends QuartzJobBean {

    private static int num = 0;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        /**
         * 执行定时任务的类为传入的参数Class cls。
         * 这个类为反射出来的类，不归spring管理，所以在这个类里注入是不成功的，
         * 需要通过spring的上下文获取bean，并set到构造函数中去进行初始化。
         */
        ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");
        String dateParam = format(DateUtil.offsetMinute(new Date(), -5), NORM_DATETIME_PATTERN);
        int row = applicationContext.getBean(OrderRecordMapper.class).cancelUnpaidOrderByDate(dateParam);
        System.out.println("执行第"+(num += 1)+"次" +", 执行结果:" + row);
    }
}
