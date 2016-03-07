package org.pacs.pe.batch.dao;

import org.pacs.pe.batch.model.Pacs;

public abstract interface PacsDao
{
  public abstract void saveWorkStudyPacs(Pacs paramPacs);
  
  public abstract void saveWorkStudyInfast(Pacs paramPacs);
}
