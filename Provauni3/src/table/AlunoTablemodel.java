/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import aluno.Aluno;
import aluno.ArvoreAvl;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Matheus Paulino
 */
public class AlunoTablemodel extends AbstractTableModel{
    ArvoreAvl arvore = new ArvoreAvl();
    private ArrayList<Aluno> dados = arvore.inorder();
    private String[] colunas = {"Matrícula:","Nome:","Curso:"};

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    
    
    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return dados.get(linha).getMatricula();
            case 1:
                return dados.get(linha).getNome();
            case 2:
                return dados.get(linha).getCurso();
            
        }
        return null;
    }

//    public void setValueAt(Aluno valor, int linha, int coluna) {
//        switch(coluna){
//            case 0:
//                  
//                 dados.get(linha).setMatricula();
//            case 1:
//                 dados.get(linha).getNome();
//            case 2:
//                 dados.get(linha).getCurso();
//            
//        }
//    }
    
    
    
    
    public void removeRow(int linha, String nome){
        this.arvore.remover(nome);
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
    
    
}
