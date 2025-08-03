package com.os.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.os.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization,Long>{

	 Organization findByOrganizationCode(String organizationCode);
}
