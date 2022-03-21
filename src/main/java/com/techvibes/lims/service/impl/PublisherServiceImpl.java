package com.techvibes.lims.service.impl;

import com.techvibes.lims.exception.NotFoundException;
import com.techvibes.lims.model.Publisher;
import com.techvibes.lims.repository.PublisherRepository;
import com.techvibes.lims.service.PublisherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Publisher> findAllPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher findPublisherById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Publisher not found  with ID %d", id)));
    }

    @Override
    public void createPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    @Override
    public void deletePublisher(Long id) {
        final Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Publisher not found  with ID %d", id)));

        publisherRepository.deleteById(publisher.getId());
    }
}
