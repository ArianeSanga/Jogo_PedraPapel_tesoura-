package com.mycompany.pedrapapeltesoura;
import java.util.Scanner;
import java.util.Random;

public class PedraPapelTesoura {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("Qual seu nome, jogador?");
        String nomeJogHumano = scanner.nextLine();
        
        Jogador jogadorHumano = new Jogador(nomeJogHumano);
        Jogador jogadorMaquina = new Jogador("Maquina");
        
        System.out.println("Escolha o jogo: (1)Jogo Único | (2)Melhor de 3");
        int tipoDeJogo = Integer.parseInt(scanner.nextLine());
        
        int nQtdVitoriasNecessarias = 1;
        
        //Caso o tipo do jogo seja melhor de 3, então a quantidade de vitórias é 3
        if (tipoDeJogo == 2){
            nQtdVitoriasNecessarias = 3;
        }
        
        while(jogadorHumano.getQuantVitorias() < nQtdVitoriasNecessarias && jogadorMaquina.getQuantVitorias() < nQtdVitoriasNecessarias){
            System.out.println("(1)Papel, (2)pedra ou (3)tesoura?");
            int selecaoEscolha = Integer.parseInt(scanner.nextLine());

            jogadorHumano.setEscolha(newCoisa(selecaoEscolha));      
            jogadorMaquina.setEscolha(newCoisa(random.nextInt(3) + 1));

            jogar(jogadorHumano, jogadorMaquina);
        }
        
        if (jogadorHumano.getQuantVitorias() == nQtdVitoriasNecessarias){
            System.out.println("Você ganhou o jogo " + jogadorHumano.getNome() + ", parabens!");
        }else{
            System.out.println("Você perdeu o jogo " + jogadorHumano.getNome() + ", tente novamente!");
        }
    }
    
    private static Coisa newCoisa(int escolha) {
        if(escolha == 1) {
            return new Papel();
        } else if(escolha == 2) {
            return new Pedra();
        }
        return new Tesoura();
    }
    
    private static void jogar(Jogador pHumano, Jogador pMaquina) {
        Class<?> escolhaHumano = pHumano.getEscolha().getClass();
        Class<?> escolhaMaquina = pMaquina.getEscolha().getClass();
        
        System.out.println("Você escolheu: " + escolhaHumano.getSimpleName());
        System.out.println("A maquina escolheu: " + escolhaMaquina.getSimpleName()); 
        
        //Caso escolha seja igual então continua o jogo 
        if (escolhaHumano == escolhaMaquina){
            System.out.println("Round Empatado! Você: " + pHumano.getQuantVitorias() + " x Maquina: " + pMaquina.getQuantVitorias()); 
            return;
        }
        
        //Verifica quem ganhou a jogada
        if (escolhaHumano == Papel.class && escolhaMaquina == Pedra.class || 
            escolhaHumano == Pedra.class && escolhaMaquina == Tesoura.class ||
            escolhaHumano == Tesoura.class && escolhaMaquina == Papel.class){
          pHumano.ganhou();
          System.out.println("Venceu o Round! Você: " + pHumano.getQuantVitorias() + " x Maquina: " + pMaquina.getQuantVitorias()); 
        }else{
            pMaquina.ganhou();
            System.out.println("Perdeu o Round! Você: " + pHumano.getQuantVitorias() + " x Maquina: " + pMaquina.getQuantVitorias()); 
        }   
   }   
}
    
