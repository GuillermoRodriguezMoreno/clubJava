package org.iesvdm.clubjava.repository;

import org.iesvdm.clubjava.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Search by post id ordered by comment date
    public List<Comment> findAllByPostIdOrderByCommentDate(Long postId);
}
