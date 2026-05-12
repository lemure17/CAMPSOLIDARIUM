package repository;

import model.Insumo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InsumoRepository {

    private static final String ARQUIVO = "insumos.dat";
    private List<Insumo> insumos;

    public InsumoRepository() {
        this.insumos = carregarDoDisco();
    }

    public void salvar(Insumo insumo) {
        int novoCodigo = insumos.stream()
                .mapToInt(Insumo::getCodigo)
                .max()
                .orElse(0) + 1;
        insumo.setCodigo(novoCodigo);
        insumos.add(insumo);
        salvarEmDisco();
    }

    public List<Insumo> listarTodos() {
        return new ArrayList<>(insumos);
    }

    public Insumo buscarPorCodigo(int codigo) {
        return insumos.stream()
                .filter(i -> i.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    public boolean atualizar(Insumo insumoAtualizado) {
        for (int i = 0; i < insumos.size(); i++) {
            if (insumos.get(i).getCodigo() == insumoAtualizado.getCodigo()) {
                insumos.set(i, insumoAtualizado);
                salvarEmDisco();
                return true;
            }
        }
        return false;
    }

    public boolean deletar(int codigo) {
        boolean removido = insumos.removeIf(i -> i.getCodigo() == codigo);
        if (removido) salvarEmDisco();
        return removido;
    }

    private void salvarEmDisco() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARQUIVO))) {
            oos.writeObject(insumos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar insumos.dat: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<Insumo> carregarDoDisco() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(arquivo))) {
            return (List<Insumo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar insumos.dat: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}