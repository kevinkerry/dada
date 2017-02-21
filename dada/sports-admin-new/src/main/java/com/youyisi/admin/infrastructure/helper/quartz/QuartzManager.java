package com.youyisi.admin.infrastructure.helper.quartz;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
  
/** 
 * @Description: 定时任务管理类 
 *  
 * @ClassName: QuartzManager 
 * @Copyright: Copyright (c) 2014 
 *  
 * @author Comsys-LZP 
 * @date 2014-6-26 下午03:15:52 
 * @version V2.0 
 */  
public class QuartzManager {  
    private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
   /**
    * 
    * @param job
    * @param classz
    */
    public static void addJob(MyJob job,Class classz) {  
        try {  
            Scheduler scheduler = gSchedulerFactory.getScheduler();
            
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        	//获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
        	CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        	//不存在，创建一个
        	if (null == trigger) {
        		JobDetail jobDetail = JobBuilder.newJob(classz)
        			.withIdentity(job.getJobName(), job.getJobGroup()).build();
        		jobDetail.getJobDataMap().put("scheduleJob", job);
        		//表达式调度构建器
        		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
        			.getCronExpression());
        		//按新的cronExpression表达式构建一个新的trigger
        		trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
        		scheduler.scheduleJob(jobDetail, trigger);
        	} else {
        		// Trigger已存在，那么更新相应的定时设置
        		//表达式调度构建器
        		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
        			.getCronExpression());
        		//按新的cronExpression表达式重新构建trigger
        		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
        			.withSchedule(scheduleBuilder).build();
        		//按新的trigger重新设置job执行
        		scheduler.rescheduleJob(triggerKey, trigger);
        	}
        	scheduler.start();
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
}  