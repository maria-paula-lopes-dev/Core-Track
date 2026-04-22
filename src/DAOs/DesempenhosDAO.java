package DAOs;

import Classes.Desempenhos;
import Conecxao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maria
 */
/**
 * EXPECIFICAÇÕES DESSA CLASSE: INSERIR REGISTRO >> POR CONTA DO MONITORAMENTO
 * DE CADA COMPONENTE -> TODO VEZ QUE LER OS DADOS DO OSHI
 *
 * BUSCAR POR ID >> PEGA REGISTROS ESPECÍFICOS DO BANCO
 *
 * LISTAR POR USUÁRIOS >> RETORNA OS REGISTROS DE DESEMPENHO DE UM USER
 * ESPECÍFICO -> PRO GRÁFICO DE LINHA E BARRAS -> HISTÓRICO DE DESEMPENHO
 * ->RELATÓRIOS
 *
 * LISTAR ÚLTIMOS REGISTROS >> PEGA APENAS OS ÚLTIMOS REGISTROS
 *
 * LISTAR CPU, RAM... >> USADO PRA PREENCHER O GRÁFICO
 *
 *
 */
public class DesempenhosDAO {

    // ===== INSERIR =====
    public boolean inserir(Desempenhos d) {
        String sql = "INSERT INTO desempenho (id_usuario, cpu_uso, ram_uso, armazenamento_uso, tempo_uso, temperatura) "
                + "VALUES (?,?,?,?,?,?)";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.criarConexao();
            if (con == null) {
                System.out.println("Erro: conexão nula!");
                return false;
            }

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, d.getIdUsuario());
            stmt.setDouble(2, d.getUsoCPU());
            stmt.setDouble(3, d.getUsoRAM());
            stmt.setDouble(4, d.getUsoDisco());
            stmt.setDouble(5, d.getTempoDeUso());
            stmt.setDouble(6, d.getTemperatura());

            stmt.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir desempenho: " + e.getMessage());
            return false;
        } finally {
            Conexao.close(con, stmt, null);
        }
    }

    // ===== BUSCAR POR ID =====
    public Desempenhos buscarPorId(int id) {

        String sql = "SELECT * FROM desempenho WHERE id_desempenho = ?";
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
                return new Desempenhos(
                        rs.getInt("id_desempenho"),
                        rs.getInt("id_usuario"),
                        rs.getDouble("cpu_uso"),
                        rs.getDouble("ram_uso"),
                        rs.getDouble("armazenamento_uso"),
                        rs.getDouble("tempo_uso"),
                        rs.getDouble("temperatura"),
                        rs.getTimestamp("data_registro").toLocalDateTime()
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar desempenho: " + e.getMessage());
        } finally {
            Conexao.close(con, stmt, rs);
        }
        return null;
    }

    // ===== LISTAR TODOS POR USUÁRIO =====
    public List<Desempenhos> listarPorUsuario(int idUsuario) {

        List<Desempenhos> lista = new ArrayList<>();
        String sql = "SELECT * FROM desempenho WHERE id_usuario = ? ORDER BY data_registro ASC";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = Conexao.criarConexao();
            if (con == null) {
                System.out.println("Erro: conexão nula!");
                return lista;
            }

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Desempenhos d = new Desempenhos(
                        rs.getInt("id_desempenho"),
                        rs.getInt("id_usuario"),
                        rs.getDouble("cpu_uso"),
                        rs.getDouble("ram_uso"),
                        rs.getDouble("armazenamento_uso"),
                        rs.getDouble("tempo_uso"),
                        rs.getDouble("temperatura"),
                        rs.getTimestamp("data_registro").toLocalDateTime()
                );

                lista.add(d);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao listar desempenho: " + e.getMessage());
        } finally {
            Conexao.close(con, stmt, rs);
        }

        return lista;
    }

    // ===== LISTAR ÚLTIMOS N REGISTROS =====
    public List<Desempenhos> listarUltimos(int idUsuario, int limite) {

        List<Desempenhos> lista = new ArrayList<>();
        String sql = "SELECT * FROM desempenho WHERE id_usuario = ? ORDER BY data_registro DESC LIMIT ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = Conexao.criarConexao();
            if (con == null) {
                System.out.println("Erro: conexão nula!");
                return lista;
            }

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, limite);

            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Desempenhos(
                        rs.getInt("id_desempenho"),
                        rs.getInt("id_usuario"),
                        rs.getDouble("cpu_uso"),
                        rs.getDouble("ram_uso"),
                        rs.getDouble("armazenamento_uso"),
                        rs.getDouble("tempo_uso"),
                        rs.getDouble("temperatura"),
                        rs.getTimestamp("data_registro").toLocalDateTime()
                ));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao listar últimos registros: " + e.getMessage());
        } finally {
            Conexao.close(con, stmt, rs);
        }

        return lista;
    }

    // ===== LISTA SOMENTE CPU (para gráficos) =====
    public List<Double> listarCPU(int idUsuario) {

        List<Double> lista = new ArrayList<>();
        String sql = "SELECT cpu_uso FROM desempenho WHERE id_usuario = ? ORDER BY data_registro ASC";

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = Conexao.criarConexao();
            if (con == null) {
                System.out.println("Erro: conexão nula!");
                return lista;
            }

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(rs.getDouble("cpu_uso"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao listar CPU: " + e.getMessage());
        } finally {
            Conexao.close(con, stmt, rs);
        }

        return lista;
    }

    // ===== DELETAR REGISTRO =====
    public boolean deletar(int idDesempenho) {

        String sql = "DELETE FROM desempenho WHERE id_desempenho = ?";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.criarConexao();
            if (con == null) {
                System.out.println("Erro: conexão nula!");
                return false;
            }

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idDesempenho);
            stmt.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
            return false;
        } finally {
            Conexao.close(con, stmt, null);
        }
    }

    // ===== ATUALIZAR (caso precise) =====
    public boolean atualizar(Desempenhos d) {

        String sql = "UPDATE desempenho SET cpu_uso=?, ram_uso=?, armazenamento_uso=?, tempo_uso=?, temperatura=? "
                + "WHERE id_desempenho=?";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.criarConexao();
            if (con == null) {
                System.out.println("Erro: conexão nula!");
                return false;
            }

            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, d.getUsoCPU());
            stmt.setDouble(2, d.getUsoRAM());
            stmt.setDouble(3, d.getUsoDisco());
            stmt.setDouble(4, d.getTempoDeUso());
            stmt.setDouble(5, d.getTemperatura());
            stmt.setInt(6, d.getIdDesempenho());

            stmt.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
            return false;
        } finally {
            Conexao.close(con, stmt, null);
        }
    }
}
