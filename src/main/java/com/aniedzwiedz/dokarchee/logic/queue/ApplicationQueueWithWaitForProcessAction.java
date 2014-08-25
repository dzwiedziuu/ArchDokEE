package com.aniedzwiedz.dokarchee.logic.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.controller.ApplicationController;

@Component
@Profile("queueSynchronous")
public class ApplicationQueueWithWaitForProcessAction implements AbstractApplicationQueue<Action>
{
	@Autowired
	private ApplicationController applicationController;

	@Override
	public Action take()
	{
		throw new RuntimeException("operation not supported");
	}

	@Override
	public void put(Action action)
	{
		applicationController.processAction(action);
	}
}
