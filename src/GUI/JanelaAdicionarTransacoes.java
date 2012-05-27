package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import GerenciaDeDados.Repositorio;
import GerenciaDeDados.Transacao;


public class JanelaAdicionarTransacoes extends JFrame {

	JComboBox jComboBox1;
	JComboBox jComboBox2;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JRadioButton jRadioButton1;
	JRadioButton jRadioButton2;
	JRadioButton jRadioButton3;
	JRadioButton jRadioButton4;
	JRadioButton jRadioButton5;
	JButton jButton1;
	JButton jButton2;
	JButton jButton3;
	JTextArea textArea1;
	JScrollPane jScrollPane1;
	JTextField textfield1;
	Repositorio rep;
	JanelaInicial janela;

	public JanelaAdicionarTransacoes(Repositorio repNovo, JanelaInicial janelaNova){
		this.janela = janelaNova;
		this.rep = repNovo;

	}

	// construindo janela
	public void SetJanelaAdicionarTransacoes(){
		//seta tamanho e posicao
		this.setBounds(100, 40, 1050, 300);
		//Layout null eh pra eu poder colocar componentes onde eu quiser
		this.setLayout(null);
		//muda a cor do fundo
		//this.getContentPane().setBackground(Color.white);
		//titulo
		this.setTitle("Adicionar Operações");
		this.setFont(new Font("Comic Sans MS", 1, 11));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

	}
	// componentes da janela
	public void setComponentes(){

		LeitorDeListas buscaArquivo = new LeitorDeListas();


		this.jComboBox1 = new JComboBox();
		this.jComboBox1.setBounds(150,50, 90, 20);
		this.jComboBox1.setBackground(Color.white);
		this.jComboBox1.setModel(new DefaultComboBoxModel(buscaArquivo.RetornarVariaveis(this.rep.getListaVariaveis())));
		jComboBox1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox1ActionPerformed(evt);
			}

		});

		this.jComboBox2 = new JComboBox();
		this.jComboBox2.setBounds(30,50, 90, 20);
		this.jComboBox2.setBackground(Color.white);
		this.jComboBox2.setModel(new DefaultComboBoxModel(buscaArquivo.RetornarTrasacoes(this.rep.getTransacoes())));
		jComboBox2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox2ActionPerformed(evt);
			}

		});

		label1 = new JLabel("Transações");
		label1.setBounds(30, 10, 100, 40);
		label1.setFont(new Font("Comic Sans MS", 1, 18));

		label2 = new JLabel("Variáveis");
		label2.setBounds(150, 10, 100, 40);
		label2.setFont(new Font("Comic Sans MS", 1, 18));

		label3 = new JLabel("Operações");
		label3.setBounds(260, 10, 100, 40);
		label3.setFont(new Font("Comic Sans MS", 1, 18));


		this.jRadioButton1 = new JRadioButton();
		this.jRadioButton1.setBounds(260, 45,100,30);
		this.jRadioButton1.setText("Read");
		this.jRadioButton1.setFont(new Font("Comic Sans MS", 1, 18));

		jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton1ActionPerformed(evt);
			}

		});

		this.jRadioButton2 = new JRadioButton();
		this.jRadioButton2.setBounds(260, 85,100,30);
		this.jRadioButton2.setText("Write");
		this.jRadioButton2.setFont(new Font("Comic Sans MS", 1, 18));

		jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton2ActionPerformed(evt);
			}
		});

		this.jRadioButton3 = new JRadioButton();
		this.jRadioButton3.setBounds(260, 125,100,30);
		this.jRadioButton3.setText("Begin");
		this.jRadioButton3.setFont(new Font("Comic Sans MS", 1, 18));

		jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton3ActionPerformed(evt);
			}
		});

		this.jRadioButton4 = new JRadioButton();
		this.jRadioButton4.setBounds(260, 165,100,30);
		this.jRadioButton4.setText("End");
		this.jRadioButton4.setFont(new Font("Comic Sans MS", 1, 18));

		jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton4ActionPerformed(evt);
			}
		});

		this.jRadioButton5 = new JRadioButton();
		this.jRadioButton5.setBounds(260, 205,100,30);
		this.jRadioButton5.setText("Commit");
		this.jRadioButton5.setFont(new Font("Comic Sans MS", 1, 18));

		jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton5ActionPerformed(evt);
			}
		});

		this.jButton1 = new JButton("nova transação");
		this.jButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 15));
		this.jButton1.setBounds(30, 200, 220,40);

		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}

		});

		this.jButton2 = new JButton("Adicionar nova operação");
		this.jButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 15));
		this.jButton2.setBounds(370, 150, 220,40);

		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}

		});

		this.jButton3 = new JButton("Voltar a janela Executar");
		this.jButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 15));
		this.jButton3.setBounds(370, 200, 220,40);

		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}



		});

		label4 = new JLabel("Operações adicionadas");
		label4.setBounds(600, 10, 300, 40);
		label4.setFont(new Font("Comic Sans MS", 1, 18));

		textArea1 = new JTextArea(40, 40);
		jScrollPane1 = new JScrollPane(textArea1);
		jScrollPane1.setBounds(600, 50, 400, 190);

		label5 = new JLabel("valor");
		label5.setBounds(370, 60, 100, 40);
		label5.setFont(new Font("Comic Sans MS", 1, 15));

		textfield1 = new JTextField();
		textfield1.setBackground(Color.white);
		textfield1.setBounds(370, 90, 80, 20);
		textfield1.setEnabled(false);

	}

	public void addComponentes(){

		this.add(jComboBox1);
		this.add(jComboBox2);
		this.add(label1);
		this.add(label2);
		this.add(label3);
		this.add(label4);
		this.add(label5);
		this.add(jRadioButton1);
		this.add(jRadioButton2);
		this.add(jRadioButton3);
		this.add(jRadioButton4);
		this.add(jRadioButton5);
		this.add(jButton1);
		this.add(jButton2);
		this.add(jButton3);
		this.add(jScrollPane1);
		this.add(textfield1);


	}

	private void jComboBox1ActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}
	private void jComboBox2ActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	private void jRadioButton1ActionPerformed(ActionEvent evt) {
		jRadioButton2.setSelected(false);
		jRadioButton3.setSelected(false);
		jRadioButton4.setSelected(false);
		jRadioButton5.setSelected(false);
		textfield1.setEnabled(false);
		textfield1.setText("");

	}

	private void jRadioButton2ActionPerformed(ActionEvent evt) {
		jRadioButton1.setSelected(false);
		jRadioButton3.setSelected(false);
		jRadioButton4.setSelected(false);
		jRadioButton5.setSelected(false);	       
		textfield1.setEnabled(true);

		if(jRadioButton2.isSelected() == false){

			textfield1.setEnabled(false);
		}

	}

	private void jRadioButton3ActionPerformed(ActionEvent evt) {
		jRadioButton1.setSelected(false);
		jRadioButton2.setSelected(false);
		jRadioButton4.setSelected(false);
		jRadioButton5.setSelected(false);
		textfield1.setEnabled(false);
		textfield1.setText("");

	}

	private void jRadioButton4ActionPerformed(ActionEvent evt) {
		jRadioButton1.setSelected(false);
		jRadioButton2.setSelected(false);
		jRadioButton3.setSelected(false);
		jRadioButton5.setSelected(false);
		textfield1.setEnabled(false);
		textfield1.setText("");

	}

	private void jRadioButton5ActionPerformed(ActionEvent evt) {
		jRadioButton1.setSelected(false);
		jRadioButton2.setSelected(false);
		jRadioButton3.setSelected(false);
		jRadioButton4.setSelected(false);
		textfield1.setEnabled(false);
		textfield1.setText("");


	}

	private void jButton1ActionPerformed(ActionEvent evt) {
		LeitorDeListas buscaArquivo = new LeitorDeListas();
		int proxima = (this.rep.getTransacoes().size())+ 1;
		this.rep.adicionarTransacaoLista(this.rep.criarTransacao("T" + proxima));
		this.jComboBox2.setModel(new DefaultComboBoxModel(buscaArquivo.RetornarTrasacoes(this.rep.getTransacoes())));
		
		

	}

	private void jButton2ActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	private void jButton3ActionPerformed(ActionEvent evt) {
		this.janela.setVisible(true);
		this.setVisible(false);

	}

	//	public static void main(String[] args) {
	//
	//		JanelaAdicionarTransacoes janela = new JanelaAdicionarTransacoes();
	//		janela.SetJanelaAdicionarTransacoes();
	//		janela.setComponentes();
	//		janela.addComponentes();
	//		janela.setVisible(true);
	//
	//	}

}
