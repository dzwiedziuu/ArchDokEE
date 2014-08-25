package com.aniedzwiedz.dokarchee.gui.view;

import org.vaadin.dialogs.ConfirmDialog;

import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.action.preAction.BlankPreAction;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.UI;

public class AbstractListView
{
	public static class Confirm extends BlankPreAction implements org.vaadin.dialogs.ConfirmDialog.Listener
	{
		private Action action;

		public Confirm(AbstractView sender)
		{
			super(sender);
		}

		@Override
		public void doPreAction(Action action)
		{
			this.action = action;
			ConfirmDialog confirmDialog = ConfirmDialog.getFactory()
					.create("Uwaga", "Na pewno chcesz skasowac ten rekord?", "TAK", "NIE", null);
			confirmDialog.setWidth(400.0f, Unit.PIXELS);
			confirmDialog.show(UI.getCurrent(), Confirm.this, true);
		}

		@Override
		public void onClose(ConfirmDialog dialog)
		{
			if (dialog.isConfirmed() == true)
				super.doPreAction(action);
		}
	}
}
