package com.aniedzwiedz.dokarchee.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniedzwiedz.dokarchee.data.dao.AbstractDao;
import com.aniedzwiedz.dokarchee.data.dao.custom.FigureDao;
import com.aniedzwiedz.dokarchee.data.model.Figure;
import com.aniedzwiedz.dokarchee.data.service.AbstractService;
import com.aniedzwiedz.dokarchee.data.service.FigureService;

@Service
public class FigureServiceImpl extends AbstractService<Figure> implements FigureService
{
	@Autowired
	private FigureDao figureDao;

	@Override
	protected AbstractDao<Figure> getDaoObj()
	{
		return figureDao;
	}
}
