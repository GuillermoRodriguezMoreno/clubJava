package org.iesvdm.clubjava.repository;

import org.iesvdm.clubjava.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Search by tittle ordered by post date
    public List<Post> findAllByTitleContainingIgnoreCaseOrderByPostDate(String title);

    // Search by tag ordered by post date
    public List<Post> findAllByTagsNameContainingIgnoreCaseOrderByPostDate(String tag);
}
