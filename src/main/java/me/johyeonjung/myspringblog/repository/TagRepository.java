package me.johyeonjung.myspringblog.repository;

import me.johyeonjung.myspringblog.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, HashSet> {
}
