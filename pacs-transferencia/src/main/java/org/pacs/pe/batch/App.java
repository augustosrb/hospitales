package org.pacs.pe.batch;

import java.util.UUID;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
  static Logger logger = Logger.getLogger(App.class);
  
  public static void main(String[] args)
  {
    DOMConfigurator.configure("/batch-log-config/pacs-transferencia-log4j.xml");
    
    MDC.put("TRANSFERENCIA_NDC", UUID.randomUUID().toString());
    if (logger.isInfoEnabled()) {
      logger.info("start method main(String[] args)");
    }
    System.setProperty("org.terracotta.quartz.skipUpdateCheck", "true");
    String springConfig = "spring/batch/jobs/job-quartz.xml";
    
    ApplicationContext context = new ClassPathXmlApplicationContext(
      springConfig);
    
    MDC.remove("TRANSFERENCIA_NDC");
  }
}