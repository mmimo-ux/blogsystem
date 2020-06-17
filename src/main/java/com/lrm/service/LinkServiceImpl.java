package com.lrm.service;

import com.lrm.NotFoundException;
import com.lrm.dao.LinkRepository;
import com.lrm.dao.UserRepository;
import com.lrm.po.Link;
import com.lrm.po.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl implements LinkService{

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Link saveLink(Link link) {
        return linkRepository.save(link);
    }

    @Override
    public Page<Link> listOneLink(User user, Pageable pageable) {
        return linkRepository.findByUser(user,pageable);
    }

    @Override
    public Link getLink(Long id) {
        return linkRepository.findOne(id);
    }

    @Override
    public Link getLinkByurl(User user, String url) {
        return linkRepository.findByUserAndUrl(user,url);
    }

    @Override
    public void deleteLink(Long id) {
        linkRepository.delete(id);
    }

    @Override
    public Link updateLink(Long id, Link link) {
        Link l = linkRepository.findOne(id);
        if (l == null) {
            throw new NotFoundException("不存在该友链信息");
        }
        BeanUtils.copyProperties(link, l);

        return linkRepository.save(l);
    }

    @Override
    public List<Link> listLink(Long userId) {
        User user=userRepository.findOne(userId);
        return linkRepository.findByUser(user);
    }
}
