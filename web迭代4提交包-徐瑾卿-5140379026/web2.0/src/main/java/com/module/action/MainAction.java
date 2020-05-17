package com.module.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

/**
 * 
 * @author danielme.com
 * 
 */
public class MainAction extends ActionSupport
{
	private static final long serialVersionUID = 33466065079709970L;
	
	private static final Logger LOG = Logger.getLogger(MainAction.class);
	
	public String execute() throws Exception
	{			
		LOG.info("language: " + getLocale().getLanguage());
		return SUCCESS;
	}		

}
