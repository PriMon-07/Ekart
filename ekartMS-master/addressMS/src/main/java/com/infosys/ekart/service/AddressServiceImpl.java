package com.infosys.ekart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.infosys.ekart.entity.AddressEntity;
import com.infosys.ekart.exception.ErrorOnServerException;
import com.infosys.ekart.model.Address;
import com.infosys.ekart.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository repository;

	@Autowired
	Environment env;

	@Override
	public List<Address> getAddress(String userId) throws ErrorOnServerException {
		try {
			final List<Address> addresss = new ArrayList<>();
			List<AddressEntity> entity = repository.findByUserId(userId);
			if (!entity.isEmpty()) {
				entity.forEach(x -> {
					Address address = new Address();
					BeanUtils.copyProperties(x, address);
					addresss.add(address);
				});
			}
			return addresss;
		} catch (DataAccessException e) {
			throw new ErrorOnServerException(env.getProperty("ERROR_ON_SERVER"));
		}

	}

	@Override
	public String saveAddress(String userId, Address address) {

		AddressEntity entity = address.toAddressEntity();
		entity.setUserId(userId);
		entity.setAddressId((int) repository.count() + 1);
		repository.save(entity);

		return "Added successfully!!";

	}

	@Override
	public AddressEntity getAddress(Integer addressId) {
		return repository.findByAddressId(addressId);
	}

	@Override
	public boolean isValidUserId(String userId) {
		return new RestTemplate().getForObject("http://ACCOUNTMS/" + userId + "/checkuser", Boolean.class);
		// "/validate", Boolean.class); //Need to confirm with Navaniet Sen
		//return new RestTemplate().getForObject("https://ad7b32dc-f324-42e4-b5ed-f8d6e0bc5d16.mock.pstmn.io/1/validate",
				//Boolean.class);
	}

	@Override
	public String updateAddress(Address address, Integer addressId) {
		AddressEntity entity = repository.getOne(addressId);
		entity.setAddress(address.getAddress());
		entity.setCity(address.getCity());
		entity.setPinCode(address.getPinCode());
		entity.setPhoneNumber(address.getPhoneNumber());
		entity.setState(address.getState());
		repository.save(entity);
		return "Update successfull!!";
	}

	@Override
	public String deleteAddress(int addressId) {
		repository.deleteById(addressId);
		return "Deleted Successfully!!";
	}
}
