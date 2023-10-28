package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.Comments;

public interface CommentService {
	List<Comments> commentList();
	String addComment(Comments comment);

}
