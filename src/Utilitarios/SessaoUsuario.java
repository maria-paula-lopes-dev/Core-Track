/**
 * EXPLICAÇÃO DA CLASSE: 
 * >>======================================
 * Classe que guarda o ID do usuário que já entrou com os dados no app
 * A tela Dados.fxml vai aparecer apenas 1x
 * O Id será salvo em um arquivo
 * Na próxima vez que app abrir, ele consulta esse arquivo
 * Verifica usuário
 * >>======================================
 */
package Utilitarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author maria
 */
public class SessaoUsuario {
    private static final String CAMINHO = "config_usuario.txt";
    
    // Salva o ID no arquivo
    public static void salvarId(int id) {
        // Classe File >> Permite escrever arquivos txt
        // O try() fecha automaticamente depois de usar
        try(FileWriter fw = new FileWriter(CAMINHO)){
            // Escreve dentro do arquivo
            fw.write(String.valueOf(id));   // agora salva só o ID
            
        }catch(IOException e){
            // Caso dê erro
            System.out.println("Erro ao salvar id: " + e.getMessage());
        }
        
    }
    
    // Método que carrega (LER) o ID do arquivo
    // Se não existir, retorna -1
    public static int carregarId(){
        // Cria um objeto File apontando para o arquivo
        File f = new File(CAMINHO);
        
        // Se o arquivo não existir então é a 1°x 
        if(!f.exists()){
            return -1;
        }
        
        // Tenta ler o conteúdo do arquivo
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            
            String linha = br.readLine();
            // Lê a 1° linha existente e começa com "id = "
            if(linha != null && !linha.isEmpty()){
                coretrackapp.Globais.setId_usuario(Integer.parseInt(linha.trim()));
                return Integer.parseInt(linha.trim());
            }
            
        }catch (IOException | NumberFormatException e) {
            // Captura erros de leitura ou conversão
            System.out.println("Erro ao ler id: " + e.getMessage());
        }
        
        return -1;
    }
    
    // Método pra apagar o arquivo
    public static void apagarId() {

        // Representa o arquivo
        File f = new File(CAMINHO);

        // Se existir, apaga
        if (f.exists()) {
            f.delete();
        }
    }
}
