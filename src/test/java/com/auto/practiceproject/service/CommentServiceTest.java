package com.auto.practiceproject.service;

import com.auto.practiceproject.dao.CommentDAO;
import com.auto.practiceproject.model.Comment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @MockBean
    private CommentDAO commentDAO;

    @Autowired
    private CommentService commentService;

    @Test
    public void createCommentTest() {

        Comment commentTestData = new Comment("Hello", LocalDateTime.now(), null, null);
        Mockito.doReturn(commentTestData).when(commentDAO).save(any());

        Comment comment = commentService.createComment(commentTestData);

        Assert.assertNotNull(comment);
        Mockito.verify(commentDAO,
                Mockito.times(1)).save(ArgumentMatchers.any(Comment.class));

    }

}
