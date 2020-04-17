package server.dao;


import api.model.Comment;

public interface CommentDao {

    void save(Comment objectToCreate);
}
