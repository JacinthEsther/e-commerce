package com.techie.user.repositories;

import com.techie.user.models.ImageUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepo extends JpaRepository<ImageUrl, Long> {
}
