package UTESHOP.services.implement;

import UTESHOP.dao.IAddressDao;
import UTESHOP.dao.implement.AddressDao;
import UTESHOP.entity.Address;
import UTESHOP.services.IAddressService;


public class AddressService implements IAddressService {
	IAddressDao addressDao = new AddressDao();
	@Override
	public Address findByAddressId(int id) {
		return addressDao.findByAddressId(id);
	}

	@Override
	public void update(Address address) {
		addressDao.update(address);
		
	}

	@Override
	public Address insert(Address address) {
		return addressDao.insert(address);
	}

}
