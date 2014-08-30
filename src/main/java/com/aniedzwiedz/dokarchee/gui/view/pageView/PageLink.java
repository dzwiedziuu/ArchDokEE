package com.aniedzwiedz.dokarchee.gui.view.pageView;

public class PageLink
{
	private String label;
	private String linkContent;

	public PageLink(String label, String linkContent)
	{
		this.label = label;
		this.linkContent = linkContent;
	}

	public String getLabel()
	{
		return label;
	}

	public String getLinkContent()
	{
		return linkContent;
	}
}
