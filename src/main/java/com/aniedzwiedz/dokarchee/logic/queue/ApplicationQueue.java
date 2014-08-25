package com.aniedzwiedz.dokarchee.logic.queue;

import java.util.concurrent.ArrayBlockingQueue;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.logic.action.Action;

@Component
@Profile("queueAsynchronous")
public class ApplicationQueue extends ArrayBlockingQueue<Action> implements AbstractApplicationQueue<Action>
{
	private static final long serialVersionUID = -4606567960155316517L;

	private static final int CAPACITY = 20;

	public ApplicationQueue()
	{
		super(CAPACITY);
	}

	public Action take()
	{
		try
		{
			return super.take();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void put(Action action)
	{
		try
		{
			super.put(action);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
