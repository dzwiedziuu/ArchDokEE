package com.aniedzwiedz.dokarchee.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.action.Action;
import com.aniedzwiedz.dokarchee.controller.ApplicationController;

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
