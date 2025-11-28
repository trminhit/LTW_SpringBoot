package ltwebct4.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ltwebct4.entity.Video;
import ltwebct4.repository.VideoRepository;
import ltwebct4.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoRepository videoRepository;

    @Override
    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    @Override
    public Video findById(String id) {
        Optional<Video> opt = videoRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public Video save(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public void deleteById(String id) {
        videoRepository.deleteById(id);
    }

    @Override
    public List<Video> findByTitleContaining(String title) {
        return videoRepository.findByTitleContaining(title);
    }
}