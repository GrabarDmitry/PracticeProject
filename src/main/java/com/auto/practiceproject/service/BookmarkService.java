package com.auto.practiceproject.service;

import com.auto.practiceproject.model.Bookmark;

public interface BookmarkService {

  Bookmark findByUser();

  Bookmark updateBookmark(Bookmark bookmark);

  Bookmark addAnnouncementToBookmark(Long announcementId);
}
