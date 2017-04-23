package com.jkxy.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import com.jkxy.web.dao.IPoetsDao;
import com.jkxy.web.model.Poetries;
import com.jkxy.web.model.Poets;
import com.jkxy.web.service.IPoetriesService;
import com.jkxy.web.service.IPoetsService;

/**
 * @author 
 *
 */
public class PoetsService implements IPoetsService {
	
	private IPoetsDao poetsDao;

	public IPoetsDao getPoetsDao() {
		return poetsDao;
	}

	public void setPoetsDao(IPoetsDao poetsDao) {
		this.poetsDao = poetsDao;
	}

	@Override
	public Poets poet(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return poetsDao.poet(id);
	}

	@Override
	public List<Poets> poetAuthor(String author) throws SQLException {
		// TODO Auto-generated method stub
		return poetsDao.poetAuthor(author);
	}
}
