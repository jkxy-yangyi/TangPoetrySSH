package com.jkxy.web.service.impl;

import java.sql.SQLException;
import java.util.List;


import com.jkxy.web.dao.IPoetriesDao;
import com.jkxy.web.model.Poetries;
import com.jkxy.web.service.IPoetriesService;

public class PoetriesService implements IPoetriesService {

	private IPoetriesDao poetriesDao;
	public IPoetriesDao getPoetriesDao() {
		return poetriesDao;
	}

	public void setPoetriesDao(IPoetriesDao poetriesDao) {
		this.poetriesDao = poetriesDao;
	}


	@Override
	public int getTotalByPoetries(Integer id) {
		// TODO Auto-generated method stub
		return poetriesDao.getTotalByPoetries(id);
	}

	@Override
	public List<Poetries> titleSearchListpage(String title,Integer currentPage,Integer pageSize) throws SQLException {
		// TODO Auto-generated method stub
		return poetriesDao.titleSearchListpage(title, currentPage, pageSize);
	}

	@Override
	public Poetries titleSearch(String title, Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return poetriesDao.titleSearch(title, id);
	}

	@Override
	public List<Poetries> authorListpage(Integer id,Integer currentPage,Integer pageSize) throws SQLException {
		// TODO Auto-generated method stub
		return poetriesDao.authorListpage(id, currentPage,pageSize);
	}

	@Override
	public List<Poetries> bodySearchpage(String body,Integer currentPage,Integer pageSize) throws SQLException {
		// TODO Auto-generated method stub
		return poetriesDao.bodySearchpage(body,currentPage,pageSize);
	}

	@Override
	public int getTotalBykeyword(String keyword) {
		// TODO Auto-generated method stub
		return poetriesDao.getTotalBykeyword(keyword);
	}

	@Override
	public int getTotalByTitle(String title) {
		// TODO Auto-generated method stub
		return poetriesDao.getTotalByTitle(title);
	}
	

}
