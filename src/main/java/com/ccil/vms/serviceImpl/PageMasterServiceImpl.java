package com.ccil.vms.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccil.vms.model.PageMaster;
import com.ccil.vms.model.Role;
import com.ccil.vms.model.Vendor;
import com.ccil.vms.repository.PageRepository;
import com.ccil.vms.repository.VendorRepository;
import com.ccil.vms.service.PageMasterService;
import com.ccil.vms.service.VendorService;

@Service
public class PageMasterServiceImpl implements PageMasterService {

	@Autowired
	PageRepository pageRepository;
	
	
	@Override
	public void save(PageMaster pageMaster) {
		pageRepository.save(pageMaster);
	}


	@Override
	public List<PageMaster> findByRole(Role role) {
		return pageRepository.findByRole(role);
	}

	

	@Override
	public List<PageMaster> findAllPageMasters() {
		return pageRepository.findAll();
	}

	

	
	

	

}
