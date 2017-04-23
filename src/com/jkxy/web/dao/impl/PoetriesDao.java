package com.jkxy.web.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jkxy.web.dao.IPoetriesDao;
import com.jkxy.web.model.Poetries;

public class PoetriesDao implements IPoetriesDao{
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	/**
	 * 标题分页查询诗歌
	 * @param title
	 * @param currentPage
	 * @param pageSize
	 * @return List集合
	 */
	@Override
	public List<Poetries> titleSearchListpage(String title,Integer currentPage,Integer pageSize)
			throws SQLException {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Poetries where title like '%" + title + "%' ");
		int startRow=(currentPage-1)*pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		List<Poetries> poetriesList=query.list();
		session.close();
		return poetriesList;
		
	}


	/**
	 * 标题和ID查询诗歌
	 * @param title
	 * @param id
	 * @return Poetries实体
	 */
	@Override
	public Poetries titleSearch(String title, Integer id)
			throws SQLException {
		Session session=sessionFactory.openSession();
		Transaction ts=session.beginTransaction();
		Query query=session.createQuery("from Poetries where poet_id='"+ id+"' and title='"+ title+"'");
		List poe=query.list();
		ts.commit();
		session.close();
		if(poe.size()>0) {
			return (Poetries) poe.get(0);
		}else {
			return null;
		}
		
	}


	/**
	 * 根据诗歌内容查询
	 * @param body
	 * @return Poetries实体
	 */
	@Override
	public List<Poetries> bodySearchpage(String body,Integer currentPage,Integer pageSize)
			throws SQLException {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Poetries where content like '%" + body + "%' ");
		int startRow=(currentPage-1)*pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		List<Poetries> poetriesList=query.list();
		session.close();
		return poetriesList;
	}
	
	/**
	 * 根据作者ID号分页查询诗歌
	 * @param id
	 * @param name
	 * @return List集合
	 */
	public List<Poetries> authorListpage(Integer id,Integer currentPage,Integer pageSize) {
		// TODO Auto-generated method stub
		//打开会话连接
		Session session=sessionFactory.openSession();
		//获取事务管理
		//Transaction ts=session.beginTransaction();
		Query query=session.createQuery("from Poetries where poet_id="+id);
		//获取记录的第一条（当前页面-1*页面尺寸）
		int startRow=(currentPage-1)*pageSize;
		query.setFirstResult(startRow);
		//页面的最多条数（页面尺寸）
		query.setMaxResults(pageSize);
		List<Poetries> poetriesList=query.list();
		session.close();
		return poetriesList;
	}

	/**
	 * id查询所有的诗歌的总数
	 * @param id
	 * @return int
	 */
	@Override
	public int getTotalByPoetries(Integer id) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Poetries where poet_id="+id);
		List<Poetries> poetries=query.list();
		session.close();
		return poetries.size();
	}
	
	/**
	 * 内容查询所有的诗歌的总数
	 * @param keyword
	 * @return int
	 */
	@Override
	public int getTotalBykeyword(String keyword) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Poetries where content like '%" + keyword + "%' ");
		List<Poetries> poetries=query.list();
		session.close();
		return poetries.size();
	}

	/**
	 * 题目查询所有的诗歌的总数
	 * @param keyword
	 * @return int
	 */
	@Override
	public int getTotalByTitle(String title) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Poetries where title like '%" + title + "%' ");
		List<Poetries> poetries=query.list();
		session.close();
		return poetries.size();
	}


}
