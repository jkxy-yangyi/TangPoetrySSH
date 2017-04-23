package com.jkxy.web.service;

import java.sql.SQLException;
import java.util.List;

import com.jkxy.web.model.Poetries;

public interface IPoetriesService {
	/**
     * id查询诗歌总记录数
     * @param id
     * @return 总记录数
     */	
	public int getTotalByPoetries(Integer id);
	/**
     * 关键字查询诗歌总记录数
     * @param keyword
     * @return 总记录数
     */	
	public int getTotalBykeyword(String keyword);
	/**
     * 题目查询诗歌总记录数
     * @param title
     * @return 总记录数
     */	
	public int getTotalByTitle(String title);
	/**
     * 标题分页查询诗歌
     * @param title
     * @return List集合
     */	
	public List<Poetries> titleSearchListpage(String title,Integer currentPage,Integer pageSize) throws SQLException;
	/**
     * 标题和ID查询诗歌
     * @param title，id
     * @return Poetries实体
     */	
	public Poetries titleSearch(String title,Integer id) throws SQLException;
	/**
     * ID分页查询诗歌
     * @param id，currentPage，pageSize
     * @return List集合
     */	
	public List<Poetries> authorListpage(Integer id,Integer currentPage,Integer pageSize) throws SQLException;
	/**
     * 内容分页查询诗歌
     * @param body，currentPage，pageSize
     * @return List集合
     */	
	public List<Poetries> bodySearchpage(String body,Integer currentPage,Integer pageSize) throws SQLException;
}
