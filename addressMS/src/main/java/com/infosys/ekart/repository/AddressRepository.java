package com.infosys.ekart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.ekart.entity.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer>{

	public AddressEntity findByAddressId(Integer addressId);

	public List<AddressEntity> findByUserId(String userId);

}