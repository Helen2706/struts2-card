package com.edu.dao.card;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.INPUT_STREAM;

import com.edu.dao.IBaseDao;
import com.edu.db_util.JdbcPoolUtils;
import com.edu.model.card.Card;

public class CardDao implements IBaseDao<Card> {

	@Override
	public int insert(Card card) {
		String sql = "insert into card(name,sex,department,mobile,phone,email,address) "
				+ "values(?,?,?,?,?,?,?)"; 
		Object params[] = {card.getName(),card.getSex(),card.getDepartment(),card.getMobile(),
				card.getPhone(),card.getEmail(),card.getAddress()
		};
		return JdbcPoolUtils.dbCUD(sql, params);
	}

	@Override
	public int insertList(List<Card> list) {
		for(int i=0;i<list.size();i++)
			insert(list.get(i));
		return list.size();
	}

	@Override
	public int update(Card card) {
		String sql = "update card set name=?,sex=?,department=?,mobile=?,phone=?,email=?,address=?"
				+ " where id=?";
		Object params[] = {card.getName(),card.getSex(),card.getDepartment(),card.getMobile(),
				card.getPhone(),card.getEmail(),card.getAddress(),card.getId()
		};
		return JdbcPoolUtils.dbCUD(sql, params);
	}

	@Override
	public int deleteList(int... ids) {
		for(int i=0;i<ids.length;i++)
			delete(ids[i]);
		return ids.length;
	}

	@Override
	public int delete(Card card) {
		String sql = "delete from card where id=?";
		Object params[] = {card.getId()};
		return JdbcPoolUtils.dbCUD(sql, params);
	}

	@Override
	public int delete(int id) {
		String sql = "delete from card where id=?";
		Object params[] = {id};
		return JdbcPoolUtils.dbCUD(sql, params);
	}

	@Override
	public Card findById(int id){
		String sql = "select * from card where id=?";
		Object params[] = {id};
		ResultSet resultSet = null;
		Card card2 = null;
		resultSet = JdbcPoolUtils.query(sql, params);
		try {
			if(resultSet.next()){
				card2 = new Card();
				card2.setId(resultSet.getInt("id"));
				card2.setName(resultSet.getString("name"));
				card2.setSex(resultSet.getString("sex"));
				card2.setDepartment(resultSet.getString("department"));
				card2.setMobile(resultSet.getString("mobile"));
				card2.setPhone(resultSet.getString("phone"));
				card2.setEmail(resultSet.getString("email"));
				card2.setAddress(resultSet.getString("address"));
				card2.setFlag(resultSet.getString("flag"));
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return card2;
	}

	@Override
	public Card find(Card card) {
		Card card2 = findById(card.getId());
		return card2;
	}

	@Override
	public List<Card> findAll() {
		String sql = "select * from card";
		Object params[] = null;
		ResultSet resultSet = null;
		List<Card> cards = new ArrayList<Card>();
		try {
			resultSet = JdbcPoolUtils.query(sql, params);
			while(resultSet.next()){
				Card card2 = new Card();
				card2.setId(resultSet.getInt("id"));
				card2.setName(resultSet.getString("name"));
				card2.setSex(resultSet.getString("sex"));
				card2.setDepartment(resultSet.getString("department"));
				card2.setMobile(resultSet.getString("mobile"));
				card2.setPhone(resultSet.getString("phone"));
				card2.setEmail(resultSet.getString("email"));
				card2.setAddress(resultSet.getString("address"));
				card2.setFlag(resultSet.getString("flag"));
				cards.add(card2);
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cards;
	}

	@Override
	public List<Card> findByCondition(String condition) {
		String sql = "select * from card";
		Object params[] = null;
		ResultSet resultSet = null;
		List<Card> cards = new ArrayList<Card>();
		String fields[] = {"name","sex","department","mobile","address","phone","email"};
		if(condition!=null&&!condition.equals("")){
			sql = sql + " where ";
			for(int i=0;i<fields.length-1;i++)
				sql = sql + fields[i] + " like '%"+condition+"%' or ";
			sql = sql + fields[fields.length-1] + " like '%"+condition+"%'";
		}
		try {
			resultSet = JdbcPoolUtils.query(sql, params);
			while(resultSet.next()){
				Card card2 = new Card();
				card2.setId(resultSet.getInt("id"));
				card2.setName(resultSet.getString("name"));
				card2.setSex(resultSet.getString("sex"));
				card2.setDepartment(resultSet.getString("department"));
				card2.setMobile(resultSet.getString("mobile"));
				card2.setPhone(resultSet.getString("phone"));
				card2.setEmail(resultSet.getString("email"));
				card2.setAddress(resultSet.getString("address"));
				card2.setFlag(resultSet.getString("flag"));
				cards.add(card2);
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cards;
	}
	
/**
 * 以下是针对名片管理中的回收站而添加的特殊属性
 */
	public Card findById(int id,String flag){
		Card card2 = findById(id);
		if(card2.getFlag().equals(flag))
			return card2;
		return null;
	}
	
	public Card find(Card card,String flag){
		Card card2 = null;
		card2 = findById(card.getId(), flag);
		return card2;
	}
	
	public List<Card> findAll(String flag){
		List<Card> cards = findAll();
		List<Card> cards2 = new ArrayList<Card>();
		for(Card card:cards){
			if(card.getFlag().equals(flag))
				cards2.add(card);
		}
		return cards2;
	}
	
	public List<Card> findByCondition(String condition,String flag){
		List<Card> cards = findByCondition(condition);
		List<Card> cards2 = new ArrayList<Card>();
		for(Card card:cards){
			if(card.getFlag().equals(flag))
				cards2.add(card);
		}
		return cards2;
	}
	
	//修改flag值
	public int updateFlag(int id,String flag) throws Exception{
		String sql = "update card set flag=? where id=?";
		Object params[] = {flag,id};
		return JdbcPoolUtils.dbCUD(sql, params);
	}
	
	//将名片移到回收站
	public int retrieve(int...ids) throws Exception{
		for(int id:ids){
			updateFlag(id, "1");
		}
		return ids.length;
	}
	
	//从回收站还原名片
		public int revert(int...ids) throws Exception{
			for(int id:ids){
				updateFlag(id, "0");
			}
			return ids.length;
		}
}
