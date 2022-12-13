package com.example;

//Arvore genérica que aceita todo tipo
//Extends para dizer que os atributos podem ser comparados
public class Arvore<TIPO extends Comparable> {    
    private Elemento<TIPO> raiz;
    
    public Arvore(){
        this.raiz = null;
    }
//------------------------------------------\\Adicionar//---------------------------------------\\
//Por hora todo dado inserido está sendo armazenado na memória e também no database
//Porém quando a operação acaba, a memória se finda e se mantém no banco
    public void adicionar(TIPO valor){
        Elemento<TIPO> novoElemento = new Elemento<TIPO>(valor);
        int aux = intValue(valor);
        ConnectionDatabase conexao = new ConnectionDatabase();

        //Processo de criar uma nova conexão
        if (raiz == null){
            this.raiz = novoElemento;
            conexao.inserirID(1);
        }else{
            Elemento<TIPO> atual = this.raiz;
            while(true){
                if (novoElemento.getValor().compareTo(atual.getValor()) == -1){
                    if (atual.getEsquerda() != null){
                        atual = atual.getEsquerda();
                    }else{
                        atual.setEsquerda(novoElemento);
                        break;
                    }
                }else{ 
                    if (atual.getDireita() != null){
                        atual = atual.getDireita();
                    }else{
                        atual.setDireita(novoElemento);
                        break;
                    }
                }
            }
        }
    }

    private int intValue(TIPO valor) {
        return 0;
    }
    public Elemento<TIPO> getRaiz() {
        return raiz;
    }
    
//---------------------------------------------\\EM ORDEM//------------------------------------\\
    public void emOrdem(Elemento<TIPO> atual){
        if (atual != null){
            emOrdem(atual.getEsquerda());
            System.out.println(atual.getValor());
            emOrdem(atual.getDireita());
        }        
    }

//---------------------------------------------\\PRÉ ORDEM//------------------------------------\\
    public void preOrdem(Elemento<TIPO> atual){
        if (atual != null){
            System.out.println(atual.getValor());
            preOrdem(atual.getEsquerda());            
            preOrdem(atual.getDireita());
        }
    }

//---------------------------------------------\\PÓS ORDEM//------------------------------------\\
    public void posOrdem(Elemento<TIPO> atual){
        if (atual != null){            
            posOrdem(atual.getEsquerda());            
            posOrdem(atual.getDireita());
            System.out.println(atual.getValor());
        }        
    }
}