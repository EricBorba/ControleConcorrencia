package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import fachada.Fachada;

import GerenciaDeDados.Repositorio;
import GerenciaDeDados.Transacao;

public class JanelaEscolherTransacao extends JFrame{
	JButton jButton3;
	JanelaInicial janela;
	Repositorio rep;
	JComboBox jComboBox1;
	JLabel label1;
	JButton jButton1;
	JButton jButton2;
	Fachada fachada;
	
	public JanelaEscolherTransacao(Repositorio repNovo, JanelaInicial janelaNova){
		this.janela = janelaNova;
		//rep = new Repositorio();
		this.rep = repNovo;

	}
	
	public void SetJanelaEscolherTransacao(){
		//seta tamanho e posicao
		this.setBounds(100, 40, 450, 300);
		//Layout null eh pra eu poder colocar componentes onde eu quiser
		this.setLayout(null);
		//muda a cor do fundo
		//this.getContentPane().setBackground(Color.white);
		//titulo
		this.setTitle("ESCOLHER TRANSAÇÃO");
		this.setFont(new Font("Comic Sans MS", 1, 11));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

	}
	
	public void setComponentes(){
		
		LeitorDeListas buscaArquivo = new LeitorDeListas();
		
		this.jComboBox1 = new JComboBox();
		this.jComboBox1.setBounds(40,50, 90, 20);
		this.jComboBox1.setBackground(Color.white);
		this.jComboBox1.setModel(new DefaultComboBoxModel(buscaArquivo.RetornarTrasacoes(this.rep.getTransacoes())));
		jComboBox1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox1ActionPerformed(evt);
			}
		});
		
		this.jButton1 = new JButton("Adicionar as operações");
		this.jButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 15));
		this.jButton1.setBounds(150, 100, 250,40);

		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}


		});

		this.jButton2 = new JButton("Voltar a janela Executar");
		this.jButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 15));
		this.jButton2.setBounds(150, 150, 250,40);

		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}


		});
		
		this.jButton3 = new JButton("nova transação");
		this.jButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 15));
		this.jButton3.setBounds(150, 50, 250,40);

		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}


		});
		
		label1 = new JLabel("Escolha a Transação");
		label1.setBounds(10, 10, 200, 40);
		label1.setFont(new Font("Comic Sans MS", 1, 15));
	}
	

	private void jComboBox1ActionPerformed(ActionEvent evt) {
		
	
	}
	

	private void jButton1ActionPerformed(ActionEvent evt) {
		
		Transacao transacao = new Transacao(jComboBox1.getSelectedItem().toString());
		//fachada.CriarRepositorio(rep).adicionarTransacaoLista(transacao);
		rep.adicionarTransacaoLista(transacao);
		JanelaAdicionarOperacoesNaTransacao janela = new JanelaAdicionarOperacoesNaTransacao(jComboBox1.getSelectedItem().toString(),this.rep,this);
		System.out.println(jComboBox1.getSelectedItem().toString());
		System.out.println(jComboBox1.getSelectedItem().toString()+":"+ transacao.getTempoDeCriacao());
		janela.SetJanelaAdicionarTransacoes();
		janela.setComponentes();
		janela.addComponentes();
		janela.setVisible(true);
		this.setVisible(false);
		
	}
	

	private void jButton2ActionPerformed(ActionEvent evt) {
		this.janela.setVisible(true);
		this.setVisible(false);
		
	}
	

	private void jButton3ActionPerformed(ActionEvent evt) {
		LeitorDeListas buscaArquivo = new LeitorDeListas();
		int proxima = (this.rep.getTransacoes().size())+ 1;
		this.rep.adicionarTransacaoLista(this.rep.criarTransacao("T" + proxima));
		this.jComboBox1.setModel(new DefaultComboBoxModel(buscaArquivo.RetornarTrasacoes(this.rep.getTransacoes())));
		
		
	}


	
	public void addComponentes(){
		
		this.add(jComboBox1);
		this.add(label1);
		this.add(jButton1);
		this.add(jButton2);
		this.add(jButton3);
	}
	

	
}
