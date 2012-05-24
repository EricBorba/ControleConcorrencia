package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoundedRangeModel;
import javax.swing.BoxLayout;




public class JanelaInicial extends JFrame{
	//SimpleTableModel modelo;
	JTable EstadoDisco;
	JScrollPane scrollDisco;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JTextField textfield1;
	JTextArea textArea1;
	JScrollPane jScrollPane1;
	JTextArea textArea2;
	JScrollPane jScrollPane2;
	JRadioButton jRadioButton1;
	JRadioButton jRadioButton2;
	JRadioButton jRadioButton3;
	JRadioButton jRadioButton4;
	JRadioButton jRadioButton5;
	JRadioButton jRadioButton6;
	JRadioButton jRadioButton7;
	JButton jButton1;
	JButton jButton2;
	JButton jButton3;
	
// construindo janela
	public void setJanelaInicial(){
		//seta tamanho e posicao
		this.setBounds(200, 10, 1000, 650);
		//Layout null eh pra eu poder colocar componentes onde eu quiser
		this.setLayout(null);
		//muda a cor do fundo
		//this.getContentPane().setBackground(Color.white);
		//titulo
		this.setTitle("GERADOR DE CONCORR�NCIA");
		this.setFont(new Font("Comic Sans MS", 1, 11));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

	}
// componentes da janela
	public void setComponentes(){
		label1 = new JLabel("Estado do Disco");		
		label1.setBounds(350, 20, 200, 40);
		label1.setFont(new Font("Comic Sans MS", 1, 15));
		
		label2 = new JLabel("Opera��es a serem Executadas");		
		label2.setBounds(40, 20, 300, 40);
		label2.setFont(new Font("Comic Sans MS", 1, 15));
		
		label3 = new JLabel("LOG");		
		label3.setBounds(40, 320, 250, 40);
		label3.setFont(new Font("Comic Sans MS", 1, 15));

		textArea1 = new JTextArea(40, 40);
		jScrollPane1 = new JScrollPane(textArea1);
		jScrollPane1.setBounds(40, 50, 300, 250);
				
		textArea2 = new JTextArea(40, 40);
		jScrollPane2 = new JScrollPane(textArea2);
		jScrollPane2.setBounds(40, 350, 560, 250);
		

		this.jRadioButton1 = new JRadioButton();
		this.jRadioButton1.setBounds(660, 70,200,30);
		this.jRadioButton1.setText("Lock Bin�rio");
		this.jRadioButton1.setFont(new Font("Comic Sans MS", 1, 18));
		
		jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
		
		this.jRadioButton2 = new JRadioButton();
		this.jRadioButton2.setBounds(660, 100,200,30);
		this.jRadioButton2.setText("Lock Multiplo");
		this.jRadioButton2.setFont(new Font("Comic Sans MS", 1, 18));
		
		jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
		
		this.jRadioButton3 = new JRadioButton();
		this.jRadioButton3.setBounds(660, 130,300,30);
		this.jRadioButton3.setText("Bloqueio de 2 fases B�sico");
		this.jRadioButton3.setFont(new Font("Comic Sans MS", 1, 18));
		
		jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
		
		this.jRadioButton4 = new JRadioButton();
		this.jRadioButton4.setBounds(660, 160,320,30);
		this.jRadioButton4.setText("Bloqueio de 2 fases Conservador");
		this.jRadioButton4.setFont(new Font("Comic Sans MS", 1, 18));
		
		jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
		
		this.jRadioButton5 = new JRadioButton();
		this.jRadioButton5.setBounds(660, 190,300,30);
		this.jRadioButton5.setText("Bloqueio de 2 fases Estrito");
		this.jRadioButton5.setFont(new Font("Comic Sans MS", 1, 18));
		
		jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });
		
		this.jRadioButton6 = new JRadioButton();
		this.jRadioButton6.setBounds(660, 220,300,30);
		this.jRadioButton6.setText("Time Stamping Wait-Die");
		this.jRadioButton6.setFont(new Font("Comic Sans MS", 1, 18));
		
		jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });
		
		this.jRadioButton7 = new JRadioButton();
		this.jRadioButton7.setBounds(660, 250,300,30);
		this.jRadioButton7.setText("Time Stamping Wound-Wait");
		this.jRadioButton7.setFont(new Font("Comic Sans MS", 1, 18));
		
		jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });
		
		this.jButton1 = new JButton("Adicionar Opera��es");
		this.jButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 15));
		this.jButton1.setBounds(700, 450, 250,40);

		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		
		this.jButton2 = new JButton("Executar Transa��es");
		this.jButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 15));
		this.jButton2.setBounds(700, 500, 250,40);

		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});
		
		this.jButton3 = new JButton("SAIR");
		this.jButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 15));
		this.jButton3.setBounds(700, 550, 250,40);

		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}

			});
			
	}
	
// tabela do estado atual do disco
	public void setEstadoAtual(){

		Arquivo arq = new Arquivo("DadosDisco.txt", "lixo.txt");
					
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] {"Vari�vel", "Valor"});
		while(!arq.isEndOfFile()){
			modelo.addRow(new Object[] {arq.acharIgual(arq), arq.acharQuebra(arq)});
			
		}
	
		
		this.EstadoDisco = new JTable(modelo);  
		//EstadoDisco.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		this.scrollDisco = new JScrollPane(EstadoDisco);
		scrollDisco.setBounds(350, 50, 300, 250);
		scrollDisco.setBackground(Color.white);
		System.out.println();
	

	}
	
	 private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		 JanelaAdicionarTransacoes janela = new JanelaAdicionarTransacoes();
			janela.SetJanelaAdicionarTransacoes();
			janela.setComponentes();
			janela.addComponentes();
			janela.setVisible(true);
			this.dispose();
	    	
	}
	 
	 private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
	       //a��o radiobutton1  	
	    	
	}
	 
	 private void jButton3ActionPerformed(ActionEvent evt) {
			this.dispose();
			
		}
	
	
	 private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {
	       jRadioButton2.setSelected(false);
	       jRadioButton3.setSelected(false);
	       jRadioButton4.setSelected(false);
	       jRadioButton5.setSelected(false);
	       jRadioButton6.setSelected(false);
	       jRadioButton7.setSelected(false);
	       
		
	    	
	}

	 private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		   jRadioButton1.setSelected(false);
	       jRadioButton3.setSelected(false);
	       jRadioButton4.setSelected(false);
	       jRadioButton5.setSelected(false);
	       jRadioButton6.setSelected(false);
	       jRadioButton7.setSelected(false); 	
	    	
	}
	 
	 private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		   jRadioButton1.setSelected(false);
	       jRadioButton2.setSelected(false);
	       jRadioButton4.setSelected(false);
	       jRadioButton5.setSelected(false);
	       jRadioButton6.setSelected(false);
	       jRadioButton7.setSelected(false);   	
	    	
	}
	
	 private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		   jRadioButton1.setSelected(false);
	       jRadioButton2.setSelected(false);
	       jRadioButton3.setSelected(false);
	       jRadioButton5.setSelected(false);
	       jRadioButton6.setSelected(false);
	       jRadioButton7.setSelected(false);   	
	    	
	}

	 private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		   jRadioButton1.setSelected(false);
	       jRadioButton2.setSelected(false);
	       jRadioButton3.setSelected(false);
	       jRadioButton4.setSelected(false);
	       jRadioButton6.setSelected(false);
	       jRadioButton7.setSelected(false); 	
	    	
	}
	 
	 private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		   jRadioButton1.setSelected(false);
	       jRadioButton2.setSelected(false);
	       jRadioButton3.setSelected(false);
	       jRadioButton4.setSelected(false);
	       jRadioButton5.setSelected(false);
	       jRadioButton7.setSelected(false);  	
	    	
	}
	 
	 private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {
		   jRadioButton1.setSelected(false);
	       jRadioButton2.setSelected(false);
	       jRadioButton3.setSelected(false);
	       jRadioButton4.setSelected(false);
	       jRadioButton5.setSelected(false);
	       jRadioButton6.setSelected(false); 	
	    	
	}
	 
	 
	public void addComponentes(){
		this.add(this.label1);
		this.add(this.label2);
		this.add(this.label3);
		this.add(this.scrollDisco);
		this.add(this.jScrollPane1);
		this.add(this.jScrollPane2);
		this.add(this.jRadioButton1);
		this.add(this.jRadioButton2);
		this.add(this.jRadioButton3);
		this.add(this.jRadioButton4);
		this.add(this.jRadioButton5);
		this.add(this.jRadioButton6);
		this.add(this.jRadioButton7);
		this.add(this.jButton1);
		this.add(this.jButton2);
		this.add(this.jButton3);
	}


	public static void main(String[] args) {
		JanelaInicial janela = new JanelaInicial();
		janela.setJanelaInicial();
		janela.setEstadoAtual();
		janela.setComponentes();		
		janela.addComponentes();
		janela.setVisible(true);
	}


}