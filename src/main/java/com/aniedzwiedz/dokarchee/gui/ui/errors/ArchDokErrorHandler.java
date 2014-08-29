package com.aniedzwiedz.dokarchee.gui.ui.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.ErrorEvent;
import com.vaadin.server.ErrorHandler;

public class ArchDokErrorHandler implements ErrorHandler
{
	private static final Logger logger = LoggerFactory.getLogger(ArchDokErrorHandler.class);

	@Override
	public void error(ErrorEvent event)
	{
		logger.error("Uncaughed exception", event.getThrowable());
	}
}
