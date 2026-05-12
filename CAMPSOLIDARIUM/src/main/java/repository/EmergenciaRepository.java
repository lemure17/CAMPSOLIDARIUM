package repository;

import model.Emergencia;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmergenciaRepository {

    private static final String ARQUIVO = "emergencias.dat";
    private List<Emergencia> emergencias;

    public EmergenciaRepository() {
        this.emergencias = carregarDoDisco();
    }

    public void salvar(Emergencia emergencia) {
        int novoCodigo = emergencias.stream()
                .mapToInt(Emergencia::getCodigo)
                .max()
                .orElse(0) + 1;
        emergencia.setCodigo(novoCodigo);
        emergencias.add(emergencia);
        salvarEmDisco();
    }

    public List<Emergencia> listarTodos() {
        return new ArrayList<>(emergencias);
    }

    public Emergencia buscarPorCodigo(int codigo) {
        return emergencias.stream()
                .filter(e -> e.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    public boolean atualizar(Emergencia emergenciaAtualizada) {
        for (int i = 0; i < emergencias.size(); i++) {
            if (emergencias.get(i).getCodigo() == emergenciaAtualizada.getCodigo()) {
                emergencias.set(i, emergenciaAtualizada);
                salvarEmDisco();
                return true;
            }
        }
        return false;
    }

    public boolean deletar(int codigo) {
        boolean removido = emergencias.removeIf(e -> e.getCodigo() == codigo);
        if (removido) salvarEmDisco();
        return removido;
    }

    private void salvarEmDisco() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARQUIVO))) {
            oos.writeObject(emergencias);
        } catch (IOException e) {
            System.err.println("Erro ao salvar emergencias.dat: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<Emergencia> carregarDoDisco() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(arquivo))) {
            return (List<Emergencia>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar emergencias.dat: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}