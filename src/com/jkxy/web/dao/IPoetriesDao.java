package com.jkxy.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.jkxy.web.model.Poetries;


/**
 * @author 诗歌查询接口
 */
public interface IPoetriesDao {
	public List<Poetries> titleSearchListpage(String title,Integer currentPage,Integer pageSize) throws SQLException;
	public Poetries titleSearch(String title,Integer id) throws SQLException;
	public List<Poetries> authorListpage(Integer id,Integer currentPage,Integer pageSize) throws SQLException;
	public List<Poetries> bodySearchpage(String body,Integer currentPage,Integer pageSize) throws SQLException;
	public int getTotalByPoetries(Integer id);
	public int getTotalBykeyword(String keyword);
	public int getTotalByTitle(String title);
}
