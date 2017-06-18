package com.edu.dao;
import java.util.List;

public interface IBaseDao<T> {
	//将对象o添加到数据库
	public int insert(T o);	
	//将对象集合添加到数据库内
	public int insertList(List<T> list);
	//利用对象o修改当前记录
	public int update(T o);
	//利用id集合删除集合中对应id的记录
	public int deleteList(int...ids);
	//从数据库中删除一个记录o
	public int delete(T o);
	//利用关键字id从数据库中删除一个记录
	public int delete(int id);
	//利用id查找一条记录
	public T findById(int id);
	//查找对象o
	public T find(T o);
	//查找所有对象
	public List<T> findAll();
	//查找满足条件的所有记录
	public List<T> findByCondition(String condition);
}
