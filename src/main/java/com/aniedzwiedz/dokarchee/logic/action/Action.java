package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.queue.AbstractApplicationQueue;

public abstract class Action
{
	private AbstractView currentView;
	private String senderViewName;
	private AbstractPresenter currentPresenter;

	private AbstractApplicationQueue<Action> abstractApplicationQueue;

	public Action(String senderViewName)
	{
		this.senderViewName = senderViewName;
	}

	public Action(AbstractView abstractView)
	{
		this(abstractView.getViewName());
		this.currentView = abstractView;
	}

	protected AbstractView getSenderView()
	{
		return currentView;
	}

	public String getSenderViewName()
	{
		return senderViewName;
	}

	protected AbstractPresenter getCurrentPresenter()
	{
		return currentPresenter;
	}

	public void setCurrentPresenter(AbstractPresenter currentPresenter)
	{
		this.currentPresenter = currentPresenter;
	}

	public abstract void performAction();

	protected AbstractApplicationQueue<Action> getAbstractApplicationQueue()
	{
		return abstractApplicationQueue;
	}

	public void setAbstractApplicationQueue(AbstractApplicationQueue<Action> abstractApplicationQueue)
	{
		this.abstractApplicationQueue = abstractApplicationQueue;
	}

	public void doPerform()
	{
		currentPresenter.setParams(this);
		performAction();
	}
}
