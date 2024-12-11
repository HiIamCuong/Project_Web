package UTESHOP.services.implement;

import java.util.List;

import UTESHOP.dao.IUserDao;
import UTESHOP.dao.implement.UserDao;
import UTESHOP.entity.User;
import UTESHOP.services.IUserService;
import jakarta.persistence.NoResultException;


public class UserService implements IUserService {
	IUserDao userDao = new UserDao();
	@Override
	public int count() {
		return userDao.count();
	}

	@Override
	public List<User> findAll(int page, int pagesize) {
		return userDao.findAll(page, pagesize);
	}

	@Override
	public List<User> findByFullname(String fullname) {
		return userDao.findByFullname(fullname);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findById(int id) {
		return userDao.findById(id);
	}

	@Override
	public User findByEmail(String email) {
		try {
			return userDao.findByEmail(email);
		}
		catch (NoResultException e){
			return null;
		}
	}

	@Override
	public void delete(int id) throws Exception {
		userDao.delete(id);
		
	}

	@Override
	public void update(User user) {
		userDao.update(user);
		
	}

	@Override
	public void insert(User user) {
		userDao.insert(user);
		
	}

	@Override
	public Boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}

	@Override
	public Boolean checkCode(String email, String code) {
		return userDao.checkCode(email, code);
	}

	@Override
	public boolean checkPassword(String email, String password) {
		return userDao.checkPassword(email, password);
	}

	@Override
	public List<User> findAllCustomers() {
		 return userDao.findAllCustomers();
	}

	@Override
	public List<User> findAllAdmin() {
		return userDao.findAllAdmin();
	}

	@Override
	public void insertAdmin(User user) {
		userDao.insert(user);
	}
	
	

}
