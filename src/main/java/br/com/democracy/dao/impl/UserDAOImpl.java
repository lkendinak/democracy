package br.com.democracy.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.democracy.dao.UserDAO;
import br.com.democracy.dto.UserSearchDTO;
import br.com.democracy.persistence.User;
import br.com.democracy.persistence.enums.UserStatusEnum;
import br.com.democracy.persistence.enums.UserTypeEnum;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

	@Override
	protected Class<User> getClazz() {
		return User.class;
	}

	@Override
	protected void addPropertiedToCriteria(Criteria c, User example) {
	}
	
	@Override
	public User getUserByEmail(String email) {
		
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));

		return (User) criteria.uniqueResult();
	}
	
	@Override
	public User getUserByToken(String token) {
		
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("token", token));

		return (User) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAwaitingNormalUsers() {
		
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("status", UserStatusEnum.AWAITING_APPROVAL.id()));
		criteria.add(Restrictions.eq("type", UserTypeEnum.NORMAL.id()));
		criteria.addOrder(Order.desc("regDate"));
		
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> searchUser(UserSearchDTO user) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(User.class);

		if (user.getStatus() != null && !user.getStatus().equals("0")) {
			criteria.add(Restrictions.eq("status", Integer.parseInt(user.getStatus())));
		}

		if (user.getEmail() != null) {
			criteria.add(Restrictions.eq("email", user.getEmail()));
		}
		
		if (user.getName() != null) {
			criteria.add(Restrictions.eq("name", user.getName()));
		}
		
		return criteria.list();
	}
}
