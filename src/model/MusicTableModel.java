package model;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Dao.MusicDAO;
import Entities.Music;

@SuppressWarnings("serial")
public class MusicTableModel extends AbstractTableModel{

	private List<Music> musics;
	private List<String> colunas;
	private MusicDAO dao;
	
	public MusicTableModel(MusicDAO dao) {
		this.dao = dao;
		this.musics = dao.getLista();
		colunas = Arrays.asList("id","Nome", "Autor", "Gênero", "Duração");
	}
	
	@Override
	public int getRowCount() {
		return musics.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Music music = musics.get(rowIndex);
		
		switch(columnIndex) {
		case 0: {
			return rowIndex;
		}
		case 1: {
			return music.getName();
		}
		case 2: {
			return music.getAutor();
		}
		case 3: {
			return music.getGenre();
		}
		case 4: {
			return music.getDuration();
		}
		default:
			throw new IllegalArgumentException("Índice Inválido: " + columnIndex);
		}
	}
	
	@Override
	public String getColumnName(int i) {
		return colunas.get(i);
	}

}
