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

import GerenciaDeDados.Repositorio;
import GerenciaDeDados.Transacao;
import GerenciaDeDados.Variavel;




public class JanelaInicial extends JFrame{
	//SimpleTableModel modelo;
	JTable EstadoDisco;
	JScrollPane scrollDisco;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	JLabel label7;
	JTextField textfield1;
	JTextArea textArea1;
	JScrollPane jScrollPane1;
	JTextArea textArea2;
	JScrollPane jScrollPane2;
	JTextArea textArea3;
	JScrollPane jScrollPane3;
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
	Repositorio rep;
	JanelaAdicionarTransacoes janela;
	
	boolean radio1Selecionado;
	boolean radio2Selecionado;
	boolean radio3Selecionado;

	public JanelaInicial(Repositorio repNovo){
		this.rep = repNovo;
		

	}


	// construindo janela
	public void setJanelaInicial(){
		//seta tamanho e posicao
		this.setBounds(20, 10, 1300, 650);
		//Layout null eh pra eu poder colocar componentes onde eu quiser
		this.setLayout(null);
		//muda a cor do fundo
		//this.getContentPane().setBackground(Color.white);
		//titulo
		this.setTitle("GERADOR DE CONCORRÊNCIA");
		this.setFont(new Font("Comic Sans MS", 1, 11));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

	}
	// componentes da janela
	public void setComponentes(){
		Variavel var1 = new Variavel("a", "13");
		Variavel var2 = new Variavel("b", "67");
		Variavel var3 = new Variavel("c", "78");
		Variavel var4 = new Variavel("d", "12");
		Variavel var5 = new Variavel("e", "43");
		Variavel var6 = new Variavel("f", "31");


		this.rep.getListaVariaveis().add(var1);
		this.rep.getListaVariaveis().add(var2);
		this.rep.getListaVariaveis().add(var3);
		this.rep.getListaVariaveis().add(var4);
		this.rep.getListaVariaveis().add(var5);
		this.rep.getListaVariaveis().add(var6);
		
		Transacao t1 = new Transacao("T1");
		Transacao t2 = new Transacao("T2");
		Transacao t3 = new Transacao("T3");
		Transacao t4 = new Transacao("T4");
		
		this.rep.getTransacoes().add(t1);
		this.rep.getTransacoes().add(t2);
		this.rep.getTransacoes().add(t3);
		this.rep.getTransacoes().add(t4);
		

		label1 = new JLabel("Estado do Disco");		
		label1.setBounds(350, 20, 200, 40);
		label1.setFont(new Font("Comic Sans MS", 1, 15));

		label2 = new JLabel("Operações a serem Executadas");		
		label2.setBounds(40, 20, 300, 40);
		label2.setFont(new Font("Comic Sans MS", 1, 15));

		label3 = new JLabel("LOG");		
		label3.setBounds(40, 320, 250, 40);
		label3.setFont(new Font("Comic Sans MS", 1, 15));
		
		label4 = new JLabel("Tabela de Bloqueios");		
		label4.setBounds(510, 320, 250, 40);
		label4.setFont(new Font("Comic Sans MS", 1, 15));

		textArea1 = new JTextArea(40, 40);
		jScrollPane1 = new JScrollPane(textArea1);
		jScrollPane1.setBounds(40, 50, 300, 250);

		///log
		textArea2 = new JTextArea(40, 40);
		jScrollPane2 = new JScrollPane(textArea2);
		jScrollPane2.setBounds(40, 350, 460, 250);
		
		/// bloqueios
		textArea3 = new JTextArea(40, 40);
		jScrollPane3 = new JScrollPane(textArea3);
		jScrollPane3.setBounds(510, 350, 460, 250);

		label5 = new JLabel("BLOQUEIO");		
		label5.setBounds(660, 20, 250, 40);
		label5.setFont(new Font("Comic Sans MS", 1, 15));

		radio1Selecionado = false;
		this.jRadioButton1 = new JRadioButton();
		this.jRadioButton1.setBounds(660, 50,300,30);
		this.jRadioButton1.setText("Bloqueio de 2 fases Básico");
		this.jRadioButton1.setFont(new Font("Comic Sans MS", 1, 18));

		jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton1ActionPerformed(evt);
			}
		});
		
		
		radio2Selecionado = false;
		this.jRadioButton2 = new JRadioButton();
		this.jRadioButton2.setBounds(660, 80,300,30);
		this.jRadioButton2.setText("Bloqueio de 2 fases Estrito");
		this.jRadioButton2.setFont(new Font("Comic Sans MS", 1, 18));

		jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton2ActionPerformed(evt);
			}
		});

		radio1Selecionado = false;
		this.jRadioButton3 = new JRadioButton();
		this.jRadioButton3.setBounds(660, 110,350,30);
		this.jRadioButton3.setText("Bloqueio de 2 fases Conservador");
		this.jRadioButton3.setFont(new Font("Comic Sans MS", 1, 18));

		jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton3ActionPerformed(evt);
			}
		});
		
		label6 = new JLabel("TIPO DE BLOQUEIO");		
		label6.setBounds(660, 135, 250, 40);
		label6.setFont(new Font("Comic Sans MS", 1, 15));

		this.jRadioButton4 = new JRadioButton();
		this.jRadioButton4.setBounds(660, 165,320,30);
		this.jRadioButton4.setText("Lock Binário");
		this.jRadioButton4.setFont(new Font("Comic Sans MS", 1, 18));
		this.jRadioButton4.setEnabled(false);

		jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton4ActionPerformed(evt);
			}
		});

		this.jRadioButton5 = new JRadioButton();
		this.jRadioButton5.setBounds(660, 195,300,30);
		this.jRadioButton5.setText("Lock Multiplo");
		this.jRadioButton5.setFont(new Font("Comic Sans MS", 1, 18));
		this.jRadioButton5.setEnabled(false);

		jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton5ActionPerformed(evt);
			}
		});
		
		label7 = new JLabel("TRATAMENTO");		
		label7.setBounds(660, 220, 250, 40);
		label7.setFont(new Font("Comic Sans MS", 1, 15));

		this.jRadioButton6 = new JRadioButton();
		this.jRadioButton6.setBounds(660, 255,300,30);
		this.jRadioButton6.setText("Time Stamping Wait-Die");
		this.jRadioButton6.setFont(new Font("Comic Sans MS", 1, 18));
		this.jRadioButton6.setEnabled(false);

		jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton6ActionPerformed(evt);
			}
		});

		this.jRadioButton7 = new JRadioButton();
		this.jRadioButton7.setBounds(660, 280,300,30);
		this.jRadioButton7.setText("Time Stamping Wound-Wait");
		this.jRadioButton7.setFont(new Font("Comic Sans MS", 1, 18));
		this.jRadioButton7.setEnabled(false);

		jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton7ActionPerformed(evt);
			}
		});

		this.jButton1 = new JButton("Adicionar Operações");
		this.jButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 15));
		this.jButton1.setBounds(1000, 450, 250,40);

		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		this.jButton2 = new JButton("Executar Transações");
		this.jButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 15));
		this.jButton2.setBounds(1000, 500, 250,40);

		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		this.jButton3 = new JButton("SAIR");
		this.jButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 15));
		this.jButton3.setBounds(1000, 550, 250,40);

		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}

		});

	}

	// tabela do estado atual do disco
	public void setEstadoAtual(){

	
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] {"Variável", "Valor"});
		
		int i = 0;
		while(i < this.rep.getListaVariaveis().size()){

			modelo.addRow(new Object[] {this.rep.getListaVariaveis().get(i).getNomeVariavel(), this.rep.getListaVariaveis().get(i).getValor()});
			i++;

		}
		
		

		this.EstadoDisco = new JTable(modelo);  
		this.scrollDisco = new JScrollPane(EstadoDisco);
		scrollDisco.setBounds(350, 50, 300, 250);
		scrollDisco.setBackground(Color.white);
	


	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		
		JanelaAdicionarTransacoes janela = new JanelaAdicionarTransacoes(this.rep,this);
		janela.SetJanelaAdicionarTransacoes();
		janela.setComponentes();
		janela.addComponentes();
		janela.setVisible(true);
		this.setVisible(false);
	
		
		
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		//ação radiobutton1  	

	}

	private void jButton3ActionPerformed(ActionEvent evt) {
		this.dispose();

	}


	private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		jRadioButton2.setSelected(false);
		jRadioButton3.setSelected(false);
		if(radio1Selecionado == false){
		jRadioButton4.setEnabled(true);
		jRadioButton5.setEnabled(true);
		jRadioButton6.setEnabled(true);
		jRadioButton7.setEnabled(true);
		radio1Selecionado = true;
		radio3Selecionado = false;
		radio2Selecionado = false;
		}else{
			jRadioButton4.setEnabled(false);
			jRadioButton5.setEnabled(false);
			jRadioButton6.setEnabled(false);
			jRadioButton7.setEnabled(false);
			radio1Selecionado = false;
		}

	}

	private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		jRadioButton1.setSelected(false);
		jRadioButton3.setSelected(false);
		
		if(radio2Selecionado == false){
			jRadioButton4.setEnabled(true);
			jRadioButton5.setEnabled(true);
			jRadioButton6.setEnabled(true);
			jRadioButton7.setEnabled(true);
			radio2Selecionado = true;
			radio1Selecionado = false;
			radio3Selecionado = false;
			}else{
				jRadioButton4.setEnabled(false);
				jRadioButton5.setEnabled(false);
				jRadioButton6.setEnabled(false);
				jRadioButton7.setEnabled(false);
				radio2Selecionado = false;
			}

	}

	private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		jRadioButton1.setSelected(false);
		jRadioButton2.setSelected(false);
		if(radio3Selecionado == false){
			jRadioButton4.setEnabled(true);
			jRadioButton5.setEnabled(true);
			jRadioButton6.setEnabled(true);
			jRadioButton7.setEnabled(true);
			radio3Selecionado = true;
			radio1Selecionado = false;
			radio2Selecionado = false;
			}else{
				jRadioButton4.setEnabled(false);
				jRadioButton5.setEnabled(false);
				jRadioButton6.setEnabled(false);
				jRadioButton7.setEnabled(false);
				radio3Selecionado = false;
			}
	}

	private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		jRadioButton5.setSelected(false);
		//jRadioButton6.setSelected(false);
		//jRadioButton7.setSelected(false);   	

	}

	private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		jRadioButton4.setSelected(false);
		//jRadioButton6.setSelected(false);
		//jRadioButton7.setSelected(false); 	

	}

	private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		//jRadioButton4.setSelected(false);
		//jRadioButton5.setSelected(false);
		jRadioButton7.setSelected(false);  	

	}

	private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {
		//jRadioButton4.setSelected(false);
		//jRadioButton5.setSelected(false);
		jRadioButton6.setSelected(false); 	

	}


	public void addComponentes(){
		this.add(this.label1);
		this.add(this.label2);
		this.add(this.label3);
		this.add(this.label4);
		this.add(this.label5);
		this.add(this.label6);
		this.add(this.label7);
		this.add(this.scrollDisco);
		this.add(this.jScrollPane1);
		this.add(this.jScrollPane2);
		this.add(this.jScrollPane3);
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
		Repositorio reptemp = new Repositorio();	
		JanelaInicial janela = new JanelaInicial(reptemp);
		janela.setJanelaInicial();
		janela.setComponentes();
		janela.setEstadoAtual();
		janela.addComponentes();
		janela.setVisible(true);
	}


}
