package com.ccil.vms.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccil.vms.model.Vendor;

import com.ccil.vms.repository.VendorRepository;
import com.ccil.vms.service.VendorService;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	VendorRepository vendorRepository;
	
	
	@Override
	public void save(Vendor vendor) {
		vendorRepository.save(vendor);
	}


	@Override
	public Vendor findByVendorName(String vendorName) {
		return vendorRepository.findByVendorName(vendorName);
	}


	@Override
	public Vendor findByVendorDomain(String vendorDomain) {
			return vendorRepository.findByVendorDomain(vendorDomain);
	}


	@Override
	public List<Vendor> findAllVendors() {
		return vendorRepository.findAll();
	}

	

	
	

	

}
