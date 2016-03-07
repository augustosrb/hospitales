package org.pacs.pe.batch.writers;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.pacs.pe.batch.dao.PacsDao;
import org.pacs.pe.batch.model.Pacs;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomWriter implements ItemWriter<Pacs> {
	static Logger logger = Logger.getLogger(CustomWriter.class);
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/batch/config/database.xml");
	PacsDao pacsDao = (PacsDao) this.ctx.getBean("pacsDAO", PacsDao.class);

	public void write(List<? extends Pacs> lstPacs) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("start method write(List<? extends Pacs> lstPacs)");
		}
		Integer sizeList = Integer.valueOf(lstPacs.size());
		if (logger.isInfoEnabled()) {
			logger.info("sizeList: " + sizeList.intValue());
		}
		if (lstPacs.size() > 0) {
			for (Pacs p : lstPacs) {
				p.setFecha_registro(new Date());
				this.pacsDao.saveWorkStudyInfast(p);
				this.pacsDao.saveWorkStudyPacs(p);
			}
		}
	}
}
