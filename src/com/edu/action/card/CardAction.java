package com.edu.action.card;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.edu.dao.card.CardDao;
import com.edu.model.card.Card;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Scope;
import com.opensymphony.xwork2.inject.Scoped;
@Namespace("/card")
@Scoped(Scope.REQUEST)
@ParentPackage("struts-default")		//表示继承的父包
public class CardAction extends ActionSupport {
	private CardDao cardDao = new CardDao();
	private Card card;
	//提交页面，查询信息提交
	private String condition;		//查询条件
	private String[] checkList;		//可以选择多条记录复选框所形成的记录序号的复选框
	private int id;					//单记录时的主键号
	private String order;			//设置查询结果的排序方式
	private long pageNo;			//查询结果的分页页码号
	private int pageSize;			//每页要显示的记录条数
	//返回执行结果的返回信息
	private long recordCount;		//满足条件的记录总条数
	private long pageCount;			//查询出记录的总页数
	private List<Card> listCard;	//查询结果集合
	private String message;			//返回运行后的信息
	private HttpSession session;
	
	//插入一条记录
	@Action(
			value="insert",
			results={@Result(name="success",location="/find",type="redirectAction")}
			)
	public String insert() throws Exception{
		cardDao.insert(card);
		message = "插入一条记录成功！";
		return "success";
	}
	
	//插入一组记录
	@Action(
			value="insertList",
			results={@Result(name="success",location="/find",type="redirectAction")}
			)
	public String insertList() throws Exception{
		int n = cardDao.insertList(listCard);
		message = "插入一组"+n+"条记录成功！";
		return "success";
	}
	
	//删除一条记录
	@Action(
			value="delete",
			results={@Result(name="success",location="/find",type="redirectAction")}
			)
	public String delete() throws Exception{
		cardDao.delete(id);
		message = "删除一条记录成功！";
		return "success";
	}
	
	//删除一组记录
	@Action(
			value="deleteList",
			results={@Result(name="success",location="/find",type="redirectAction")}
			)
	public String deleteList() throws Exception{
		int[] ids = new int[checkList.length];
		for(int i=0;i<checkList.length;i++)
			ids[i] = Integer.parseInt(checkList[i]);
		int n = cardDao.deleteList(ids);
		message = "删除"+n+"条记录成功！";
		return "success";
	}
	
	//查找记录
	@Action(
			value="find",
			results={@Result(name="success",location="/card/list.jsp",type="dispatcher")}
			)
	public String find() throws Exception{
		listCard = cardDao.findByCondition(condition, "0");
		session = ServletActionContext.getRequest().getSession();
		session.setAttribute("condition", condition);
		session.setAttribute("order", order);
		return "success";
	}
	
	//跳转到更新记录页面
	@Action(
			value="findupdate",
			results={@Result(name="success",location="/card/update.jsp",type="dispatcher")}
			)
	public String findUpdate() throws Exception{
		card = cardDao.findById(id,"0");
		return "success";
	}
	
	//更新一条记录
	@Action(
			value="update",
			results={@Result(name="success",location="/find",type="redirectAction")}
			)
	public String update() throws Exception{
		cardDao.update(card);
		return "success";
	}
	
	//将一组名片移到回收站
	@Action(
			value="retrieve",
			results={@Result(name="success",location="/find",type="redirectAction")}
			)
	public String retrieve() throws Exception{
		int[] ids = new int[checkList.length];
		for(int i=0;i<checkList.length;i++)
			ids[i] = Integer.parseInt(checkList[i]);
		cardDao.retrieve(ids);
		return "success";
	}

	public CardDao getCardDao() {
		return cardDao;
	}

	public void setCardDao(CardDao cardDao) {
		this.cardDao = cardDao;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String[] getCheckList() {
		return checkList;
	}

	public void setCheckList(String[] checkList) {
		this.checkList = checkList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public long getPageNo() {
		return pageNo;
	}

	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public List<Card> getListCard() {
		return listCard;
	}

	public void setListCard(List<Card> listCard) {
		this.listCard = listCard;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	
}
