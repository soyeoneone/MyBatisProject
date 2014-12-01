package com.lsp.dao;

import java.util.List;

import com.lsp.entity.Note;

public interface NoteDAO {
	public List<Note> selectNoteByUserid(int uid);
	public List<Note> selectAllNotes(Note note);
	public Note selectByNoteid(int nid);
}
