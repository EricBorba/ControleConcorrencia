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
import MecanismosDeConcorrencia.Bloqueio2FasesEstrito;




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
	JLabel label8;
	JTextField textfield1;
	JTextArea textArea1;
	JScrollPane jScrollPane1;
	JTextArea textArea2;
	JScrollPane jScrollPane2;
	JTextArea textArea3;
	JScrollPane jScrollPane3;
	JTextArea textArea4;
	JScrollPane jScrollPane4;
	//JRadioButton jRadioButton1;
	JRadioButton jRadioButton2;
	//JRadioButton jRadioButton3;
	//JRadioButton jRadioButton4;
	JRadioButton jRadioButton5;
	JRadioButton jRadioButton6;
	//JRadioButton jRadioButton7;
	JButton jButton1;
	JButton jButton2;
	JButton jButton3;
	Repositorio rep;
	JanelaAdicionarOperacoesNaTransacao janela;
	boolean radio1Selecionado;
	boolean radio2Selecionado;
	boolean radio3Selecionado;
	DefaultTableModel modelo;
	boolean inicioModelo;


	public JanelaInicial(Repositorio repNovo){
		this.rep = repNovo;


	}

	public void criarRepositorio(){

		rep = new Repositorio();
	}

	// construindo janela
	public void setJanelaInicial(){
		modelo = new DefaultTableModel(new Object[][] {}, new String[] {"Vari�vel", "Valor"});
		inicioModelo = true;
		//seta tamanho e posicao
		this.setBounds(10, 10, 1260, 700);
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

		this.rep.getListaVariaveisAntigas().add(var1);
		this.rep.getListaVariaveisAntigas().add(var2);
		this.rep.getListaVariaveisAntigas().add(var3);
		this.rep.getListaVariaveisAntigas().add(var4);
		this.rep.getListaVariaveisAntigas().add(var5);
		this.rep.getListaVariaveisAntigas().add(var6);


		label1 = new JLabel("Estado do Disco");		
		label1.setBounds(500, 20, 200, 40);
		label1.setFont(new Font("Comic Sans MS", 1, 15));

		label2 = new JLabel("Opera��es a serem Executadas");		
		label2.setBounds(40, 20, 300, 40);
		label2.setFont(new Font("Comic Sans MS", 1, 15));

		label3 = new JLabel("LOG");		
		label3.setBounds(22, 340, 250, 40);
		label3.setFont(new Font("Comic Sans MS", 1, 15));

		label4 = new JLabel("Tabela de Bloqueios");		
		label4.setBounds(434, 340, 250, 40);
		label4.setFont(new Font("Comic Sans MS", 1, 15));

		label8 = new JLabel("Lista Suja");		
		label8.setBounds(846, 340, 250, 40);
		label8.setFont(new Font("Comic Sans MS", 1, 15));

		textArea1 = new JTextArea(40, 40);
		jScrollPane1 = new JScrollPane(textArea1);
		jScrollPane1.setBounds(40, 50, 400, 250);

		///log
		textArea2 = new JTextArea(40, 40);
		jScrollPane2 = new JScrollPane(textArea2);
		jScrollPane2.setBounds(22, 370, 390, 250);		
		/// bloqueios
		textArea3 = new JTextArea(40, 40);
		jScrollPane3 = new JScrollPane(textArea3);
		jScrollPane3.setBounds(434, 370, 390, 250);

		/// Tabela da professora
		textArea4 = new JTextArea(40, 40);
		jScrollPane4 = new JScrollPane(textArea4);
		jScrollPane4.setBounds(846, 370, 390, 250);
//
//		label5 = new JLabel("BLOQUEIO");		
//		label5.setBounds(960, 20, 250, 40);
//		label5.setFont(new Font("Comic Sans MS", 1, 15));
//
//	
//
//		radio2Selecionado = false;
//		this.jRadioButton2 = new JRadioButton();
//		this.jRadioButton2.setBounds(960, 50,300,30);
//		this.jRadioButton2.setText("Bloqueio de 2 fases Estrito");
//		this.jRadioButton2.setFont(new Font("Comic Sans MS", 1, 18));
//
//		jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				jRadioButton2ActionPerformed(evt);
//			}
//		});
//		
//
//		label6 = new JLabel("TIPO DE BLOQUEIO");		
//		label6.setBounds(960, 80, 250, 40);
//		label6.setFont(new Font("Comic Sans MS", 1, 15));
//
//
//		this.jRadioButton5 = new JRadioButton();
//		this.jRadioButton5.setBounds(960, 110,300,30);
//		this.jRadioButton5.setText("Lock Multiplo");
//		this.jRadioButton5.setFont(new Font("Comic Sans MS", 1, 18));
//		this.jRadioButton5.setEnabled(false);
//
//		jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				jRadioButton5ActionPerformed(evt);
//			}
//		});

		label7 = new JLabel("TRATAMENTO");		
		label7.setBounds(960, 140, 250, 40);
		label7.setFont(new Font("Comic Sans MS", 1, 15));

		this.jRadioButton6 = new JRadioButton();
		this.jRadioButton6.setBounds(960, 170,300,30);
		this.jRadioButton6.setText("Time Stamping Wait-Die");
		this.jRadioButton6.setFont(new Font("Comic Sans MS", 1, 18));
		this.jRadioButton6.setEnabled(true);

		jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton6ActionPerformed(evt);
			}
		});

		

		this.jButton1 = new JButton("Adicionar Transa��o");
		this.jButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 15));
		this.jButton1.setBounds(960, 200, 250,40);

		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		this.jButton2 = new JButton("Executar Transa��o");
		this.jButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 15));
		this.jButton2.setBounds(960, 250, 250,40);

		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		this.jButton3 = new JButton("SAIR");
		this.jButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 15));
		this.jButton3.setBounds(960, 300, 250,40);

		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}

		});

	}

	// tabela do estado atual do disco
	public void setEstadoAtual(){

		int i = 0;
		if(inicioModelo == true){
			while(i < this.rep.getListaVariaveis().size()){
				System.out.println(this.rep.getListaVariaveis().get(i).getNomeVariavel()+"   "+this.rep.getListaVariaveis().get(i).getValor());
				this.modelo.addRow(new Object[] {this.rep.getListaVariaveis().get(i).getNomeVariavel(), this.rep.getListaVariaveis().get(i).getValor()});
				i++;

			}
			inicioModelo = false;
		}else{
			
			while(i < this.rep.getListaVariaveis().size()){
				System.out.println(this.rep.getListaVariaveis().get(i).getNomeVariavel()+"   "+this.rep.getListaVariaveis().get(i).getValor());
				//this.modelo.insertRow(i,(new Object[] {this.rep.getListaVariaveis().get(i).getNomeVariavel(), this.rep.getListaVariaveis().get(i).getValor()}));
				this.modelo.setValueAt(this.rep.getListaVariaveis().get(i).getNomeVariavel(), i, 0);
				this.modelo.setValueAt(this.rep.getListaVariaveis().get(i).getValor(), i, 1);
				i++;

			}
			

		}
		System.out.println("passou");


		this.EstadoDisco = new JTable(modelo);  
		this.scrollDisco = new JScrollPane(EstadoDisco);
		scrollDisco.setBounds(500, 50, 400, 250);
		scrollDisco.setBackground(Color.white);



	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

		JanelaEscolherTransacao janela = new JanelaEscolherTransacao(this.rep,this);
		janela.SetJanelaEscolherTransacao();
		janela.setComponentes();
		janela.addComponentes();
		janela.setVisible(true);
		this.setVisible(false);



	}

	//// criar erro p qndo n�o houver operacoes criadas nas transacoes da lista
	/// nao teria q separar p fazer com o lock e sem o lock, e com o time stamping ou sem?
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {


			Bloqueio2FasesEstrito bloqueio = new Bloqueio2FasesEstrito(rep.getTransacoes());
			bloqueio.executar(rep, jRadioButton6.isSelected());

			//printando lista de operacoes a serem executadas
			for(int i = 0; i < rep.getTransacoes().size();i++){
				String ConjuntoOperacoes = textArea1.getText();
				for(int j = 0; j < rep.getTransacoes().get(i).getListaOperacoes().size();j++){
					
					if(rep.getTransacoes().get(i).getListaOperacoes().get(j).getNomeOperacao().equals("End")||rep.getTransacoes().get(i).getListaOperacoes().get(j).getNomeOperacao().equals("Begin")||rep.getTransacoes().get(i).getListaOperacoes().get(j).getNomeOperacao().equals("Commit")){
						if(ConjuntoOperacoes.equals("")){	
							ConjuntoOperacoes = (rep.getTransacoes().get(i).getnomeTransacao()+" "+rep.getTransacoes().get(i).getListaOperacoes().get(j).getNomeOperacao());
						}else{

							ConjuntoOperacoes = ConjuntoOperacoes +"\n"+(rep.getTransacoes().get(i).getnomeTransacao()+" "+rep.getTransacoes().get(i).getListaOperacoes().get(j).getNomeOperacao());
						}
						textArea1.setText(ConjuntoOperacoes);
					}else{
						if(ConjuntoOperacoes.equals("")){	
							ConjuntoOperacoes = (rep.getTransacoes().get(i).getnomeTransacao()+" "+rep.getTransacoes().get(i).getListaOperacoes().get(j).getNomeOperacao()+" "+rep.getTransacoes().get(i).getListaOperacoes().get(j).getValorAntigo()+" "+rep.getTransacoes().get(i).getListaOperacoes().get(j).getValorNovo()+" "+rep.getTransacoes().get(i).getListaOperacoes().get(j).getVariavel().getNomeVariavel());
						}else{

							ConjuntoOperacoes = ConjuntoOperacoes +"\n"+ (rep.getTransacoes().get(i).getnomeTransacao()+" "+rep.getTransacoes().get(i).getListaOperacoes().get(j).getNomeOperacao()+" "+rep.getTransacoes().get(i).getListaOperacoes().get(j).getValorAntigo()+" "+rep.getTransacoes().get(i).getListaOperacoes().get(j).getValorNovo()+" "+rep.getTransacoes().get(i).getListaOperacoes().get(j).getVariavel().getNomeVariavel());

						}
						
					}

				}
				textArea1.setText(ConjuntoOperacoes+"\n"+"\n");

			}

			//printando lista de operacoes oficial
			for(int i = 0; i < bloqueio.getListaOperacoesOficial().size();i++){
				String ConjuntoOperacoes = textArea2.getText();
				if(ConjuntoOperacoes.equals("")){
					ConjuntoOperacoes = (bloqueio.getListaOperacoesOficial().get(i));

				}else{

					ConjuntoOperacoes = ConjuntoOperacoes+"\n" + (bloqueio.getListaOperacoesOficial().get(i));
				}

				textArea2.setText(ConjuntoOperacoes);
			}

			//printando lista suja de operacoes
			for(int i = 0; i < bloqueio.getListaOperacoesFinal().size();i++){

				String ConjuntoOperacoes = textArea4.getText();
				if(ConjuntoOperacoes.equals("")){
					ConjuntoOperacoes = (bloqueio.getListaOperacoesFinal().get(i));

				}else{

					ConjuntoOperacoes = ConjuntoOperacoes+"\n"+(bloqueio.getListaOperacoesFinal().get(i));
				}

				textArea4.setText(ConjuntoOperacoes);
			}
			//printando lista bloqueio
			for(int i = 0; i < rep.getListaDeBloqueioMultiploLixo().size();i++){
				String ConjuntoOperacoes = textArea3.getText();
				if(ConjuntoOperacoes.equals("")){
					ConjuntoOperacoes = (rep.getListaDeBloqueioMultiploLixo().get(i).getNomeTransacao()+" "+rep.getListaDeBloqueioMultiploLixo().get(i).getModoBloqueio()+" "+ rep.getListaDeBloqueioMultiploLixo().get(i).getNomeVariavel());
				}else{

					ConjuntoOperacoes = ConjuntoOperacoes +"\n"+ (rep.getListaDeBloqueioMultiploLixo().get(i).getNomeTransacao()+" "+rep.getListaDeBloqueioMultiploLixo().get(i).getModoBloqueio()+" "+ rep.getListaDeBloqueioMultiploLixo().get(i).getNomeVariavel());
				}
				textArea3.setText(ConjuntoOperacoes);
			}

		
		System.out.println(this.rep.getListaVariaveis().get(0).getValor());
		this.setEstadoAtual();
		this.add(this.scrollDisco);

	}

	private void jButton3ActionPerformed(ActionEvent evt) {
		this.dispose();

	}


	private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		jRadioButton2.setSelected(false);
		//jRadioButton3.setSelected(false);
		if(radio1Selecionado == false){
			//jRadioButton4.setEnabled(true);
			jRadioButton5.setEnabled(true);
			jRadioButton6.setEnabled(true);
			//jRadioButton7.setEnabled(true);
			radio1Selecionado = true;
			radio3Selecionado = false;
			radio2Selecionado = false;
		}else{
			//jRadioButton4.setEnabled(false);
			jRadioButton5.setEnabled(false);
			jRadioButton6.setEnabled(false);
			//jRadioButton7.setEnabled(false);
			radio1Selecionado = false;
		}

	}

	private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		//		jRadioButton1.setSelected(false);
		//		jRadioButton3.setSelected(false);

		if(radio2Selecionado == false){
			//jRadioButton4.setEnabled(true);
			jRadioButton5.setEnabled(true);
			jRadioButton6.setEnabled(true);
			//jRadioButton7.setEnabled(true);
			radio2Selecionado = true;
			radio1Selecionado = false;
			radio3Selecionado = false;
		}else{
			//jRadioButton4.setEnabled(false);
			jRadioButton5.setEnabled(false);
			jRadioButton6.setEnabled(false);
			//jRadioButton7.setEnabled(false);
			radio2Selecionado = false;
		}

	}

	private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		//		jRadioButton1.setSelected(false);
		jRadioButton2.setSelected(false);
		if(radio3Selecionado == false){
			//jRadioButton4.setEnabled(true);
			jRadioButton5.setEnabled(true);
			jRadioButton6.setEnabled(true);
			//jRadioButton7.setEnabled(true);
			radio3Selecionado = true;
			radio1Selecionado = false;
			radio2Selecionado = false;
		}else{
			//jRadioButton4.setEnabled(false);
			jRadioButton5.setEnabled(false);
			jRadioButton6.setEnabled(false);
			//jRadioButton7.setEnabled(false);
			radio3Selecionado = false;
		}
	}

	private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		jRadioButton5.setSelected(false);
		//jRadioButton6.setSelected(false);
		//jRadioButton7.setSelected(false);   	

	}

	private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		//jRadioButton4.setSelected(false);
		//jRadioButton6.setSelected(false);
		//jRadioButton7.setSelected(false); 	

	}

	private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		//jRadioButton4.setSelected(false);
		//jRadioButton5.setSelected(false);
		//jRadioButton7.setSelected(false);  	

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
		//this.add(this.label5);
		//this.add(this.label6);
		this.add(this.label7);
		this.add(this.label8);
		this.add(this.scrollDisco);
		this.add(this.jScrollPane1);
		this.add(this.jScrollPane2);
		this.add(this.jScrollPane3);
		this.add(this.jScrollPane4);
		//		this.add(this.jRadioButton1);
		//this.add(this.jRadioButton2);
		//		this.add(this.jRadioButton3);
		//this.add(this.jRadioButton4);
		//this.add(this.jRadioButton5);
		this.add(this.jRadioButton6);
		//this.add(this.jRadioButton7);
		this.add(this.jButton1);
		this.add(this.jButton2);
		this.add(this.jButton3);
	}


	public static void main(String[] args) {
		Repositorio reptemp = new Repositorio();	
		JanelaInicial janela = new JanelaInicial(reptemp);
		janela.criarRepositorio();
		janela.setJanelaInicial();
		janela.setComponentes();
		janela.setEstadoAtual();
		janela.addComponentes();
		janela.setVisible(true);
	}


}
