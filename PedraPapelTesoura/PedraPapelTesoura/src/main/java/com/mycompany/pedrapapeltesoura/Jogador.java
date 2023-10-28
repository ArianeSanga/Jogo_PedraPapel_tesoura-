package com.mycompany.pedrapapeltesoura;

public class Jogador {
    private String nome;
    private Coisa escolha;
    private int quantVitorias;

    public Jogador(String nome){
        this.nome = nome;
        this.quantVitorias = 0;
    }

    public void setEscolha(Coisa escolha){
        this.escolha = escolha;
    }

    public String getNome(){
        return this.nome;
    }

    public Coisa getEscolha(){
        return this.escolha;
    }
    
    public void ganhou() {
        this.quantVitorias++;
    }
    
    public int getQuantVitorias() {
        return this.quantVitorias;
    }
}
