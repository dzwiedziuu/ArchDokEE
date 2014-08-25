package com.aniedzwiedz.dokarchee.gui.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.queue.AbstractApplicationQueue;
import com.vaadin.navigator.View;
import com.vaadin.ui.Panel;

public abstract class AbstractView extends Panel implements ActionTaker, View, NamedView
{
	private static final long serialVersionUID = -8228882086603009109L;

	@Autowired
	private AbstractApplicationQueue<Action> queue;

	public void takeAction(Action action)
	{
		queue.put(action);
	}
}
