package com.lrm.service;

import com.lrm.po.Link;
import com.lrm.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LinkService {

    Link saveLink(Link link);

    Page<Link> listOneLink(User user, Pageable pageable);

    Link getLink(Long id);

    Link getLinkByurl(User user,String url);

    void deleteLink(Long id);

    Link updateLink(Long id,Link link);

    List<Link> listLink(Long userId);

}
