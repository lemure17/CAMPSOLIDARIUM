package repository;

import model.Ong;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OngRepository {

    private static final String ARQUIVO = "ongs.dat";
    private List<Ong> ongs;

    public OngRepository() {
        this.ongs = carregarDoDisco();
    }

    // ── CREATE ──────────────────────────────────────────
    public void salvar(Ong ong) {
        // Gera código automático: maior código existente + 1
        int novoCodigo = ongs.stream()
                .mapToInt(Ong::getCodigo)
                .max()
                .orElse(0) + 1;
        ong.setCodigo(novoCodigo);
        ongs.add(ong);
        salvarEmDisco();
    }

    // ── READ ─────────────────────────────────────────────
    public List<Ong> listarTodos() {
        return new ArrayList<>(ongs); // retorna cópia para não expor a lista interna
    }

    public Ong buscarPorCodigo(int codigo) {
        return ongs.stream()
                .filter(o -> o.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    // ── UPDATE ───────────────────────────────────────────
    public boolean atualizar(Ong ongAtualizada) {
        for (int i = 0; i < ongs.size(); i++) {
            if (ongs.get(i).getCodigo() == ongAtualizada.getCodigo()) {
                ongs.set(i, ongAtualizada);
                salvarEmDisco();
                return true;
            }
        }
        return false;
    }

    // ── DELETE ───────────────────────────────────────────
    public boolean deletar(int codigo) {
        boolean removido = ongs.removeIf(o -> o.getCodigo() == codigo);
        if (removido) salvarEmDisco();
        return removido;
    }

    // ── SERIALIZAÇÃO ─────────────────────────────────────
    private void salvarEmDisco() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARQUIVO))) {
            oos.writeObject(ongs);
        } catch (IOException e) {
            System.err.println("Erro ao salvar ongs.dat: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<Ong> carregarDoDisco() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(arquivo))) {
            return (List<Ong>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar ongs.dat: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}