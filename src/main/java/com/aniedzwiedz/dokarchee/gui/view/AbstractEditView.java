package com.aniedzwiedz.dokarchee.gui.view;

import com.aniedzwiedz.dokarchee.gui.form.DefaultForm.FormFieldListener;
import com.aniedzwiedz.dokarchee.gui.form.DefaultForm.FormListener;

public interface AbstractEditView<T> extends AbstractView
{
	public interface EditViewListener<T> extends FormFieldListener, FormListener<T>, ViewListener
	{
	}

	void addEditViewListener(EditViewListener<T> listViewListener);
}
