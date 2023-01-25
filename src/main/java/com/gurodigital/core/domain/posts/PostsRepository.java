package com.gurodigital.core.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository @Repository를 추가할 필요 없습니다.
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
