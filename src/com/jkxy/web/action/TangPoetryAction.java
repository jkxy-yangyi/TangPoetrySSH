package com.jkxy.web.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jkxy.web.model.Poetries;
import com.jkxy.web.model.Poets;
import com.jkxy.web.service.IPoetriesService;
import com.jkxy.web.service.IPoetsService;
import com.jkxy.web.uitl.Pager;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TangPoetryAction extends ActionSupport{
	private IPoetriesService poetriesService;
	private IPoetsService poetsService;
	private Integer currentPage=1;
	
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public IPoetriesService getPoetriesService() {
		return poetriesService;
	}

	public void setPoetriesService(IPoetriesService poetriesService) {
		this.poetriesService = poetriesService;
	}

	public IPoetsService getPoetsService() {
		return poetsService;
	}

	public void setPoetsService(IPoetsService poetsService) {
		this.poetsService = poetsService;
	}

	public String show() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		//HttpServletRequest req = (HttpServletRequest)request;
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String selects = request.getParameter("selects");
		String keyword = request.getParameter("keyword");
		String id = request.getParameter("id");  //作者id,用于标题相同时查询
		/**
		 * lbl_Title题目查询诗歌
		 */
		if(selects.equals("lbl_Title")){  
			Poetries poetries=null;
			Poets poets;
			if(!"".equals(id) && !"null".equals(id) && id!=null){
				poetries= poetriesService.titleSearch(keyword,Integer.parseInt(id));
				if(poetries != null){
					try {
						poets = poetsService.poet(poetries.getPoet_id());
						//HttpSession session = request.getSession();
						poetries.setName(poets.getName());
						session.setAttribute("poetries", poetries);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return "title";
				}else {
					request.setAttribute("message", "查询失败,请核对查询内容是否匹配!");
					return "success";
				}
			}else {
				int totalSize=poetriesService.getTotalByTitle(keyword);
				Pager page=new Pager(currentPage, totalSize);
				List<Poetries> poetriesList = poetriesService.titleSearchListpage(keyword,currentPage, page.getPageSize());
				if (poetriesList != null && !poetriesList.isEmpty()) {
					List<Poetries> poes = new ArrayList<Poetries>();
					for(int i=0;i<poetriesList.size();i++){
						Poetries p = poetriesList.get(i);
						poets= poetsService.poet(p.getPoet_id());
						p.setName(poets.getName());
						poes.add(p);
					}
					//HttpSession session = request.getSession();
					request.setAttribute("page", page);
					session.setAttribute("poetriesList", poes);
					return "title";
				}else {
					request.setAttribute("message", "查询失败");
					return "success";
				}
			}
		}/**
		 * lbl_Author作者查询诗歌
		 */
		else if(selects.equals("lbl_Author")){	
			//HttpSession session = request.getSession();
			try {
				if(keyword == null){
					keyword = (String) session.getAttribute("name");
				}
				List<Poets> poetLost = poetsService.poetAuthor(keyword);
				Poets p = poetLost.get(0);
				int totalSize=poetriesService.getTotalByPoetries(p.getId());
				Pager page=new Pager(currentPage, totalSize);
				List<Poetries> poets = poetriesService.authorListpage(p.getId(),currentPage, page.getPageSize());
				List<Poetries> poes = new ArrayList<Poetries>();
				for(int i=0;i<poets.size();i++){
					Poetries poetries = poets.get(i);
					poetries.setName(p.getName());
					poes.add(poetries);
				}
				
				if(poes != null && poes.size()>0){
					//Map requestMap = (Map) ActionContext.getContext().get("request");
					request.setAttribute("page", page);
					session.setAttribute("poets", poets);
					session.setAttribute("name", keyword);
					return "author";
				}else {
					request.setAttribute("message", "查询失败,请核对查询内容是否匹配!");
					return "success";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}/**
		 * lbl_Body诗词内容查询诗歌
		 */
		else if(selects.equals("lbl_Body")){	
			try {
				int totalSize=poetriesService.getTotalBykeyword(keyword);
				Pager page=new Pager(currentPage, totalSize);
				List<Poetries> poetrieList = poetriesService.bodySearchpage(keyword,currentPage, page.getPageSize());
				if(poetrieList.size() > 0){
					List<Poetries> poes = new ArrayList<Poetries>();
					for(int i=0;i<poetrieList.size();i++){
						Poetries p = (Poetries) poetrieList.get(i);
						Poets poets= poetsService.poet(p.getPoet_id());
						p.setName(poets.getName());
						poes.add(p);
					}
					request.setAttribute("page", page);
					//HttpSession session = request.getSession();
					session.setAttribute("poetrieList", poes);
					return "body";
				}else {
					request.setAttribute("message", "查询失败,请核对查询内容是否匹配!");
					return "success";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			request.setAttribute("message", "查询失败,请核对查询内容是否匹配!");
			return "success";
		}
		return "";
	}

}


