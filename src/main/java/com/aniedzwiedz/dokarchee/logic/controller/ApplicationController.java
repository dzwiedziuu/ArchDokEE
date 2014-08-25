package com.aniedzwiedz.dokarchee.logic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.queue.AbstractApplicationQueue;

@Component
public class ApplicationController implements Runnable
{
	@Autowired
	private AbstractApplicationQueue<Action> queue;

	@Autowired
	private SessionController sessionController;

	// @PostConstruct
	public void postConstruct()
	{
		new Thread(this).start();
	}

	@Override
	public void run()
	{
		while (!Thread.interrupted())
		{
			Action action = queue.take();
			processAction(action);
		}
	}

	public void processAction(Action action)
	{
		if (action.getSender() == null)
			throw new RuntimeException("Action sender not set");
		sessionController.processAction(action);
	}
}
