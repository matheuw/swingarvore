/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aluno;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class ArvoreAvl {

 public static Aluno raiz;
    static {
        raiz = null;
    }
 

	public void inserir(int matricula,String nome, String curso) {
		Aluno n = new Aluno(matricula,nome,curso);
		inserirAVL(ArvoreAvl.raiz, n);
	}

	public void inserirAVL(Aluno aComparar, Aluno aInserir) {

		if (aComparar == null) {
			ArvoreAvl.raiz = aInserir;
		} else {
			if (aComparar.getNome().compareTo(aInserir.getNome()) > 0) {

				if (aComparar.getEsquerda() == null) {
					aComparar.setEsquerda(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);

				} else {
                                        
					inserirAVL(aComparar.getEsquerda(), aInserir);
				}

			} else if (aComparar.getNome().compareTo(aInserir.getNome()) < 0) {

				if (aComparar.getDireita() == null) {
					aComparar.setDireita(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);

				} else {
					inserirAVL(aComparar.getDireita(), aInserir);
				}

			} else {
				                        System.out.println("Matricula já cadastrada");
			}
		}
	}

	public void verificarBalanceamento(Aluno atual) {
		setBalanceamento(atual);
		int balanceamento = atual.getBalanceamento();

		if (balanceamento == -2) {

			if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
				atual = rotacaoDireita(atual);
                                System.out.println("Rotação a direira");

			} else {
				atual = duplaRotacaoEsquerdaDireita(atual);
                                System.out.println("Rotação dupla da esquerda para direita");
			}

		} else if (balanceamento == 2) {

			if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
				atual = rotacaoEsquerda(atual);
                                System.out.println("Rotação a esquerda");

			} else {
				atual = duplaRotacaoDireitaEsquerda(atual);
                                System.out.println("Rotação dupla da direta pra esquerda");
			}
		}

		if (atual.getPai() != null) {
			verificarBalanceamento(atual.getPai());
		} else {
			ArvoreAvl.raiz = atual;
		}
	}

	public void remover(String k) {
		removerAVL(ArvoreAvl.raiz, k);
	}

	public void removerAVL(Aluno atual, String nome) {
		if (atual == null) {
			return;
		} else {

			if (atual.getNome().compareTo(nome) > 0) {
				removerAVL(atual.getEsquerda(), nome);

			} else if (atual.getNome().compareTo(nome) < 0) {
				removerAVL(atual.getDireita(), nome);

			} else if (atual.getNome().equals(nome)) {
				removerNoEncontrado(atual);
			}
		}
	}

	public void removerNoEncontrado(Aluno aRemover) {
		Aluno r;

		if (aRemover.getEsquerda() == null || aRemover.getDireita() == null ) {

			if (aRemover.getPai() == null) { 
                                if(aRemover.getDireita() != null){
                                    ArvoreAvl.raiz = aRemover.getDireita();
                                    ArvoreAvl.raiz.setPai(null);
                                    verificarBalanceamento(ArvoreAvl.raiz);
                                }else if(aRemover.getEsquerda() !=null){
                                    ArvoreAvl.raiz = aRemover.getEsquerda();
                                    ArvoreAvl.raiz.setPai(null);
                                    verificarBalanceamento(ArvoreAvl.raiz);
                                }
				
				return;
			}
			r = aRemover;

		} else {
			r = sucessor(aRemover);
			aRemover.setMatricula(r.getMatricula());
		}

		Aluno p;
		if (r.getEsquerda() != null) {
			p = r.getEsquerda();
		} else {
			p = r.getDireita();
		}

		if (p != null) {
			p.setPai(r.getPai());
		}

		if (r.getPai() == null) {
			ArvoreAvl.raiz = p;
		} else {
			if (r == r.getPai().getEsquerda()) {
				r.getPai().setEsquerda(p);
			} else {
				r.getPai().setDireita(p);
			}
			verificarBalanceamento(r.getPai());
		}
		r = null;
	}

	public Aluno rotacaoEsquerda(Aluno inicial) {

		Aluno direita = inicial.getDireita();
		direita.setPai(inicial.getPai());

		inicial.setDireita(direita.getEsquerda());

		if (inicial.getDireita() != null) {
			inicial.getDireita().setPai(inicial);
		}

		direita.setEsquerda(inicial);
		inicial.setPai(direita);

		if (direita.getPai() != null) {

			if (direita.getPai().getDireita() == inicial) {
				direita.getPai().setDireita(direita);
			
			} else if (direita.getPai().getEsquerda() == inicial) {
				direita.getPai().setEsquerda(direita);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(direita);

		return direita;
	}

	public Aluno rotacaoDireita(Aluno inicial) {

		Aluno esquerda = inicial.getEsquerda();
		esquerda.setPai(inicial.getPai());

		inicial.setEsquerda(esquerda.getDireita());

		if (inicial.getEsquerda() != null) {
			inicial.getEsquerda().setPai(inicial);
		}

		esquerda.setDireita(inicial);
		inicial.setPai(esquerda);

		if (esquerda.getPai() != null) {

			if (esquerda.getPai().getDireita() == inicial) {
				esquerda.getPai().setDireita(esquerda);
			
			} else if (esquerda.getPai().getEsquerda() == inicial) {
				esquerda.getPai().setEsquerda(esquerda);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(esquerda);

		return esquerda;
	}

	public Aluno duplaRotacaoEsquerdaDireita(Aluno inicial) {
		inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}

	public Aluno duplaRotacaoDireitaEsquerda(Aluno inicial) {
		inicial.setDireita(rotacaoDireita(inicial.getDireita()));
		return rotacaoEsquerda(inicial);
	}

	public Aluno sucessor(Aluno q) {
		if (q.getDireita() != null) {
			Aluno r = q.getDireita();
			while (r.getEsquerda() != null) {
				r = r.getEsquerda();
			}
			return r;
		} else {
			Aluno p = q.getPai();
			while (p != null && q == p.getDireita()) {
				q = p;
				p = q.getPai();
			}
			return p;
		}
	}

	private int altura(Aluno atual) {
		if (atual == null) {
			return -1;
		}

		if (atual.getEsquerda() == null && atual.getDireita() == null) {
			return 0;
		
		} else if (atual.getEsquerda() == null) {
			return 1 + altura(atual.getDireita());
		
		} else if (atual.getDireita() == null) {
			return 1 + altura(atual.getEsquerda());
		
		} else {
			return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
		}
	}

	private void setBalanceamento(Aluno no) {
		no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
	}
	final public ArrayList<Aluno> inorder() {
		ArrayList<Aluno> ret = new ArrayList<>();
                if(raiz != null && raiz.getMatricula() !=0){
                    inorder(raiz, ret);
                }	
		return ret;
	}

	final protected void inorder(Aluno no, ArrayList<Aluno> lista) {
		if (no == null) {
			return;
		}
		inorder(no.getEsquerda(), lista);
		lista.add(no);
		inorder(no.getDireita(), lista);
	}
        
        
        public Aluno busca(String elemento){
            return busca(elemento, ArvoreAvl.raiz);
            
        }
        
        
        public Aluno busca(String elemento, Aluno node){
            if(elemento == null || node == null){
                return null;
            }
            
            if(node == null && node.getMatricula() == 0 || node.getNome().equals(elemento)){
                return node;
            }else if(node.getNome().compareTo(elemento) > 0){
                return busca(elemento, node.getEsquerda());
            }else{
                return busca(elemento, node.getDireita());
            }
            
        }
        
        public void Alterar(Aluno aAlterar,Aluno anterior){
            Alterar(ArvoreAvl.raiz, aAlterar,anterior);
            
        }
        
        public void Alterar(Aluno aComparar, Aluno aAlterar,Aluno anterior){
            if(aComparar == null || aAlterar == null){
                JOptionPane.showMessageDialog(null, "Não pode existir um campo preenchido vazio");
            }
            
            if(aComparar.getNome().equals(anterior.getNome())){
                   aComparar.Alterar(aAlterar.getMatricula(), aAlterar.getNome(), aAlterar.getCurso());
            }else if(aComparar.getNome().compareTo(anterior.getNome()) > 0){
                 Alterar(aComparar.getEsquerda(),aAlterar,anterior);
            }else{
                Alterar(aComparar.getDireita(),aAlterar,anterior);
            }
        }

    @Override
    public String toString() {
        String arvore = Arrays.toString(inorder().toArray());  
        return arvore;
    }
    
        
}