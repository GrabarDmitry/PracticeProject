package com.auto.practiceproject.service;

import com.auto.practiceproject.model.Announcement;

import java.util.List;

public interface BookmarkService {

    List<Announcement> findByUserFromBookmark();

}
