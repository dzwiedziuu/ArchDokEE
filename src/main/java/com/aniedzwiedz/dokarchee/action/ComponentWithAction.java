package com.aniedzwiedz.dokarchee.action;

public class ComponentWithAction<T>
{
	private T component;
	private Action action;

	public ComponentWithAction(T component, Action action)
	{
		this.component = component;
		this.action = action;
	}

	public T getComponent()
	{
		return component;
	}

	public Action getAction()
	{
		return action;
	}
}
