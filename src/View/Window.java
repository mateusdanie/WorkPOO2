package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Dao.MusicDAO;
import Entities.Music;
import Lib.WindowConfigs;

public class Window{
	
	private static MusicDAO dao = new MusicDAO();
	private JFrame Window;
	private static JPanel panelTable, panelButton;
	private static JTable table;
	private static JButton exitButton, createButton, editButton, deleteButton;
	private static JScrollPane scrollPane;
	private FlowLayout FLayout = new FlowLayout();
	
	private static JPanel panelName = new JPanel();
	private static JPanel panelAutor = new JPanel();
	private static JPanel panelGenre = new JPanel();
	private static JPanel panelDuration = new JPanel();
	
	
	private static JLabel nameL = new JLabel("Nome:      ");
	private static JLabel autorL = new JLabel("Autor:      ");
	private static JLabel genreL = new JLabel("Gênero:   ");
	private static JLabel durationL = new JLabel("Duração: ");
	
	private static JTextField nameTxt = new JTextField(15);
	private static JTextField autorTxt = new JTextField(15);
	private static JTextField genreTxt = new JTextField(15);
	private static JTextField durationTxt = new JTextField(15);
	
	private String name;
	private String autor;
	private String genre;
	private int duration;
	
	private Color mainColor = new Color(54,54,54);
	private Color secondColor = new Color(128,128,128);

	
	public Window() {
		Window = new JFrame();
		panelTable = new JPanel();
		panelButton = new JPanel();
		table = new JTable();
		exitButton = new JButton("Sair");
		createButton = new JButton("Criar");
		editButton = new JButton("Editar");
		deleteButton = new JButton("Excluir");
		scrollPane = new JScrollPane();
		
		render();
		carregaTabela();
	}
	
	public void render() {
		WindowConfigs.windowConfig(Window, 600, 600);
		Window.getContentPane().setBackground(mainColor);
		labelConfig();
		panelConfig();
		buttonConfig();
		Window.add(panelTable);
		Window.add(panelName);
		Window.add(panelAutor);
		Window.add(panelGenre);
		Window.add(panelDuration);
		Window.add(panelButton);
		Window.setVisible(true);
		
	}
	
	
	public void panelConfig() {
		panelTable.setLayout(FLayout);
		
		table.setModel(new DefaultTableModel(
				new Object[][]{
			
				},
				new String[] {
						"id", "Nome", "Autor", "Gênero", "Duração"
				}
		));
		
		scrollPane.setViewportView(table);
		scrollPane.getViewport().setBackground(secondColor);
		panelTable.add(scrollPane);
		table.setBackground(secondColor);
		panelTable.setBackground(mainColor);
		panelName.setBackground(mainColor);
		panelAutor.setBackground(mainColor);
		panelGenre.setBackground(mainColor);
		panelDuration.setBackground(mainColor);
		panelButton.setBackground(mainColor);
		
		WindowConfigs.setLayout(panelName);
		WindowConfigs.setLayout(panelAutor);
		WindowConfigs.setLayout(panelGenre);
		WindowConfigs.setLayout(panelDuration);
		WindowConfigs.setLayout(panelButton);
		
		panelName.add(nameL);
		panelName.add(nameTxt);
		panelAutor.add(autorL);
		panelAutor.add(autorTxt);
		panelGenre.add(genreL);
		panelGenre.add(genreTxt);
		panelDuration.add(durationL);
		panelDuration.add(durationTxt);
		
		panelButton.setLayout(FLayout);
		panelButton.setSize(32, 32);
		panelButton.add(createButton);
		panelButton.add(editButton);
		panelButton.add(deleteButton);
		panelButton.add(exitButton);
	}

	public static void labelConfig() {
		nameL.setForeground(Color.WHITE);
		autorL.setForeground(Color.WHITE);
		genreL.setForeground(Color.WHITE);
		durationL.setForeground(Color.WHITE);
	}
	public static void carregaTabela() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);

		
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		
		try {
			int i = 0;
			for(Music m: dao.getLista()) {
					model.addRow(new Object[] {
							i,
							m.getName(),
							m.getAutor(),
							m.getGenre(),
							m.getDuration()
					});
				
				i++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void buttonConfig() {
		
		createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					name = nameTxt.getText();
					autor = autorTxt.getText();
					genre = genreTxt.getText();
					duration = Integer.parseInt(durationTxt.getText());
					dao.addMusic(name, autor, genre, duration);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"Formato De String Não Compatível!!!");
				}
				
				
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame idWindow = new JFrame();
				JPanel idPanel = new JPanel();
				JLabel idLabel = new JLabel("ID:");
				JTextField idTxt = new JTextField(15);
				JButton okButton = new JButton("Ok");
				
				WindowConfigs.setLayout(idWindow);
				WindowConfigs.setLayout(idPanel);
				WindowConfigs.windowConfig(idWindow, 300, 100);
				idPanel.add(idLabel);
				idPanel.add(idTxt);
				idPanel.add(okButton);
				idWindow.add(idPanel);
				
				try {
					name = nameTxt.getText();
					autor = autorTxt.getText();
					genre = genreTxt.getText();
					duration = Integer.parseInt(durationTxt.getText());
					
					idWindow.setVisible(true);
					
					try {
						okButton.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								try {
									int id = Integer.parseInt(idTxt.getText());
									
									if(id <= dao.musics.size()-1) {
										dao.editMusic(id, name, autor, genre, duration);
									}else {
										JOptionPane.showMessageDialog(null,"Indíce Invalido!!!");
									}
								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null,"String Vázia Ou Formato Imcompativel!!!");
								}
									
							}
						});
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null,"String Vázia Ou Formato Imcompativel!!!");
					}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"Formato De String Não Compatível!!!");
				}
				
				
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame idWindow = new JFrame();
				JPanel idPanel = new JPanel();
				JLabel idLabel = new JLabel("ID:");
				JTextField idTxt = new JTextField(15);
				JButton okButton = new JButton("Ok");
				
				WindowConfigs.setLayout(idWindow);
				WindowConfigs.setLayout(idPanel);
				WindowConfigs.windowConfig(idWindow, 300, 100);
				idPanel.add(idLabel);
				idPanel.add(idTxt);
				idPanel.add(okButton);
				idWindow.add(idPanel);
				
				idWindow.setVisible(true);
				
				try {
					okButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								int id = Integer.parseInt(idTxt.getText());
								
								if(id <= dao.musics.size()-1) {
									dao.removeMusic(id);
								}else {
									JOptionPane.showMessageDialog(null,"Indíce Invalido");
								}
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null,"String Vázia Ou Formato Imcompativel!!!");
							}
								
						}
					});
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"String Vázia Ou Formato Imcompativel!!!");
				}
				
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}
}
