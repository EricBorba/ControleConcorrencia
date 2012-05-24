package GUI;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class SimpleTableModel extends AbstractTableModel{
	 
		  
	    private ArrayList linhas = null;  
	    private String [] colunas = null;  
	    public String[] getColunas() {return colunas;}  
	    public ArrayList getLinhas() {return linhas;}  
	    public void setColunas(String[] strings) {colunas = strings;}  
	    public void setLinhas(ArrayList list) {linhas = list;}
	    /** 
	     * Retorna o numero de colunas no modelo 
	     * @see javax.swing.table.TableModel#getColumnCount() 
	     */  
	    public int getColumnCount() {return getColunas().length;}  
	      
	    /** 
	     * Retorna o numero de linhas existentes no modelo 
	     * @see javax.swing.table.TableModel#getRowCount() 
	     */  
	    public int getRowCount() {return getLinhas().size();}  
	      
	    /** 
	     * Obtem o valor na linha e coluna 
	     * @see javax.swing.table.TableModel#getValueAt(int, int) 
	     */  
	    public Object getValueAt(int rowIndex, int columnIndex) {  
	        // Obtem a linha, que é uma String []  
	        String [] linha = (String [])getLinhas().get(rowIndex);  
	        // Retorna o objeto que esta na coluna  
	        return linha[columnIndex];  
	    }  
	    
	    
	    public SimpleTableModel(ArrayList dados, String[] colunas){  
	        setLinhas(dados);  
	        setColunas(colunas);  
	    }  
	    
	  	  
	
}
