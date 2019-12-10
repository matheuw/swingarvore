/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aluno;

import java.util.ArrayList;

public class Aluno {
  
	private Aluno esquerda;
	private Aluno direita;
	private Aluno pai;
	private int matricula;
	private int balanceamento;
        private String nome;
        private String curso; 

	public Aluno(int matricula,String nome, String curso) {
		setEsquerda(setDireita(setPai(null)));
		setBalanceamento(0);
                setNome(nome);
                setMatricula(matricula);
                setCurso(curso);
	}

    Aluno() {
    }

	public String toString() {
		return Integer.toString(getMatricula());
	}

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getCurso() {
            return curso;
        }

        public void setCurso(String curso) {
            this.curso = curso;
        }
        
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public int getBalanceamento() {
		return balanceamento;
	}

	public void setBalanceamento(int balanceamento) {
		this.balanceamento = balanceamento;
	}

	public Aluno getPai() {
		return pai;
	}

	public Aluno setPai(Aluno pai) {
		this.pai = pai;
		return pai;
	}

	public Aluno getDireita() {
		return direita;
	}

	public Aluno setDireita(Aluno direita) {
		this.direita = direita;
		return direita;
	}

	public Aluno getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(Aluno esquerda) {
		this.esquerda = esquerda;
	}
        
        public ArrayList<Aluno> toArrays(){
            ArvoreAvl arvore = new ArvoreAvl();
                return arvore.inorder();
            
        }
        
        public void Alterar(int matricula,String nome,String curso){
                setNome(nome);
                setMatricula(matricula);
                setCurso(curso);
        }
        
}