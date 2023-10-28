package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Comments;
import com.example.demo.Repository.CommentRepository;
@Service
public class CommentServiceImplementation implements CommentService{
	
	@Autowired
	CommentRepository cr;

	@Override
	public List<Comments> commentList() {
		// TODO Auto-generated method stub
		return cr.findAll();
	}

	@Override
	public String addComment(Comments comment) {
		cr.save(comment);
		return "Comment Added";
	}

}
