package com.mycompany.pedrapapeltesoura;
import java.util.Scanner;
import java.util.Random;

public class PedraPapelTesoura {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("Escolha o tipo de jogo: (1) Humano x Maquina (2) Maquina x Maquina (3) Humano x Humano");
        int tipoDeJogadores = Integer.parseInt(scanner.nextLine());
        
        System.out.println("Escolha o jogo: (1)Jogo Único | (2)Melhor de 3");
        int tipoDeJogo = Integer.parseInt(scanner.nextLine());
        
        int nQtdVitoriasNecessarias = 1;
        
        
        //Caso o tipo do jogo seja melhor de 3, então a quantidade de vitórias é 3
        if (tipoDeJogo == 2){
            nQtdVitoriasNecessarias = 3;
        }
        
        if(tipoDeJogadores == 1) {
            System.out.println("Qual seu nome, jogador?");
            String nomeJogHumano = scanner.nextLine();
        
            Jogador jogadorHumano = new Jogador(nomeJogHumano);
            Jogador jogadorMaquina = new Jogador("Maquina");
            
            escolheEJoga(jogadorHumano, jogadorMaquina, nQtdVitoriasNecessarias, tipoDeJogadores);
        } else if(tipoDeJogadores == 2) {
            Jogador jogadorMaquina1 = new Jogador("Maquina 1");
            Jogador jogadorMaquina2 = new Jogador("Maquina 2");
            
            escolheEJoga(jogadorMaquina1, jogadorMaquina2, nQtdVitoriasNecessarias, tipoDeJogadores);
        } else {
            System.out.println("Qual seu nome, jogador 1?");
            String nomeJogHumano1 = scanner.nextLine();
            Jogador jogadorHumano1 = new Jogador(nomeJogHumano1);
            
            System.out.println("Qual seu nome, jogador 2?");
            String nomeJogHumano2 = scanner.nextLine();
            Jogador jogadorHumano2 = new Jogador(nomeJogHumano2);
            
            escolheEJoga(jogadorHumano1, jogadorHumano2, nQtdVitoriasNecessarias, tipoDeJogadores);
        }
        
        
    }
    
    private static void escolheEJoga(Jogador jogador1, Jogador jogador2, int nQtdVitoriasNecessarias, int tipoDeJogadores) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        while(jogador1.getQuantVitorias() < nQtdVitoriasNecessarias && jogador2.getQuantVitorias() < nQtdVitoriasNecessarias){
            if(tipoDeJogadores == 1) {
                System.out.println("(1)Papel, (2)pedra ou (3)tesoura?");
                int selecaoEscolha = Integer.parseInt(scanner.nextLine());

                jogador1.setEscolha(newCoisa(selecaoEscolha));      
                jogador2.setEscolha(newCoisa(random.nextInt(3) + 1));

            } else if(tipoDeJogadores == 2) {
                jogador1.setEscolha(newCoisa(random.nextInt(3) + 1));
                jogador2.setEscolha(newCoisa(random.nextInt(3) + 1));
               
            } else {
                System.out.println("(1)Papel, (2)pedra ou (3)tesoura, jogador 1?");
                int selecaoEscolhaJog1 = Integer.parseInt(scanner.nextLine());
                jogador1.setEscolha(newCoisa(selecaoEscolhaJog1));
                
                System.out.println("(1)Papel, (2)pedra ou (3)tesoura, jogador 2?");
                int selecaoEscolhaJog2 = Integer.parseInt(scanner.nextLine());
                jogador2.setEscolha(newCoisa(selecaoEscolhaJog2));
            }
            
            jogar(jogador1, jogador2);
          
        }
        
        if (jogador1.getQuantVitorias() == nQtdVitoriasNecessarias){
            System.out.println(jogador1.getNome() + " ganhou o jogo ");
        }else{
            System.out.println(jogador2.getNome() + " ganhou o jogo ");
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
    
    private static void jogar(Jogador jogador1, Jogador jogador2) {
        Class<?> escolhaHumano = jogador1.getEscolha().getClass();
        Class<?> escolhaMaquina = jogador2.getEscolha().getClass();
        
        System.out.println(jogador1.getNome() + " escolheu: " + escolhaHumano.getSimpleName());
        System.out.println(jogador2.getNome() + " escolheu: " + escolhaMaquina.getSimpleName()); 
        
        //Caso escolha seja igual então continua o jogo 
        if (escolhaHumano == escolhaMaquina){
            System.out.println("Round Empatado!  " + jogador1.getNome()+ ": " + jogador1.getQuantVitorias() + " x " + jogador2.getNome() + ": " + jogador2.getQuantVitorias()); 
            return;
        }
        
        //Verifica quem ganhou a jogada
        if (escolhaHumano == Papel.class && escolhaMaquina == Pedra.class || 
            escolhaHumano == Pedra.class && escolhaMaquina == Tesoura.class ||
            escolhaHumano == Tesoura.class && escolhaMaquina == Papel.class){
          jogador1.ganhou();
          System.out.println(jogador1.getNome() + " venceu o Round! " + jogador1.getNome()+ ": " + jogador1.getQuantVitorias() + " x " + jogador2.getNome() + ": " + jogador2.getQuantVitorias()); 
        }else{
            jogador2.ganhou();
            System.out.println(jogador2.getNome() + " venceu o Round! " + jogador1.getNome()+ ": " + jogador1.getQuantVitorias() + " x " + jogador2.getNome() + ": " + jogador2.getQuantVitorias()); 
        }   
   }   
}
    
