package Conecxao;

import java.sql.*;

public class Conexao {

    // --------------------------------------------------------------------
    // MÉTODO RESPONSÁVEL POR CRIAR UMA CONEXÃO COM O MYSQL
    // --------------------------------------------------------------------
    public static Connection criarConexao() throws ClassNotFoundException {

        // URL de conexão com o banco (local, porta padrão 3306, banco coretrack)
        String url = "jdbc:mysql://localhost:3306/coretrack";

        // Usuário e senha do MySQL
        String user = "root";
        String password = "";

        try {
            // -----------------------------------------------
            // 1) Carrega o driver JDBC do MySQL na memória
            //    Sem isso o Java não sabe como conversar com o MySQL
            // -----------------------------------------------
            Class.forName("com.mysql.cj.jdbc.Driver");

            // -----------------------------------------------
            // 2) Cria e retorna a conexão com o banco de dados
            // -----------------------------------------------
            Connection conexao = DriverManager.getConnection(url, user, password);
            return conexao;

        } catch (ClassNotFoundException e) {
            // O driver JDBC não foi encontrado (provavelmente o .jar não está no projeto)
            System.out.println("Driver JDBC não encontrado: " + e.getMessage());
            Utilitarios.Avisos.msgInformacao("Driver JDBC não encontrado: " + e.getMessage(), 3);

        } catch (SQLException e) {
            // Erro ao tentar conectar ao banco (porta ocupada, banco offline, senha errada, etc.)
            System.out.println("Erro na conexão com banco de dados: " + e.getMessage());
            Utilitarios.Avisos.msgInformacao("Erro na conexão com banco de dados: " + e.getMessage(), 3);
        }

        // Caso algum erro aconteça, retorna null
        return null;
    }

    // --------------------------------------------------------------------
    // FECHA APENAS A CONEXÃO
    // --------------------------------------------------------------------
    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();  // fecha a conexão fisicamente
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --------------------------------------------------------------------
    // FECHA A CONEXÃO + STATEMENT
    // --------------------------------------------------------------------
    public static void close(Connection connection, Statement stmt) {

        // Fecha só a conexão
        close(connection);

        try {
            if (stmt != null) {
                stmt.close(); // fecha o comando SQL
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --------------------------------------------------------------------
    // FECHA A CONEXÃO + STATEMENT + RESULTSET
    // --------------------------------------------------------------------
    public static void close(Connection connection, Statement stmt, ResultSet rs) {

        // Fecha conexão e statement (chama o método anterior)
        close(connection, stmt);

        try {
            if (rs != null) {
                rs.close(); // fecha o resultado de uma consulta SELECT
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
