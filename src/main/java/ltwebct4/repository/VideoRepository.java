package ltwebct4.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ltwebct4.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {
    List<Video> findByTitleContaining(String title);
}