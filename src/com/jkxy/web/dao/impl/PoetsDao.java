package com.jkxy.web.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import com.jkxy.web.dao.IPoetsDao;
import com.jkxy.web.model.Poetries;
import com.jkxy.web.model.Poets;
import com.jkxy.web.service.IPoetsService;
import com.jkxy.web.service.impl.PoetriesService;

public class PoetsDao implements IPoetsDao{
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	PoetriesDao poetriesDao = new PoetriesDao();
	/**
     * 根据作者ID查询作者
     * @param id
     * @return Poets实体
     */
	@Override
	public Poets poet(Integer id) throws SQLException {
		Session session=sessionFactory.openSession();
		//Transaction ts=session.beginTransaction();
		Query query=session.createQuery("from Poets where id='"+ id+"'");
		List<Poets> poe=query.list();
		//ts.commit();
		session.close();
		if(poe.size()>0) {
			return (Poets) poe.get(0);
		}else {
			return null;
		}
	}

	/**
	 * 根据作者姓名查询作者所有诗歌名称
	 * @param author
	 * @return List集合
	 */
	@Override
	public List<Poets> poetAuthor(String author) throws SQLException {
		Session session=sessionFactory.openSession();
		//Transaction ts=session.beginTransaction();
		Query query=session.createQuery(" from Poets where name='"+ author+"'");
		List<Poets> plist=(List<Poets>)query.list();
		if(plist.size()>0) {
			//Poets poets = plist.get(0);
			//ts.commit();
			session.close();
			return plist;
		}else {
			return null;
		}
		
	}

	

}
