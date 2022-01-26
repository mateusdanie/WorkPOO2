package Dao;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Entities.Music;
import View.Window;

public class MusicDAO extends Music{
	
	
	public List <Music> musics = new ArrayList<Music>();

	private static Music music = new Music();
	
	public MusicDAO() {
		Music musica = new Music();
		musica.setName("PIMP");
		musica.setAutor("50 Cent");
		musica.setGenre("RAP");
		musica.setDuration(3);
		musics.add(musica);
		
		Music musica1 = new Music();
		musica1.setName("Godzilla");
		musica1.setAutor("Eminem");
		musica1.setGenre("RAP");
		musica1.setDuration(5);
		musics.add(musica1);
		
		Music musica2 = new Music();
		musica2.setName("Till I Colapse");
		musica2.setAutor("Eminem");
		musica2.setGenre("RAP");
		musica2.setDuration(4);
		musics.add(musica2);
	}
	
	public void addMusic(String name, String autor, String genre, int duration) {
		
				music.setName(name);
				music.setAutor(autor);
				music.setGenre(genre);
				music.setDuration(duration);
				
				musics.add(music);
				
				Window.carregaTabela();
				
				JOptionPane.showMessageDialog(null,"Musica Criada Com Sucesso!!!");
	}

	
	public void editMusic(int id, String name, String autor, String genre, int duration) {
			
				musics.get(id).setName(name);
				musics.get(id).setAutor(autor);
				musics.get(id).setGenre(genre);
				musics.get(id).setDuration(duration);
				
				Window.carregaTabela();
				
				JOptionPane.showMessageDialog(null,"Musica Editada Com Sucesso!!!");
		
	}
	
	public void removeMusic(int id) {
		
		musics.remove(id);
		Window.carregaTabela();
		
		JOptionPane.showMessageDialog(null,"Musica Removida Com Sucesso!!!");
		
	}
	
	
	public List<Music> getLista(){
		return musics;
	}
	
	
}
