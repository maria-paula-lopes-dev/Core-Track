package DAOs;

import Classes.Relatorios;
import Conecxao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author maria
 */
/**
 * EXPECIFICAÇÕES DESSA CLASSE:
 *
 * INSERIR >> SALVA UM RELATÓRIO GERADO PELO CORETRACK
 *
 * BUSCAR POR ID >> CARREGA O RELATÓRIO
 *
 * LISTAR >> CARREGA OS RELATÓRIOS SALVOS DAQUELE USUÁRIO
 *
 * ATUALIZAR >> ATUALIZAR OS DADOS -> CONFIGURAÇÕES
 *
 */
public class RelatoriosDAO {

    // --------------------------------------------------------
    // SALVA UM NOVO USUÁRIO NO BANCO
    // --------------------------------------------------------
    public boolean inserir(Relatorios r) {
        String sql = "INSERT INTO relatorios (id_usuario, resumo, sugestoes) VALUES (?, ?, ?)";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.criarConexao();
            if (con == null) {
                System.out.println("Erro: conexão nula!");
                return false;
            }

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, r.getIdUsuario());
            stmt.setString(2, r.getResumo());
            stmt.setString(3, r.getSugestoes());

            stmt.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir relatório: " + e.getMessage());
            return false;
        } finally {
            Conexao.close(con, stmt, null);
        }
    }

    // --------------------------------------------------------
    // CARREGA UM USUÁRIO DO BANCO
    // --------------------------------------------------------
    public Relatorios buscarPorId(int id) {
        String sql = "SELECT * FROM relatorios WHERE id_relatorio = ?";

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = Conexao.criarConexao();
            if (con == null) {
                System.out.println("Erro: conexão nula!");
                return null;
            }

            stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {

                Relatorios r = new Relatorios(
                        rs.getInt("id_relatorio"),
                        rs.getInt("id_usuario"),
                        rs.getString("resumo"),
                        rs.getString("sugestoes"),
                        rs.getTimestamp("data_geracao").toLocalDateTime()
                );

                return r;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar relatório: " + e.getMessage());
        } finally {
            Conexao.close(con, stmt, rs);
        }
        return null;
    }

    // --------------------------------------------------------
    // ATUALIZAR
    // --------------------------------------------------------
    public boolean atualizar(Relatorios r) {
        String sql = "UPDATE relatorios SET resumo=?, sugestoes=? WHERE id_relatorio=?";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.criarConexao();
            if (con == null) {
                System.out.println("Erro: conexão nula!");
                return false;
            }

            stmt = con.prepareStatement(sql);

            stmt.setString(1, r.getResumo());
            stmt.setString(2, r.getSugestoes());
            stmt.setInt(3, r.getIdRelatorio());

            stmt.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao atualizar relatório: " + e.getMessage());
            return false;
        } finally {
            Conexao.close(con, stmt, null);
        }
    }

    // --------------------------------------------------------
    // DELETAR
    // --------------------------------------------------------
    public boolean deletar(int idRelatorio) {
        String sql = "DELETE FROM relatorios WHERE id_relatorio=?";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.criarConexao();
            if (con == null) {
                System.out.println("Erro: conexão nula!");
                return false;
            }

            stmt = con.prepareStatement(sql);

            stmt.setInt(1, idRelatorio);
            stmt.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao deletar relatório: " + e.getMessage());
            return false;
        } finally {
            Conexao.close(con, stmt, null);
        }
    }
}
