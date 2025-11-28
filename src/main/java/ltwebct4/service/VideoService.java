package ltwebct4.service;

import java.util.List;

import ltwebct4.entity.Video;

public interface VideoService {

	List<Video> findAll();

	Video findById(String id);

	Video save(Video video);

	void deleteById(String id);

	List<Video> findByTitleContaining(String title);

}
