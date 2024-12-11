package UTESHOP.services;

import UTESHOP.entity.Address;

public interface IAddressService {
	Address findByAddressId(int id);
	
	void update(Address address);
	
	Address insert(Address address);
}
