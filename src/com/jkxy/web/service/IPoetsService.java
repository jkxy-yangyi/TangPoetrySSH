package com.jkxy.web.service;

import java.sql.SQLException;
import java.util.List;

import com.jkxy.web.model.Poets;

public interface IPoetsService {
	/**
     * 根据作者ID查询作者
     * @param id
     * @return Poets实体
     */
	public Poets poet(Integer id) throws SQLException;
	/**
     * 根据作者查询
     * @param author 作者名字
     * @return List集合
     */
	public List<Poets> poetAuthor(String author) throws SQLException;
		
}
